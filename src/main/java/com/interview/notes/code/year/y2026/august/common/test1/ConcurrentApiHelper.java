package com.interview.notes.code.year.y2026.august.common.test1;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.Executors;

public class ConcurrentApiHelper {

    private final HttpClient client = HttpClient.newBuilder()
            .executor(Executors.newVirtualThreadPerTaskExecutor())
            .build();
//
//    public List<String> fetchAll(List<String> urls) throws Exception {
//        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
//            List<Future<String>> futures = urls.stream()
//                    .map(url -> scope.fork(() -> fetchCall(url)))
//                    .toList();
//
//            scope.join();
//            scope.throwIfFailed();
//
//            return futures.stream().map(Future::resultNow).toList();
//        }
//    }

    private String fetchCall(String url) throws Exception {
        var request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }
}