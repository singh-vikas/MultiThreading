package com.github.multithreading.executor;

import java.util.concurrent.ThreadFactory;

public class NamedThreadFactory implements ThreadFactory {

  private int count;
  private String name = "PoolWorker";

  @Override public Thread newThread(Runnable r) {

    Thread t = new Thread(r, name + ++count);
    return t;
  }
}
