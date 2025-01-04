import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class WorkStealingPoolExample {

    public static void main(String[] args) {
        // Create a WorkStealingPool (optimized for parallel tasks)
        ExecutorService executor = Executors.newWorkStealingPool();

        // Product categories to search
        List<String> categories = Arrays.asList("Electronics", "Clothing", "Home Appliances");

        // Use a List to collect all search results (from each sub-task)
        List<Future<List<String>>> futureResults = new ArrayList<>();

        // Divide the search into sub-tasks based on product categories
        for (String category : categories) {
            Future<List<String>> future = executor.submit(() -> searchProductCategory(category));
            futureResults.add(future);
        }

        // Wait for all sub-tasks to complete and aggregate the results
        List<String> aggregatedResults = aggregateResults(futureResults);

        // Print the aggregated search results
        System.out.println("Aggregated search results: " + aggregatedResults);

        // Shut down the executor
        executor.shutdown();
    }

    // Method to simulate searching within a specific category
    private static List<String> searchProductCategory(String category) {
        System.out.println("Searching products in category: " + category + " by " + Thread.currentThread().getName());
        try {
            Thread.sleep(1500); // Simulating search processing time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Returning some simulated product names
        List<String> products = generateSampleProducts(category);
        System.out.println("Search completed for category: " + category + " by " + Thread.currentThread().getName());
        return products;
    }

    // Method to generate a list of products for each category
    private static List<String> generateSampleProducts(String category) {
        switch (category) {
            case "Electronics":
                return Arrays.asList("Smartphone", "Laptop", "Tablet");
            case "Clothing":
                return Arrays.asList("Shirt", "Jeans", "Jacket");
            case "Home Appliances":
                return Arrays.asList("Refrigerator", "Washing Machine", "Microwave");
            default:
                return Collections.emptyList();
        }
    }

    // Method to aggregate the results from all sub-tasks
    private static List<String> aggregateResults(List<Future<List<String>>> futureResults) {
        List<String> aggregatedResults = new ArrayList<>();
        for (Future<List<String>> future : futureResults) {
            try {
                // Wait for each task to complete and add its result to the aggregated list
                List<String> categoryResults = future.get(); 
                aggregatedResults.addAll(categoryResults);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return aggregatedResults;
    }
}
