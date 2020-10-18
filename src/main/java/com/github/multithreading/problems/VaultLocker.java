package com.github.multithreading.problems;

import com.sun.net.httpserver.HttpServer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VaultLocker {

    public static final int PASSWORD_UPPER_LIMTT = 99999;

    public static void main(String[] args) {

        Random random = new Random();
        int password = random.nextInt(PASSWORD_UPPER_LIMTT);
        Vault vault = new Vault(password);

        List<Thread> threadList = new ArrayList<>();

        Hacker1 hacker1 = new Hacker1(vault);
        Hacker2 hacker2 = new Hacker2(vault);
        CopThread cop = new CopThread("Jack Ryan");

        threadList.add(hacker1);
        threadList.add(hacker2);
        threadList.add(cop);

        for (Thread t : threadList) {
            t.start();
        }


    }

    public static class Vault {
        int password;

        Vault(int password) {
            this.password = password;
        }

        public boolean isCorrectPassword(int guess) {
            return this.password == guess;
        }
    }

    public static abstract class HackerThread extends Thread {

        protected Vault vault;

        HackerThread(Vault vault) {
            this.vault = vault;
            this.setName(this.getClass().getName());
            this.setPriority(MAX_PRIORITY);
        }

        @Override
        public synchronized void start() {
            System.out.println("Starting :" + this.getName());
            super.start();
        }
    }

    public static class Hacker1 extends HackerThread {

        Hacker1(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int guess = 0; guess < PASSWORD_UPPER_LIMTT; guess++) {
                if (vault.isCorrectPassword(guess)) {
                    System.out.println("Hacker Thread :" + Thread.currentThread().getName() + "guessed the password");
                    System.exit(0);
                }
            }
        }
    }

    public static class Hacker2 extends HackerThread {

        Hacker2(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int guess = PASSWORD_UPPER_LIMTT; guess > 0; guess--) {
                if (vault.isCorrectPassword(guess)) {
                    System.out.println("Hacker Thread :" + Thread.currentThread().getName() + "guessed the password");
                    System.exit(0);
                }
            }
        }
    }

    public static class CopThread extends Thread {

        CopThread(String cop) {
            this.setName(cop);
        }

        @Override
        public void run() {

            for (int i = 10; i > 0; i--) {
                System.out.println(i);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Cop arrived : " + Thread.currentThread().getName() + " arrested the hacker");
            System.exit(0);
        }
    }
}
