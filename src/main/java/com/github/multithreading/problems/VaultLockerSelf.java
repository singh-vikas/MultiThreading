package com.github.multithreading.problems;

import java.util.Random;

public class VaultLockerSelf {

    public static final int PASSWORD_UPPER_LIMTT = 99999;

    public static void main(String args[]) {
        Random random = new Random();
        int password = random.nextInt(PASSWORD_UPPER_LIMTT);

        Vault vault = new Vault(password);

        Thread hacker1 = new HackerEnd("HackerEnd", vault);
        Thread hacker2 = new HackerStart("HackerStart", vault);

        Thread cop = new CopThread();

        hacker1.start();
        hacker2.start();
        cop.start();


    }


    public static class Vault {

        int password;

        Vault(int password) {
            this.password = password;
        }

        boolean isCorrectPassword(int input) {
            return input == password;
        }

    }


    public static abstract class AbstractHackerThread extends Thread {
        private Vault vault;

        AbstractHackerThread(String name, Vault vault) {
            this.setName(name);
            this.vault = vault;
            this.setPriority(MAX_PRIORITY);
        }

        @Override
        public void start() {
            System.out.println("Running thread" + Thread.currentThread().getName());
            super.start();
        }

        boolean guessThePassword(int guess) {
            return vault.isCorrectPassword(guess);
        }
    }

    public static class HackerStart extends AbstractHackerThread {
        HackerStart(String name, Vault vault) {
            super(name, vault);
        }

        @Override
        public void run() {
            for (int i = 0; i < PASSWORD_UPPER_LIMTT; i++) {
                if (guessThePassword(i)) {
                    System.out.println("Hacker :" + Thread.currentThread().getName() + "guessed the password!");
                    System.exit(0);
                }
            }
        }
    }

    public static class HackerEnd extends AbstractHackerThread {
        HackerEnd(String name, Vault vault) {
            super(name, vault);
        }

        @Override
        public void run() {
            for (int i = 0; i < PASSWORD_UPPER_LIMTT; i++) {
                if (guessThePassword(i)) {
                    System.out.println("Hacker :" + Thread.currentThread().getName() + "guessed the password!");
                    System.exit(0);
                }
            }
        }
    }

    public static class CopThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Cop has been interuppted");
                }
            }

            System.out.println("Cop has arrived.");
        }
    }

}
