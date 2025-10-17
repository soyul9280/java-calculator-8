package controller;

import model.Calculator;
import view.InputView;

public class CalculatorController {

  public void calculate() {
    init();
  }

  public void init() {
    InputView view = new InputView();
    String inputedString = view.inputString();

    Calculator calculator = new Calculator.CalculatorBuilder()
        .separator(",")
        .separator(":")
        .build();
  }
}
