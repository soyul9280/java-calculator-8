package controller;

import model.Calculator;
import view.InputView;

public class CalculatorController {

    public void calculate() {
        Calculator calculator = init();
    }

    public Calculator init() {
        InputView view = new InputView();
        String inputedString = view.inputString();
        inputedString = inputedString.replaceAll(":", ",");
        String[] splitStrings = inputedString.split(",");

        Calculator calculator = new Calculator.CalculatorBuilder()
                .separator(",")
                .separator(":")
                .operand(0)
                .build();

        return calculator;
    }

}
