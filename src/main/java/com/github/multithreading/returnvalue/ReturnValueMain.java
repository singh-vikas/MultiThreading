package com.github.multithreading.returnvalue;

public class ReturnValueMain {

  public static void main(String args[]) {

    System.out.println("Main thread: " + Thread.currentThread().getName() + "starts");

    ThreadSumTask task1 = new ThreadSumTask(3, 4, 5000);
    Thread t1 = new Thread(task1);

    ThreadSumTask task2 = new ThreadSumTask(4, 5, 4000);
    Thread t2 = new Thread(task2);

    ThreadSumTask task3 = new ThreadSumTask(5, 6, 3000);
    Thread t3 = new Thread(task3);

    t1.start();
    t2.start();
    t3.start();

    System.out.println(task1.getSum());
    System.out.println(task2.getSum());
    System.out.println(task3.getSum());

    System.out.println("Main thread :" + Thread.currentThread().getName() + "ends");

  }
}
