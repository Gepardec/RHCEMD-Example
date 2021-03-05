package at.gepardec.example.rhcemd.mp.fault;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HttpClientCallable implements Callable<String> {

    private static final String baseURI = "http://localhost:8080/rhcemd/api";

    private final int call;

    private final HttpGet request;

    private HttpClientCallable(String path, int call) {
        this.request = new HttpGet(baseURI + path);
        this.call = call;
    }

    public static HttpClientCallable forPath(String path, int call) {
        return new HttpClientCallable(Objects.requireNonNull(path, "Path must not be null"), call);
    }

    public static List<HttpClientCallable> repeatedForPath(String path, int times) {
        return IntStream.range(0, times).mapToObj(i -> forPath(path, i + 1)).collect(Collectors.toList());
    }

    @Override
    public String call() throws Exception {
        String response = callAndGetResponse();

        return call + " call '" + request.getURI().toString() + "' with response '" + response + "'";
    }

    private String callAndGetResponse() throws Exception {
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        return EntityUtils.toString(httpResponse.getEntity());
    }
}
