package com.github.multithreading.executor;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FixedThreadPoolDemo {

  public static void main(String[] args) {

    ExecutorService executorService = Executors.newFixedThreadPool(6);

    for (int i = 0; i < 6; i++) {
      executorService.submit(new ThreadTask());
    }

    executorService.shutdown(); //Stray orphan thread running if you don't shutdown.

    //executorService.submit(new ThreadTask());   //java.util.concurrent.RejectedExecutionException:
    // Task java.util.concurrent.FutureTask@4b67cf4d rejected from
    // java.util.concurrent.ThreadPoolExecutor@7ea987ac
    // [Shutting down, pool size = 6, active threads = 6, queued tasks = 0, completed tasks = 0]

    // executorService.shutdownNow();
  }
}

