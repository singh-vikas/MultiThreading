package com.github.multithreading.basics;

public class ThreadRunTestCase {

  public static void main(String[] args) {

    MyThreadExtend t1 = new MyThreadExtend("MyThreadExtend1");
    MyThreadExtend t2 = new MyThreadExtend("MyThreadExtend2");

    Thread t3 = new Thread(new MyThreadImplements(), "MyThreadImplements3");
    Thread t4 = new Thread(new MyThreadImplements(), "MyThreadImplements4");

    t1.start();
    t2.start();
    t3.start();
    t4.start();

  }
}
