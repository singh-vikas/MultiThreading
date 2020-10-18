package com.github.multithreading.executor;

import java.util.Random;
import java.util.concurrent.TimeUnit;

class ThreadTask implements Runnable {

  private String id;
  private int instance;
  private static int count = 0;

  Random random = new Random();

  public ThreadTask() {
    this.instance = count++;
    this.id = "ThreadTask" + instance;

  }

  @Override public void run() {

    System.out.println("##### <TASK-" + id + "> STARTING #####");

    for (int i = 10; i > 0; i--) {
      System.out.println(Thread.currentThread().getName()+ "<" + id + ">TICK TICK - " + i );

      try {
        TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    System.out.println("***** <TASK-" + id + "> COMPLETED *****");
  }
}
