package com.github.multithreading.basics.lock;

public class ObjectLevelLockDemo implements Runnable {

    private Object obj = new Object();

    @Override
    public void run() {
        lock();
        anotherWayObjectLock();
        oneMoreWayToObjectLock();

    }

    private void oneMoreWayToObjectLock() {
        System.out.println("in block " + Thread.currentThread().getName());

        synchronized (obj) { //other thread safe code
            System.out.println(Thread.currentThread().getName());
            System.out.println("in block " + Thread.currentThread().getName() + " end");
        }
    }

    ////other thread safe code
    private synchronized void anotherWayObjectLock() {
        System.out.println("in block " + Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getName());
        System.out.println("in block " + Thread.currentThread().getName() + " end");
    }

    public void lock() {
        System.out.println(Thread.currentThread().getName());
        synchronized (this) { //other thread safe code
            System.out.println("in block " + Thread.currentThread().getName());
            System.out.println("in block " + Thread.currentThread().getName() + " end");
        }
    }


    public static void main(String[] args) {
        ObjectLevelLockDemo b1 = new ObjectLevelLockDemo();
        Thread t1 = new Thread(b1);
        Thread t2 = new Thread(b1);

        ObjectLevelLockDemo b2 = new ObjectLevelLockDemo();
        Thread t3 = new Thread(b2);

        //Note that t3 will not block when threads t1 and t2 block.
        // Because the lock is placed on this object and thread t3 has different this object than thread t1,t2

        t1.setName("t1");
        t2.setName("t2");
        t3.setName("t3");
        t1.start();
        t2.start();
        t3.start();
    }
}
