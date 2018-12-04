package com.bosowski.calculator.server;

import com.bosowski.calculator.CalculatorServerListener;
import javax.swing.*;
import java.rmi.RemoteException;

public class CalculatorServerClient extends JFrame implements CalculatorServerListener {

  private JTextArea systemLog;

  private CalculatorServerClient() throws RemoteException {
    new CalculatorServer(this);

    systemLog = new JTextArea();
    systemLog.setEditable(false);
    systemLog.append("Server ready.");
    add(systemLog);

    //Typical JFrame settings.
    setTitle("Calculator Server");
    setSize(600, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  public static void main(String args[]){
    try {
      new CalculatorServerClient();
    } catch (RemoteException e) {
      System.out.println("Error while trying to instantiate " + CalculatorServer.class.getName() + ": " + e.getMessage());
      e.printStackTrace();
    }
  }

  @Override
  public void getNotified(String message) {
    systemLog.append("\n"+message);
  }
}
