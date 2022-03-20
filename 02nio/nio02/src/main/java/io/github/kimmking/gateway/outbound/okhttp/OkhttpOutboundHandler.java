package io.github.kimmking.gateway.outbound.okhttp;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import io.github.kimmking.gateway.filter.HttpRequestFilter;
import io.github.kimmking.gateway.outbound.httpclient4.NamedThreadFactory;
import io.github.kimmking.gateway.router.HttpEndpointRouter;
import io.github.kimmking.gateway.router.RandomHttpEndpointRouter;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.*;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

@Slf4j
public class OkhttpOutboundHandler {
    private final List<String> backends;

    private final ExecutorService proxyService;

    public OkhttpOutboundHandler(List<String> backends) {
        this.backends = backends;
        log.info("new backends {}", backends);

        int cores = Runtime.getRuntime().availableProcessors();
        long keepAliveTime = 1000;
        int queueSize = 2048;
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();//.DiscardPolicy();
        proxyService = new ThreadPoolExecutor(cores, cores,
                keepAliveTime, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(queueSize),
                new NamedThreadFactory("proxyService"), handler);

    }

    HttpEndpointRouter router = new RandomHttpEndpointRouter();

    public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, HttpRequestFilter filter) {
        String backendUrl = router.route(this.backends);
        final String url = backendUrl + fullRequest.uri();
        filter.filter(fullRequest, ctx);

        proxyService.submit(() -> {
            try {
                fetchGet(fullRequest, ctx, url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void fetchGet(FullHttpRequest fullRequest, ChannelHandlerContext ctx, String url) throws IOException {
        log.info("proxy {} {} {}", fullRequest.method(), url, fullRequest.headers());


        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response okResponse = client.newCall(request).execute();
        String value =  okResponse.body().string();

//            httpGet ...  http://localhost:8801
//            返回的响应，"hello,nio";
//            value = reponse....

        DefaultFullHttpResponse response;
        try {
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(value.getBytes("UTF-8")));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", response.content().readableBytes());
            ctx.write(response);
            ctx.flush();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


//        response = new DefaultFullHttpResponse(HTTP_1_1, OK);
//        if (!HttpUtil.isKeepAlive(fullRequest)) {
//            ctx.write(response).addListener(ChannelFutureListener.CLOSE);
//        } else {
//            //response.headers().set(CONNECTION, KEEP_ALIVE);
    }
}
