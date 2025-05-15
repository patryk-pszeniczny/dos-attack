package org.example.ddos;

import java.util.concurrent.atomic.AtomicLong;

public class DosStat {
    private static final AtomicLong totalRequests = new AtomicLong();
    private static final AtomicLong failedRequests = new AtomicLong();
    private static final AtomicLong totalTrafficSent = new AtomicLong();
    private static final AtomicLong totalTrafficReceived = new AtomicLong();
    private static final AtomicLong totalSuccessTime = new AtomicLong();
    private static final AtomicLong totalSuccessCount = new AtomicLong();

    public static void incrementTotal() {
        totalRequests.incrementAndGet();
    }

    public static void incrementFailed() {
        failedRequests.incrementAndGet();
    }

    public static void recordTraffic(int sent, int received) {
        totalTrafficSent.addAndGet(sent);
        totalTrafficReceived.addAndGet(received);
    }

    public static void recordSuccess(long nanos) {
        totalSuccessTime.addAndGet(nanos);
        totalSuccessCount.incrementAndGet();
    }

    public static void printStats() {
        System.out.println("Total Requests:     " + totalRequests.get());
        System.out.println("Failed Requests:    " + failedRequests.get());
        System.out.println("Traffic Sent (B):   " + totalTrafficSent.get());
        System.out.println("Traffic Recv (B):   " + totalTrafficReceived.get());
        System.out.printf("Avg Time (ms):      %.2f ms%n",
                totalSuccessCount.get() > 0 ? totalSuccessTime.get() / totalSuccessCount.get() / 1_000_000.0 : 0);
    }
}
