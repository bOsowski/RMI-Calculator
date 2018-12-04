package com.bosowski.calculator.client;

import com.bosowski.calculator.server.CalculatorServer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorEngineTest {

  CalculatorEngine client;

  @BeforeAll
  static void launchServer() throws Exception {
    new CalculatorServer();
  }

  @BeforeEach
  void setUp() throws RemoteException, NotBoundException, MalformedURLException {
    client = new CalculatorEngine();
  }

  @Test
  void testBasicEvaluation() throws RemoteException {
    assertEquals(8, client.evaluate(3,5, CalculatorEngine.Operator.ADD), 0.1);
    assertEquals(2, client.evaluate(5,3, CalculatorEngine.Operator.SUBTRACT), 0.1);
    assertEquals(15, client.evaluate(3,5, CalculatorEngine.Operator.MULTIPLY), 0.1);
    assertEquals(5, client.evaluate(15, 3, CalculatorEngine.Operator.DIVIDE), 0.1);
  }
}
