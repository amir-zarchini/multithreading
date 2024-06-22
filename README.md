# Multi-threading in Java

This repository covers content based on the "Java Multithreading, Concurrency & Performance Optimization" course by Michael Pogrebinsky on Udemy.

<details>
<summary>1. Introduction</summary>

### Motivation and OS Fundamentals

- **Why do we need Threads?**
  - **Responsiveness**: Concurrency (multitasking) improves responsiveness, especially critical in User Interfaces.
  - **Performance**: Parallelism enables handling more tasks in a shorter time.

- **Multithreading Caveat**:
  - Multithreaded programming fundamentally differs from single-threaded programming.

- **What is a Thread?**
  - When a computer boots, the OS loads from disk to memory.
  - Running an application creates an instance in memory, known as a process or context of application.
  - A process is composed of Files, Data (Heap), Code, and Main Thread (Stack, Instruction Pointer).
  - In the context of threads, the Stack and IP are unique, while the rest is shared.

### OS Fundamentals Part 2

- **Context Switch**:
  - Process and thread context switching involves suspending one thread and resuming another.
  - Threads compete for CPU time, leading to context switching.
  - Excessive threads can lead to thrashing, where more time is spent managing threads than executing them.
  - Thread context switching is cheaper than process context switching.

- **Thread Scheduling**:
  - Different strategies include First Come First Serve and Shortest Job First.
  - Most OSs use dynamic priority to avoid issues like starvation.

- **Thread vs Process**:
  - **Thread**: Suitable when tasks share a lot of data, offers faster execution and switching.
  - **Process**: Prioritizes security and stability, suitable for unrelated tasks.

</details>

<details>
<summary>2. Threading Fundamentals - Thread Creation</summary>

### Thread Creation 1

- **Thread.sleep()**: Instructs the OS to not schedule the current thread, freeing up the CPU.
- **Setting Priority**: Use `threadInstance.setPriority(1..10)` to set thread priority.
- **Exception Handling**: Use `thread.setUncaughtExceptionHandler()` to handle uncaught exceptions.

### Thread Creation 2

- Creating threads by extending the `Thread` class and overriding the `run()` method.

</details>

<details>
<summary>3. Threading Fundamentals - Thread Coordination</summary>

### Thread Termination & Daemon Threads 1

- **Thread Termination**:
  - Threads occupy resources like memory, CPU cycles, and cache memory.
  - Need to clean up resources when threads finish.
  - Application doesn't stop if there are running threads.

- **Interrupting Threads**:
  - Add code to handle interrupt signals.
  - Use methods that throw `InterruptedException`.

- **Daemon Threads**:
  - Threads that do not affect the application's operation and can terminate without issues.

### Joining Threads

- **Need for Joining**:
  - Threads operate independently, requiring synchronization at times.

- **How to Join**:
  - Use the `join` method to make a thread wait for another to finish, avoiding inefficient looping checks.

</details>

<details>
<summary>4. Performance Optimization</summary>

- **Performance Metrics**:
  - **Latency**: Time to complete a task.
  - **Throughput**: Number of tasks completed in a given period.

- **Reducing Latency**:
  - Divide tasks into subtasks to execute in parallel, reducing time.

- **Thread Pooling**:
  - Precreate threads to reduce the overhead of creation and destruction.

- **Optimal Thread Pool Size**:
  - For IO-bound applications, more threads than CPU cores can be beneficial.

</details>

<details>
<summary>5. Data Sharing Between Threads</summary>

- **Stack**:
  - Holds local variables and function parameters.
  - Unique to each thread.

- **Heap**:
  - Shared memory for objects and class members.
  - Managed by Garbage Collection.

- **Resource Sharing**:
  - Necessary for scenarios like work queues or databases accessed by multiple threads.

- **Concurrency Challenges**:
  - Ensuring atomic operations to prevent inconsistencies due to simultaneous access by multiple threads.

</details>

<details>
<summary>6. Concurrency Challenge</summary>

- **Critical Section**:
  - Code that accesses shared resources which should not be accessed simultaneously by multiple threads.

- **Synchronized Blocks**:
  - Use `synchronized` keyword to create blocks or methods that are mutually exclusive.

- **Atomic Operations**:
  - Ensure operations on shared resources are atomic to avoid race conditions.

- **Deadlock**:
  - Conditions and solutions to avoid deadlock situations in multithreaded applications.

</details>

<details>
<summary>7. Advanced Locking</summary>

- **ReentrantLock**:
  - Offers more flexibility than `synchronized` keyword.
  - Requires explicit locking and unlocking.

- **ReentrantReadWriteLock**:
  - Allows multiple threads to read while maintaining exclusive write access, improving efficiency for read-heavy scenarios.

</details>

<details>
<summary>8. Inter-Thread Communication</summary>

- **Semaphore**:
  - Used to limit the number of threads accessing resources.
  - Can act as a condition variable.

- **Condition Variables**:
  - Paired with locks to control thread execution based on conditions.

- **Using Object for Condition Variables**:
  - Utilize `wait()`, `notify()`, and `notifyAll()` for managing thread communication.

</details>

<details>
<summary>9. Lock-Free Algorithm</summary>

- **Challenges with Locks**:
  - Deadlocks, slow critical sections, priority inversion, and performance overhead.

- **Atomic Classes**:
  - Use classes like `AtomicInteger` for lock-free thread-safe operations.

- **AtomicReference**:
  - Provides atomic operations on objects.

</details>

<details>
<summary>10. Threading Models for High Performance IO</summary>

- **Blocking Operations**:
  - Can severely degrade performance in IO-bound applications.

- **Non-Blocking IO**:
  - Use callbacks and non-blocking operations to improve efficiency and responsiveness.

- **Thread Per Task Model**:
  - Creating a thread for each task can be expensive and inefficient.

- **Summary**:
  - Combining thread per core with non-blocking IO yields optimal performance.

</details>
