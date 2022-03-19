import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class OkHttpMain {
    static String run(String url, OkHttpClient client) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    /**
         21:59:24.990 [main] INFO OkHttpMain - hello,nio1
     **/
    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();

        String res = run("http://localhost:8801", client);
        log.info(res);
    }
}
