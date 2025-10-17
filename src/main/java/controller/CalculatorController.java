package controller;

import java.util.List;
import model.Calculator;
import view.InputView;
import view.OutView;

public class CalculatorController {

    public void calculate() {
        Calculator calculator = init();
        List<Integer> numberList = calculator.getOperands();
        int result = 0;
        for (Integer operand : numberList) {
            result = result + operand;
        }
        OutView outView = new OutView();
        outView.outputString(result);
    }

    public Calculator init() {
        InputView view = new InputView();
        String inputedString = view.inputString();
        String customSeparator = "";

        Calculator calculator = new Calculator.CalculatorBuilder()
                .separator(",")
                .separator(":")
                .operand(0)
                .build();

        if (inputedString.startsWith("//")) {
            customSeparator = inputedString.substring(2, 3);
            calculator.addSeparator(customSeparator);
        }

        inputedString = inputedString.replaceAll(":", ",");
        inputedString = inputedString.replaceAll(customSeparator, ",");
        String[] splitStrings = inputedString.split(",");

        for (String s : splitStrings) {
            int num = Integer.parseInt(s);
            calculator.addOperand(num);
        }
        return calculator;
    }

}
