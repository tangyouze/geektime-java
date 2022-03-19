package io.github.kimmking.gateway.router;

import java.util.List;
import java.util.Random;

public class RandomHttpEndpointRouter implements HttpEndpointRouter {
    Random random = new Random(System.currentTimeMillis());

    @Override
    public String route(List<String> urls) {
        int size = urls.size();
        return urls.get(random.nextInt(size));
    }
}
