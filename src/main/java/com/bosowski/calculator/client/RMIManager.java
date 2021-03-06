package com.bosowski.calculator.client;

import com.bosowski.calculator.Calculator;
import com.bosowski.calculator.client.nodes.Node;
import com.bosowski.calculator.client.nodes.operator_nodes.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @author bosowski
 * created on 4/12/2018
 *
 * This class calls remote methods on the bound calculator object,
 * unless the lookup fails. It will then throw an exception which
 * needs to be handled by the instantiator.
 */
public class RMIManager {

  private static Calculator calculator;

  RMIManager() throws RemoteException, NotBoundException, MalformedURLException {
    calculator = (Calculator) Naming.lookup("com.bosowski.calculator.Calculator");
  }

  enum Operator{
    MULTIPLY ,DIVIDE, ADD, SUBTRACT
  }

  double evaluate(double a, double b, Operator operator) throws RemoteException, UnsupportedOperationException {
    Node left = new ValueNode(a);
    Node right = new ValueNode(b);

    switch(operator) {
      case MULTIPLY:
        return new MultiplicationNode(left, right, calculator).evaluate();
      case DIVIDE:
        return new DivisionNode(left, right, calculator).evaluate();
      case ADD:
        return new AdditionNode(left, right, calculator).evaluate();
      case SUBTRACT:
        return new SubtractionNode(left, right, calculator).evaluate();
      default:
        throw new UnsupportedOperationException("The provided Operator is not supported.");
    }
  }

}
