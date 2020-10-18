package com.github.multithreading.basics;

public class FirstNewThread {

  public static void main(String[] args) throws InterruptedException {

    //Create first thread.
    Thread firstThread = new Thread(() -> {
      System.out.println("We are now in the thread :" + Thread.currentThread().getName());
      System.out.println("The priority of the thread is :" + Thread.currentThread().getPriority());

    });

    //Set the name of thread.
    firstThread.setName("First thread");
    // Set priority of first thread.
    firstThread.setPriority(Thread.MAX_PRIORITY);

    System.out.println("Current thread before executing the thread: " + Thread.currentThread().getName());
    firstThread.start();
    System.out.println("Current thread after executing the thread: " + Thread.currentThread().getName());
    Thread.sleep(10000);


  }

}
