package com.bosowski.calculator.client.nodes.operator_nodes;

import com.bosowski.calculator.Calculator;
import com.bosowski.calculator.client.nodes.Node;

public abstract class OperatorNode implements Node {
  protected Node left;
  protected Node right;

  protected Calculator calculator;

  public OperatorNode(Node left, Node right, Calculator calculator){
    this.left = left;
    this.right = right;
    this.calculator = calculator;
  }
}
