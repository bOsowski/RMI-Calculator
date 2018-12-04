package com.bosowski.calculator.client.nodes.operator_nodes;

import com.bosowski.calculator.client.nodes.Node;

public class ValueNode implements Node {

  double value;

  public ValueNode(double value){
    this.value = value;
  }

  @Override
  public double evaluate() {
    return value;
  }
}
