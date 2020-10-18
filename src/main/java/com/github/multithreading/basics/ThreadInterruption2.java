package com.github.multithreading.basics;

public class ThreadInterruption2 {

  public static void main(String[] args) {

    Thread t1 = new Thread(new SleeperThread());
    t1.start();
    t1.interrupt();

  }

  public static class SleeperThread extends Thread {

    @Override public void run() {
      try {
        Thread.sleep(100000000);
      } catch (InterruptedException e) {
        System.out.println("Why I have been interrupted. " + Thread.currentThread().getName());
      }
    }

  }
}
