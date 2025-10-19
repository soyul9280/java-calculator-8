package controller;

import model.Calculator;
import view.InputView;
import view.OutView;

public class CalculatorController {

    public void calculate() {
        Calculator calculator = init();
        String validatedString = registerSeparator(calculator);
        registerOperand(validatedString, calculator);
        long result = calculator.plus();
        extract(result);
    }

    public Calculator init() {
        return new Calculator.CalculatorBuilder()
                .separator(",")
                .separator(":")
                .operand(0)
                .build();
    }

    public String registerSeparator(Calculator calculator) {
        String inputedString = InputView.inputString();

        String validatedString = Validator.validate(inputedString);

        if (validatedString.startsWith("//")) {
            String customSeparator = CustomSeparatorService.getCustomSeparator(validatedString);
            calculator.addSeparator(customSeparator);
            validatedString = validatedString.substring(5);
            validatedString = validatedString.replaceAll(customSeparator, ",");
        }

        return validatedString;
    }

    public void registerOperand(String validatedString, Calculator calculator) {

        validatedString = validatedString.replaceAll(":", ",");
        String[] splitStrings = validatedString.split(",");

        for (String s : splitStrings) {
            if (s.isEmpty()) {
                return;
            }

            if (!Validator.isNumber(s)) {
                throw new IllegalArgumentException("커스텀 문자열은 선언한 뒤 사용해주세요: " + s);
            }

            long num = Long.parseLong(s);
            calculator.addOperand(num);
        }
    }


    private static void extract(long result) {
        OutView.outputString(result);
    }


}
