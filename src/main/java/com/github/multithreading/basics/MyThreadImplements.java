package com.github.multithreading.basics;

public class MyThreadImplements implements Runnable {

  @Override public void run() {
    System.out.println("Inside of MyThreadExtend run method." + Thread.currentThread().getName());
    goToSleep();
  }

  private void goToSleep() {
    System.out.println("Sleeping." + Thread.currentThread().getName());
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
