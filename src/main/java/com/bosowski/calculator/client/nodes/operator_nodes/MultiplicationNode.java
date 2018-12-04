package com.bosowski.calculator.client.nodes.operator_nodes;

import com.bosowski.calculator.Calculator;
import com.bosowski.calculator.client.nodes.Node;

import java.rmi.RemoteException;

public class MultiplicationNode extends OperatorNode {

  public MultiplicationNode(Node left, Node right, Calculator calculator) {
    super(left, right, calculator);
  }

  @Override
  public double evaluate() throws RemoteException {
    return calculator.multiply(left.evaluate(), right.evaluate());
  }
}
