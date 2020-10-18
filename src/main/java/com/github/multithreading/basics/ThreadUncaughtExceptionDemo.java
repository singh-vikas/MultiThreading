package com.github.multithreading.basics;

public class ThreadUncaughtExceptionDemo {

  public static void main(String[] args) {

    //Create a thread.
    Thread thread = new Thread(() -> {
      System.out.println("We are now in the thread :" + Thread.currentThread().getName());

      throw new RuntimeException("Runtime ThreadUncaughtExceptionDemo check");
    });

    //Set the thread name
    thread.setName("ThreadUncaughtExceptionDemo");

    //handle the uncaught exception
    thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
      @Override public void uncaughtException(Thread t, Throwable e) {
        System.out.println("A critical error happened in the thread :" +
          Thread.currentThread().getName() + "The critical error is " + e.getMessage());
      }
    });

    thread.start();
  }
}
