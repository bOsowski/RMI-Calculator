package com.bosowski.calculator;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author bosowski
 * created on 4/12/2018
 *
 * Implementing this allows to bind the methods
 * to a remote registery.
 */
public interface Calculator extends Remote {

  double add(double a, double b) throws RemoteException;

  double subtract(double a, double b) throws RemoteException;

  double multiply(double a, double b) throws RemoteException;

  double divide(double a, double b) throws RemoteException;
}