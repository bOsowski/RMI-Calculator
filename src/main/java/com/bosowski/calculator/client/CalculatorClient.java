package com.bosowski.calculator.client;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.NumberFormat;

public class CalculatorClient extends JFrame {

  private CalculatorEngine engine;

  private double previousValue;
  private CalculatorEngine.Operator operator;

  private CalculatorClient() throws RemoteException, NotBoundException, MalformedURLException {
    engine = new CalculatorEngine();

    JPanel mainPanel = new JPanel(new GridLayout(3,1 ));
    mainPanel.setSize(getWidth(), getHeight());

    //value input field
    NumberFormat format = NumberFormat.getInstance();
    NumberFormatter formatter = new NumberFormatter(format);
    JFormattedTextField inputField = new JFormattedTextField(formatter);
    inputField.setHorizontalAlignment(SwingConstants.RIGHT);
    Font font1 = new Font("SansSerif", Font.BOLD, 26);
    inputField.setFont(font1);
    mainPanel.add(inputField);

    inputField.addActionListener(a -> {
      if(inputField.getText().matches(".*[A-z]+.*") && !inputField.getText().equals("Infinity")){
        inputField.setText("");
      }
    });

    //buttons
    JPanel gridPanel = new JPanel();
    gridPanel.setLayout(new GridLayout(5,4));
    JButton numberButtons[] = new JButton[11];
    for(int i = 0; i < 10; i++){
      numberButtons[i] = new JButton(Integer.toString(i));
    }
    numberButtons[10] = new JButton(".");

    for(JButton numberButton: numberButtons){
      numberButton.addActionListener(a -> {
        //disallow multiple decimals to be added.
        if(!numberButton.getText().equals(".")  || !inputField.getText().contains(".")){
          inputField.setText(inputField.getText() + numberButton.getText());
        }
      });
    }

    JButton divisionButton = new JButton("/");
    JButton multiplicationButton = new JButton("*");
    JButton subtractionButton = new JButton("-");
    JButton additionButton = new JButton("+");
    JButton submitButton = new JButton("Submit");

    gridPanel.add(divisionButton);
    gridPanel.add(numberButtons[7]);
    gridPanel.add(numberButtons[8]);
    gridPanel.add(numberButtons[9]);

    gridPanel.add(multiplicationButton);
    gridPanel.add(numberButtons[4]);
    gridPanel.add(numberButtons[5]);
    gridPanel.add(numberButtons[6]);

    gridPanel.add(subtractionButton);
    gridPanel.add(numberButtons[1]);
    gridPanel.add(numberButtons[2]);
    gridPanel.add(numberButtons[3]);

    gridPanel.add(additionButton);
    gridPanel.add(numberButtons[0]);
    gridPanel.add(numberButtons[10]);
    gridPanel.add(submitButton);


    JButton clearButton = new JButton("AC");
    clearButton.addActionListener(a -> {
      previousValue = 0;
      inputField.setText("");
      operator = null;
    });

    JButton deleteButton = new JButton("C");
    deleteButton.addActionListener(a -> {
      if(inputField.getText().length() != 0){
        inputField.setText(inputField.getText().substring(0,inputField.getText().length()-1));
      }
    });

    gridPanel.add(clearButton);
    gridPanel.add(deleteButton);

    mainPanel.add(gridPanel);


    //System message
    JTextArea systemLog = new JTextArea("Ready..");
    systemLog.setEditable(false);
    mainPanel.add(systemLog);

    add(mainPanel);

    divisionButton.addActionListener(a -> {
      operator = CalculatorEngine.Operator.DIVIDE;
      previousValue = Double.parseDouble(inputField.getText());
      inputField.setText("");
    });

    multiplicationButton.addActionListener(a -> {
      operator = CalculatorEngine.Operator.MULTIPLY;
      previousValue = Double.parseDouble(inputField.getText());
      inputField.setText("");
    });

    subtractionButton.addActionListener(a -> {
      operator = CalculatorEngine.Operator.SUBTRACT;
      previousValue = Double.parseDouble(inputField.getText());
      inputField.setText("");
    });

    additionButton.addActionListener(a -> {
      operator = CalculatorEngine.Operator.ADD;
      previousValue = Double.parseDouble(inputField.getText());
      inputField.setText("");
    });

    submitButton.addActionListener(a -> {
      try {
        if(operator != null && !inputField.getText().isEmpty()){
          inputField.setText(Double.valueOf(engine.evaluate(previousValue, Double.parseDouble(inputField.getText()), operator)).toString());
        }
      } catch (RemoteException e) {
        systemLog.append("\nInvalid symbol '"+operator+"'.");
      }
    });


    //Typical JFrame settings.
    setTitle("Calculator Client");
    setSize(300, 450);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  public static void main(String args[]){
    try {
      new CalculatorClient();
    } catch (RemoteException | NotBoundException | MalformedURLException e) {
      System.out.println("Exception while trying to instantiate calculator from remote object: " + e.getMessage());
      e.printStackTrace();
    }
  }

}
