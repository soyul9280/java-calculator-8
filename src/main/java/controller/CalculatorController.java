package controller;

import model.Calculator;
import view.InputView;
import view.OutView;

public class CalculatorController {

    public void calculate() {
        Calculator calculator = init();
        int result = calculator.plus();
        extract(result);
    }

    public Calculator init() {
        String inputedString = InputView.inputString();

        Calculator calculator = new Calculator.CalculatorBuilder()
                .separator(",")
                .separator(":")
                .operand(0)
                .build();
        if (inputedString.isEmpty()) {
            return calculator;
        }

        if (isNumber(inputedString)) {
            throw new IllegalArgumentException("구분자를 입력해주세요. 현재: " + inputedString);
        }

        if (!inputedString.startsWith("//") && !isNumber(inputedString.substring(0, 1))) {
            throw new IllegalArgumentException("문자열 선언이 잘못되었습니다. // 혹은 숫자로 시작 가능합니다. " + inputedString);
        }

        if (inputedString.startsWith("//")) {
            String customSeparator = CustomSeparatorService.getCustomSeparator(inputedString);
            calculator.addSeparator(customSeparator);
            inputedString = inputedString.substring(5);
            inputedString = inputedString.replaceAll(customSeparator, ",");
        }

        inputedString = inputedString.replaceAll(":", ",");
        String[] splitStrings = inputedString.split(",");

        for (String s : splitStrings) {
            if (!isNumber(s)) {
                throw new IllegalArgumentException("커스텀 문자열은 선언한 뒤 사용해주세요: " + s);
            }
            int num = Integer.parseInt(s);
            calculator.addOperand(num);
        }
        return calculator;
    }


    private static void extract(int result) {
        OutView.outputString(result);
    }

    public static boolean isNumber(String startValue) {
        try {
            if (startValue.isEmpty()) {
                return true;
            }

            Integer.parseInt(startValue);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
