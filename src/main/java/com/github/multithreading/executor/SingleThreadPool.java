package com.github.multithreading.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadPool {

  public static void main(String[] args) {

    //Serializes task execution. One by one (Synchrozied or Serialzed access to common resource.)
    ExecutorService executorService = Executors.newSingleThreadExecutor();

    for (int i = 0; i < 6; i++) {
      executorService.submit(new ThreadTask());
    }

    executorService.shutdown();

  }
}
