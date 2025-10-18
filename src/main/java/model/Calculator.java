package model;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
    private List<String> separators;
    private List<Integer> operands;

    private Calculator(CalculatorBuilder builder) {
        this.separators = builder.separators;
        this.operands = builder.operands;
    }

    public int plus() {
        int result = 0;
        for (Integer operand : operands) {
            if (operand < 0) {
                throw new IllegalArgumentException("양의 정수만 입력 가능합니다. 잘못된 숫자: " + operand);
            }
            result += operand;
        }
        return result;
    }

    public void addSeparator(String separator) {
        this.separators.add(separator);
    }

    public void addOperand(int operand) {
        this.operands.add(operand);
    }

    public static class CalculatorBuilder {
        private List<String> separators = new ArrayList<>();
        private List<Integer> operands = new ArrayList<>();

        public CalculatorBuilder separator(String separator) {
            this.separators.add(separator);
            return this;
        }

        public CalculatorBuilder operand(int operand) {
            this.operands.add(operand);
            return this;
        }

        public Calculator build() {
            return new Calculator(this);
        }
    }

}
