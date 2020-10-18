package com.github.multithreading.problems;

import java.math.BigInteger;
import java.util.List;
import java.util.ArrayList;

public class ComplexCalculation {
  public BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2)   {
    BigInteger result;

    List<PowerCalculatingThread> threads = new ArrayList<PowerCalculatingThread>();

    PowerCalculatingThread t1 = new PowerCalculatingThread(base1, power1);
    PowerCalculatingThread t2 = new PowerCalculatingThread(base2, power2);

    threads.add(t1);
    threads.add(t2);

    for (PowerCalculatingThread thread : threads) {
      thread.setDaemon(true);
      thread.start();
    }

    for (PowerCalculatingThread thread : threads) {
      try {
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    result = t1.getResult().add(t2.getResult());

    return result;
  }

  private static class PowerCalculatingThread extends Thread {
    private BigInteger result = BigInteger.ONE;
    private BigInteger base;
    private BigInteger power;

    public PowerCalculatingThread(BigInteger base, BigInteger power) {
      this.base = base;
      this.power = power;
    }

    @Override
    public void run() {

      BigInteger result = BigInteger.ONE;

      for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i= i.add(BigInteger.ONE)) {
        result = result.multiply(base);
      }

      this.result = result;
    }

    public BigInteger getResult() {
      return result;
    }
  }
}
