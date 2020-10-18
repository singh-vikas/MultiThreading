package com.github.multithreading.problems;

import java.util.Random;

public class IntersectionDeadLockImplementation {

    public static void main(String args[]) {

        Intersection intersection = new Intersection();
        Thread trainA = new Thread(new TrainA(intersection));
        Thread trainB = new Thread(new TrainB(intersection));

        trainA.start();
        trainB.start();

    }
}


class TrainB implements Runnable {

    private Intersection intersection;
    private Random random = new Random();

    TrainB(Intersection intersection) {
        this.intersection = intersection;
    }

    @Override
    public void run() {

        while (true) {
            long sleepTime = random.nextInt(5);

            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                System.out.println("Train has passsed" + Thread.currentThread().getName());
            }
            intersection.takeRoadB();
        }
    }
}

class TrainA implements Runnable {

    Intersection intersection;

    TrainA(Intersection intersection) {
        this.intersection = intersection;
    }

    @Override
    public void run() {
        while (true) {
            intersection.takeRoadA();
        }
    }
}

class Intersection {

    Object roadA = new Object();
    Object roadB = new Object();

    public void takeRoadA() {
        synchronized (roadA) {
            System.out.println("Train is crossing roadA" + Thread.currentThread().getName());
        }
        synchronized (roadB) {
            System.out.println("RoadB is currently blocked");
        }
    }

    public void takeRoadB() {
        synchronized (roadB) {
            System.out.println("Train is crossing roadB" + Thread.currentThread().getName());
        }
        synchronized (roadA) {
            System.out.println("roadA is currently blocked");
        }
    }

}
