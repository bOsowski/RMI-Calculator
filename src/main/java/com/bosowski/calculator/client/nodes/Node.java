package com.bosowski.calculator.client.nodes;

import java.rmi.RemoteException;

public interface Node {
  double evaluate() throws RemoteException;
}
