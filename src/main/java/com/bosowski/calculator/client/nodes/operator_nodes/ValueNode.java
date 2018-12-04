package com.bosowski.calculator.client.nodes.operator_nodes;

import com.bosowski.calculator.client.nodes.Node;

/**
 * @author bosowski
 * created on 4/12/2018
 * This class holds a value which evaluates itself.
 */
public class ValueNode implements Node {

  private double value;

  public ValueNode(double value){
    this.value = value;
  }

  @Override
  public double evaluate() {
    return value;
  }
}
