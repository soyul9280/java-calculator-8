package controller;

import java.util.List;
import model.Calculator;
import view.InputView;

public class CalculatorController {

    public int calculate() {
        Calculator calculator = init();
        List<Integer> numberList = calculator.getOperands();
        int result = 0;
        for (Integer operand : numberList) {
            result = result + operand;
        }
        return result;
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

        for (String s : splitStrings) {
            int num = Integer.parseInt(s);
            calculator.addOperand(num);
        }
        return calculator;
    }

}
