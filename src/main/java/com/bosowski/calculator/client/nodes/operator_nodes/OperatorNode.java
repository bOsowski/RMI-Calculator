package com.bosowski.calculator.client.nodes.operator_nodes;

import com.bosowski.calculator.Calculator;
import com.bosowski.calculator.client.nodes.Node;

/**
 * @author bosowski
 * created on 4/12/2018
 * This abstract node class will be extended for each operation type.
 */
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
