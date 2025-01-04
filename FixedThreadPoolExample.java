import java.util.concurrent.*;

public class FixedThreadPoolExample {

    public static void main(String[] args) {
        // Create a FixedThreadPool with 10 threads
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // Sample orders to be processed
        for (int i = 0; i < 50; i++) {
            final int orderId = i + 1;
            executor.submit(() -> processOrder(orderId));
        }

        // Shut down the executor
        executor.shutdown();
    }

    // Method to simulate order processing
    private static void processOrder(int orderId) {
        System.out.println("Processing order: " + orderId + " by " + Thread.currentThread().getName());
        try {
            Thread.sleep(2000); // Simulating some time to process order
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Order " + orderId + " processed.");
    }
}
