package main.client;

import main.Calculator;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class CalculatorClient {

  private static Calculator calculator;

  private CalculatorClient() throws RemoteException, NotBoundException, MalformedURLException {
    calculator = (Calculator) Naming.lookup("Calculator");
  }

  public static void main(String args[]){
    try {
      new CalculatorClient();
    } catch (RemoteException | NotBoundException | MalformedURLException e) {
      System.out.println("Exception while trying to instantiate calculator from remote object: " + e.getMessage());
      e.printStackTrace();
    }
  }

}
