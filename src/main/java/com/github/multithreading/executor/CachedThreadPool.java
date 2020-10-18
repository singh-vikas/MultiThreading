package com.github.multithreading.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPool {

  public static void main(String[] args) {

    System.out.println("[" + Thread.currentThread().getName() + "] Main thread starts here...");

    //As many thread as required. No fixed number of thread.
    ExecutorService executorService = Executors.newCachedThreadPool(new NamedThreadFactory());

    for (int i = 0; i < 6; i++) {
      executorService.submit(new ThreadTask());
    }

    executorService.shutdown(); //Stray orphan thread running if you don't shutdown.

    System.out.println("[" + Thread.currentThread().getName() + "] Main thread ends here...");

    //executorService.submit(new ThreadTask());   //java.util.concurrent.RejectedExecutionException:
    // Task java.util.concurrent.FutureTask@4b67cf4d rejected from
    // java.util.concurrent.ThreadPoolExecutor@7ea987ac
    // [Shutting down, pool size = 6, active threads = 6, queued tasks = 0, completed tasks = 0]

    // executorService.shutdownNow();
  }
}
