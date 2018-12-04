package com.bosowski.calculator.server;

import com.bosowski.calculator.Calculator;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class CalculatorServer extends UnicastRemoteObject implements Calculator {

  public CalculatorServer() throws RemoteException{
    super();
    LocateRegistry.createRegistry(1099).rebind("com.bosowski.calculator.Calculator", this);
    System.out.println("Successfully bound calculator to registry.");
  }

 public static void main(String args[]){
   try {
     new CalculatorServer();
   } catch (RemoteException e) {
     System.out.println("Error while trying to instantiate " + CalculatorServer.class.getName() + ": " + e.getMessage());
     e.printStackTrace();
   }
 }

  @Override
  public double add(double a, double b) {
    return a + b;
  }

  @Override
  public double subtract(double a, double b) {
    return a - b;
  }

  @Override
  public double multiply(double a, double b) {
    return a * b;
  }

  @Override
  public double divide(double a, double b) {
    return a / b;
  }
}
