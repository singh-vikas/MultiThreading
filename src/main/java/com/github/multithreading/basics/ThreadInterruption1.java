package com.github.multithreading.basics;

import java.math.BigInteger;

public class ThreadInterruption1 {

  public static void main(String[] args) {

    ComputeThePower t1 = new ComputeThePower(new BigInteger("2"), new BigInteger("-10"));
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
        if (Thread.currentThread().isInterrupted()) {
          // t1.interrupt(); This wouldn't stop the thread.It needs isInterrupted check as well.
          // t1.interrupt() set the isInterrupted to true.
          System.out.println("I have been interrupted");
          return BigInteger.ZERO;
        }
        result = result.multiply(base);
      }
      return result;
    }
  }
}
