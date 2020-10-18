package com.github.multithreading.basics;

import java.math.BigInteger;

public class ThreadInterruption1Daemon {

  public static void main(String[] args) {

    ComputeThePower t1 = new ComputeThePower(new BigInteger("2"), new BigInteger("-10"));

    //It will stop the thread. Not handled gracefully.
    //Ending of main thread will make entire app terminated.
    // * Marks this thread as either a {@linkplain #isDaemon daemon} thread
    // * or a user thread. The Java Virtual Machine exits when the only
    // * threads running are all daemon threads.
    t1.setDaemon(true);

    t1.start();
    t1.interrupt();
  }

  public static class ComputeThePower extends Thread {
    private BigInteger base;
    private BigInteger power;

    ComputeThePower(BigInteger base, BigInteger power) {
      this.base = base;
      this.power = power;
    }

    @Override public void run() {
      System.out.println(base + "^" + power + "is" + power(base, power));
    }

    /**
     * Computer the power of given base.
     *
     * @param base
     * @param power
     * @return
     */
    public BigInteger power(BigInteger base, BigInteger power) {
      BigInteger result = BigInteger.ONE;
      for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
        result = result.multiply(base);
      }
      return result;
    }
  }
}
