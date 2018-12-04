package com.bosowski.calculator;

/**
 * @author bosowski
 * created on 4/12/2018
 *
 * Implementing this interface allows to receive
 * notifications from CalculatorServer.
 */
public interface CalculatorServerListener  {
  void getNotified(String message);
}
