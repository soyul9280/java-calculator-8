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
            if (operand < 0) {
                throw new IllegalArgumentException("양의 정수만 입력 가능합니다. 잘못된 숫자: " + operand);
            }
            result = result + operand;
        }
        OutView outView = new OutView();
        outView.outputString(result);
    }

    public Calculator init() {
        InputView view = new InputView();
        String inputedString = view.inputString();
        String customSeparator;

        Calculator calculator = new Calculator.CalculatorBuilder()
                .separator(",")
                .separator(":")
                .operand(0)
                .build();
        if (inputedString.equals("")) {
            return calculator;
        }

        if (isNumber(inputedString)) {
            throw new IllegalArgumentException("구분자를 입력해주세요. 현재: " + inputedString);
        }

        if (!inputedString.startsWith("//") && !isNumber(inputedString.substring(0, 1))) {
            throw new IllegalArgumentException("문자열 선언이 잘못되었습니다. // 혹은 숫자로 시작 가능합니다. " + inputedString);
        }

        if (inputedString.startsWith("//")) {
            int start = inputedString.indexOf("//");
            int end = inputedString.indexOf("\\n");
            customSeparator = inputedString.substring(start + 1, end);

            if (customSeparator.length() > 2) {
                throw new IllegalArgumentException(
                        "커스텀 구분자의 길이는 1이어야합니다. 현재 구분자 길이: " + customSeparator.length());
            }

            if (!inputedString.startsWith("\\n", end)) {
                throw new IllegalArgumentException("커스텀 구분자 지정 명령어가 잘못되었습니다.");
            }

            if (isNumber(customSeparator)) {
                throw new IllegalArgumentException("커스텀 구분자는 숫자가 될 수 없습니다: " + customSeparator);
            }

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

    public boolean isNumber(String startValue) {
        try {
            if (startValue.equals("")) {
                return true;
            }

            Integer.parseInt(startValue);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
