package com.github.multithreading.returnvalue;

import java.util.concurrent.TimeUnit;

public class ThreadSumTask implements Runnable {

  private int a;
  private int b;
  private int sum;
  private long sleepTime;

  private int instanceNumber;
  private static int count;
  private String taskName;
  private volatile boolean isDone;

  public ThreadSumTask(int a, int b, long sleepTime) {
    this.a = a;
    this.b = b;
    this.sleepTime = sleepTime;

    this.instanceNumber = ++count;
    this.taskName = "ThreadSumTask " + instanceNumber;
  }

  @Override public void run() {

    System.out.println("##### [" + Thread.currentThread().getName() + "] <" + taskName +
      "> STARTING #####");

    try {
      TimeUnit.MILLISECONDS.sleep(sleepTime);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    sum = a + b;

    System.out.println("##### [" + Thread.currentThread().getName() + "] <" + taskName +
      "> COMPLETED #####");

    isDone = true;

    synchronized (this) {
      System.out.println("[" + Thread.currentThread().getName() + "] <" + taskName +
        "> NOTIFYING ...");
      this.notify();
    }

  }

  public int getSum() {
    synchronized (this) {
      if (!isDone) {
        try {
          System.out.println("CurrentThread:" + Thread.currentThread().getName() + " waiting for " + this.taskName);
          this.wait();
        } catch (InterruptedException e) {
          System.out.println("A critical error happened");
        }
      }
    }

    return sum;

  }

}
