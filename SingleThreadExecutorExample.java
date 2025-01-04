import java.util.concurrent.*;

public class SingleThreadExecutorExample {

    public static void main(String[] args) {
        // Create a SingleThreadExecutor
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // Simulating order transactions
        for (int i = 0; i < 5; i++) {
            final int orderId = i + 1;
            executor.submit(() -> logTransaction(orderId));
        }

        // Shut down the executor
        executor.shutdown();
    }

    // Method to simulate logging transactions
    private static void logTransaction(int orderId) {
        System.out.println("Logging transaction for order: " + orderId + " by " + Thread.currentThread().getName());
        try {
            Thread.sleep(500); // Simulating logging time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Transaction for order " + orderId + " logged.");
    }
}
