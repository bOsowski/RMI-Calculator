package com.bosowski.calculator.server;


import com.bosowski.calculator.Calculator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorServerTest extends JFrame {

  private Calculator calculator;

  @BeforeAll
  static void launchServer() throws Exception {
    new CalculatorServer();
  }

  @BeforeEach
  void setUp() throws RemoteException, NotBoundException, MalformedURLException {
    calculator = (Calculator) Naming.lookup("com.bosowski.calculator.Calculator");
  }

  @Test
  void testAdd() throws RemoteException {
    assertEquals(1, calculator.add(0.5, 0.5), 0.1);
  }

  @Test
  void testSubtract() throws RemoteException {
    assertEquals(1, calculator.subtract(2,1), 0.1);
  }

  @Test
  void testMultiply() throws RemoteException {
    assertEquals(4, calculator.multiply(2,2), 0.1);
  }

  @Test
  void testDivide() throws RemoteException {
    assertEquals(1, calculator.divide(2,2), 0.1);
  }
}