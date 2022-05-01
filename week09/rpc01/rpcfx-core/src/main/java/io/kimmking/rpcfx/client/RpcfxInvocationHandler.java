package io.kimmking.rpcfx.client;

import com.alibaba.fastjson.JSON;
import io.kimmking.rpcfx.api.Filter;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.boot.autoconfigure.elasticsearch.jest.HttpClientConfigBuilderCustomizer;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class RpcfxInvocationHandler implements InvocationHandler {

    public static final MediaType JSONTYPE = MediaType.get("application/json; charset=utf-8");

    private final Class<?> serviceClass;
    private final String url;
    private final Filter[] filters;

    public <T> RpcfxInvocationHandler(Class<T> serviceClass, String url, Filter... filters) {
        this.serviceClass = serviceClass;
        this.url = url;
        this.filters = filters;
    }

    // 可以尝试，自己去写对象序列化，二进制还是文本的，，，rpcfx是xml自定义序列化、反序列化，json: code.google.com/p/rpcfx
    // int byte char float double long bool
    // [], data class

    @Override
    public Object invoke(Object proxy, Method method, Object[] params) throws Throwable {

        // 加filter地方之二
        // mock == true, new Student("hubao");

        RpcfxRequest request = new RpcfxRequest();
        request.setServiceClass(this.serviceClass.getName());
        request.setMethod(method.getName());
        request.setParams(params);

        if (null != filters) {
            for (Filter filter : filters) {
                if (!filter.filter(request)) {
                    return null;
                }
            }
        }

        RpcfxResponse response = post(request, url);

        // 加filter地方之三
        // Student.setTeacher("cuijing");

        // 这里判断response.status，处理异常
        // 考虑封装一个全局的RpcfxException

        return JSON.parse(response.getResult().toString());
    }

    private RpcfxResponse post(RpcfxRequest req, String url) throws IOException {
        String reqJson = JSON.toJSONString(req);
        System.out.println("req json: " + reqJson);

        // 1.可以复用client
        // 2.尝试使用httpclient或者netty client
        final Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(JSONTYPE, reqJson))
                .build();
        String respJson = CLIENT.newCall(request).execute().body().string();
        System.out.println("resp json: " + respJson);
        return JSON.parseObject(respJson, RpcfxResponse.class);
    }

    private RpcfxResponse postWithHTTPClient(RpcfxRequest req, String url) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        String json = JSON.toJSONString(req);
        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        CloseableHttpResponse response = client.execute(httpPost);
        client.close();
        return JSON.parseObject(response.toString(), RpcfxResponse.class);
    }
    static final OkHttpClient CLIENT = new OkHttpClient();
}
