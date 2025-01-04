import java.util.concurrent.*;

public class CachedThreadPoolExample {

    public static void main(String[] args) {
        // Create a CachedThreadPool
        ExecutorService executor = Executors.newCachedThreadPool();

        // Simulating customer service queries
        for (int i = 0; i < 50; i++) {
            final int queryId = i + 1;
            executor.submit(() -> handleCustomerQuery(queryId));
        }

        // Shut down the executor
        executor.shutdown();
    }

    // Method to simulate handling customer query
    private static void handleCustomerQuery(int queryId) {
        System.out.println("Handling customer query: " + queryId + " by " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000); // Simulating query handling time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Customer query " + queryId + " resolved.");
    }
}
