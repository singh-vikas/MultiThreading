package com.github.multithreading.basics.lock;

public class ClassLevelLockDemo implements Runnable {

    //Java synchronization will throw NullPointerException if object used in synchronized block is null.
    private final static Object lock = new Object();

    @Override
    public void run() {
        lock();
    }

    //Method is static - to make static data  thread safe.
    //Java synchronized keyword is re-entrant in nature it means
    // if a synchronized method calls another synchronized
    // method which requires same lock then current thread which
    // is holding lock can enter into that method without acquiring lock.
    public synchronized static void classLevelMethod() {

    }

    public void lockClassLevel() {
        ////Lock object is static
        synchronized (lock) {   //NPE if lock is null.
        }
    }

    public void lock() {
        System.out.println(Thread.currentThread().getName());

        //Synchronized block is going to be executed for the same thread.
        synchronized (ClassLevelLockDemo.class) {
            System.out.println("in block " + Thread.currentThread().getName());
            System.out.println("in block " + Thread.currentThread().getName() + " end");
        }
    }

    public static void main(String[] args) {
        ClassLevelLockDemo b1 = new ClassLevelLockDemo();
        Thread t1 = new Thread(b1);
        Thread t2 = new Thread(b1);
        ClassLevelLockDemo b2 = new ClassLevelLockDemo();
        Thread t3 = new Thread(b2);
        t1.setName("t1");
        t2.setName("t2");
        t3.setName("t3");
        t1.start();
        t2.start();
        t3.start();
    }
}
