package com.github.multithreading.basics;

public class MyThreadExtend extends Thread {

  MyThreadExtend(String threadName) {
    super(threadName);
  }

  @Override public void run() {
    System.out.println("Inside of MyThreadExtend run method." + Thread.currentThread().getName());
    goToSleep();
  }

  private void goToSleep() {
    System.out.println("MyThreadExtend is sleeping." + Thread.currentThread().getName());
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
