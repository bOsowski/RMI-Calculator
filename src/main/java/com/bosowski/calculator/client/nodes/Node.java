package com.bosowski.calculator.client.nodes;

import java.rmi.RemoteException;

/**
 * @author bosowski
 * created on 4/12/2018
 * This is a base interface from which all nodes will inherit.
 */
public interface Node {

  /**
   * Evaluates depending on the node type.
   * @return the evaluation result
   * @throws RemoteException when the remote
   * method invocation fails.
   */
  double evaluate() throws RemoteException;
}
