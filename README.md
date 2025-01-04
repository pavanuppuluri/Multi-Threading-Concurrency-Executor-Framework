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

| **Type**                        | **Use Case**                                                                                          |
|----------------------------------|----------------------------------------------------------------------------------------------------------|
| **FixedThreadPool**              | **Use case**: Web Server Request Handling <br><br> **Scenario**: Imagine you're building a web server where you want to process a set of incoming requests concurrently but limit the number of concurrent threads that can be used at any time to avoid overloading the system. <br><br> A fixed-size thread pool allows you to ensure that only a fixed number of threads are used, which is crucial for controlling resource consumption.|
| **CachedThreadPool**    | **Use case**: Handling Short-Lived Tasks in Burst Traffic <br><br> **Scenario**: You run a system that processes short-lived tasks like image compression, video processing, or analytics that can vary in frequency. During peak periods, such as when many users are uploading content, you need to dynamically create threads to handle bursts of tasks without waiting for idle threads. <br><br> A cached thread pool is useful here because it creates new threads as needed and reuses idle threads.  |
| **SingleThreadExecutor**           | **Use case**: Sequential Task Execution <br><br> **Scenario**: A logging system in a server or an application that writes log entries to a single log file. The system needs to ensure that the log entries are written in order, one after the other, without concurrent writes.      |
| **ScheduledThreadPoolExecutor**     | **Use case**: Periodic Task Scheduling <br><br> **Scenario**: You might need to run tasks at regular intervals or with a delay, like backing up data every night or cleaning up expired sessions from a database. <br><br> The ScheduledThreadPoolExecutor is well-suited for this kind of periodic task scheduling.              |
| **WorkStealingPool**      | **Use case**: Parallelizing Independent Tasks <br><br> **Scenario**: Imagine you're running a data analysis system that splits a large dataset into multiple smaller tasks. These tasks might take varying amounts of time to complete, and you want to ensure that all available threads are utilized as efficiently as possible by dynamically redistributing tasks to threads that are idle. <br><br> **Example**: An image processing pipeline that processes different sections of images. Each section may vary in size or complexity, and you want the tasks to be handled as quickly as possible by "stealing" tasks from other threads that are idle.|

### Customizing ` ThreadPoolExecutor `
We can directly use the ThreadPoolExecutor class to have finer control over the behavior of your thread pool, such as the core pool size, maximum pool size, keep-alive time, and blocking queue.

```
ThreadPoolExecutor executor = new ThreadPoolExecutor(
        2,  // corePoolSize
        4,  // maximumPoolSize
        60L, // keep-alive time
        TimeUnit.SECONDS, 
        new LinkedBlockingQueue<>(10));  // Task queue with a capacity of 10

for (int i = 0; i < 20; i++) {
    executor.submit(() -> {
        System.out.println(Thread.currentThread().getName() + " is processing a task");
    });
}
executor.shutdown();
```
