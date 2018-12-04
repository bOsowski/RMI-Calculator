package com.bosowski.calculator.server;

import com.bosowski.calculator.Calculator;
import com.bosowski.calculator.CalculatorServerListener;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author bosowski
 * created on 4/12/2018
 *
 * The class binds itself to the registry on the local
 * 'com.bosowski.calculator.Calculator' address.
 * The binding might fail if the port or address is
 * already occupied.
 */
public class CalculatorServer extends UnicastRemoteObject implements Calculator {

  CalculatorServerListener listener;

  /**
   * Binds itself to registry under the 'com.bosowski.calculator.Calculator' address.
   * @param listener to be notified when the methods are invoked.
   * @throws RemoteException when binding fails.
   */
  public CalculatorServer(CalculatorServerListener listener) throws RemoteException{
    super();
    LocateRegistry.createRegistry(1099).rebind("com.bosowski.calculator.Calculator", this);
    System.out.println("Successfully bound calculator to registry.");
    this.listener = listener;
  }

  @Override
  public double add(double a, double b) {
    listener.getNotified("Add method called with values " + a + " and " + b + ".");
    return a + b;
  }

  @Override
  public double subtract(double a, double b) {
    listener.getNotified("Subtract method called with values " + a + " and " + b + ".");
    return a - b;
  }

  @Override
  public double multiply(double a, double b) {
    listener.getNotified("Multiply method called with values " + a + " and " + b + ".");
    return a * b;
  }

  @Override
  public double divide(double a, double b) {
    listener.getNotified("Divide method called with values " + a + " and " + b + ".");
    return a / b;
  }
}
