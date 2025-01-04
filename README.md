# Multi threading, Concurrency, Executor Framework


## Java Executor Framework

The Executor framework in Java provides several useful interfaces and classes for managing threads:

| **Interface/Class**              | **Description**                                                                                         |
|----------------------------------|---------------------------------------------------------------------------------------------------------|
| **Executor Interface**           | The base interface for all executors that decouples task submission from the mechanics of task execution. |
| **ExecutorService Interface**    | Extends Executor and adds methods for lifecycle management, such as shutdown and await termination.       |
| **ThreadPoolExecutor**           | A flexible and configurable implementation of ExecutorService for managing a pool of worker threads.      |
| **ScheduledExecutorService**     | Extends ExecutorService and supports tasks that are executed after a delay or periodically.              |
| **Executors Utility Class**      | A helper class to create commonly used ExecutorService implementations like fixed-size thread pools, single-thread executors, etc. |

## Key Types of Executor Services

| **Type**                        | **Description**                                                                                          |
|----------------------------------|----------------------------------------------------------------------------------------------------------|
| **FixedThreadPool**              | `Executors.newFixedThreadPool(int nThreads)` <br><br> This type of thread pool has a fixed number of threads that will be reused to execute tasks. <br><br> It is typically used when you know the number of concurrent tasks and want to limit the number of threads. |
| **CachedThreadPool**    | `Executors.newCachedThreadPool()` <br><br> A cached thread pool creates new threads as needed, but will reuse previously constructed threads when they are available. <br><br> It’s useful when you expect a large number of short-lived tasks.       |
| **SingleThreadExecutor**           | `Executors.newSingleThreadExecutor()` <br><br> A single-threaded executor that ensures that tasks are executed sequentially in a single worker thread. <br><br> It's ideal when tasks need to be processed in a strict order, one at a time.      |
| **ScheduledThreadPoolExecutor**     | `Executors.newScheduledThreadPool(int corePoolSize)` <br><br> Used for scheduling tasks with fixed-rate or fixed-delay execution policies. <br><br> This is helpful for recurring tasks like periodic monitoring or maintenance tasks.              |
| **WorkStealingPool**      | `Executors.newWorkStealingPool()` <br><br> This is a special type of pool designed to handle workloads efficiently in a parallel and scalable manner by distributing tasks dynamically among threads. |


## Executor Services Use Cases
| **Type**                        | **Description**                                                                                          | **Use Case** | **Example** |
|----------------------------------|----------------------------------------------------------------------------------------------------------|--------------|-------------|
| **FixedThreadPool**              | `Executors.newFixedThreadPool(int nThreads)` <br><br> This type of thread pool has a fixed number of threads that will be reused to execute tasks. <br><br> It is typically used when you know the number of concurrent tasks and want to limit the number of threads. | | `ExecutorService executor = Executors.newFixedThreadPool(5);` <br> `// 5 threads` <br> `for (int i = 0; i < 10; i++) {` <br> `executor.submit(() -> {` <br> `System.out.println(Thread.currentThread().getName() + " is executing task");` <br> `});` <br> `}` <br> `executor.shutdown();` |
| **ExecutorService Interface**    | Extends Executor and adds methods for lifecycle management, such as shutdown and await termination.       | | |
| **ThreadPoolExecutor**           | A flexible and configurable implementation of ExecutorService for managing a pool of worker threads.      | | |
| **ScheduledExecutorService**     | Extends ExecutorService and supports tasks that are executed after a delay or periodically.              | | |
| **Executors Utility Class**      | A helper class to create commonly used ExecutorService implementations like fixed-size thread pools, single-thread executors, etc. | | |
