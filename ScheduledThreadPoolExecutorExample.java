import java.util.concurrent.*;

public class ScheduledThreadPoolExecutorExample {

    public static void main(String[] args) {
        // Create a ScheduledThreadPoolExecutor with one thread for scheduled tasks
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Define the task to apply promotional discounts
        Runnable discountTask = () -> applyPromotionalDiscounts();

        // Schedule the task to run once every 24 hours (86400 seconds)
        scheduler.scheduleAtFixedRate(discountTask, 0, 24, TimeUnit.HOURS);

        // Optional: Shut down the scheduler after some time for demo purposes
        // scheduler.shutdown();  // Uncomment to shut down after some time
    }

    // Method to simulate applying discounts
    private static void applyPromotionalDiscounts() {
        System.out.println("Applying promotional discounts at " + System.currentTimeMillis());
        try {
            Thread.sleep(1000); // Simulating discount application
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Promotional discounts applied.");
    }
}
