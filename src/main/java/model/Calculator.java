package model;

import controller.Validator;
import java.util.ArrayList;
import java.util.List;

public class Calculator {
    private final List<String> separators;
    private final List<Long> operands;

    private Calculator(CalculatorBuilder builder) {
        this.separators = builder.separators;
        this.operands = builder.operands;
    }

    public long plus() {
        long result = 0L;
        for (Long operand : operands) {
            if (operand < 0) {
                throw new IllegalArgumentException("양의 정수만 입력 가능합니다. 잘못된 숫자: " + operand);
            }
            result += operand;
        }
        return result;
    }

    public void addSeparator(String separator) {
        if (separator.length() > 2) {
            throw new IllegalArgumentException(
                    "커스텀 구분자의 길이는 1이어야합니다. 현재 구분자 길이: " + separator.length());
        }

        if (Validator.isNumber(separator)) {
            throw new IllegalArgumentException("커스텀 구분자는 숫자가 될 수 없습니다: " + separator);
        }
        this.separators.add(separator);
    }

    public void addOperand(long operand) {
        if (operand < 0) {
            throw new IllegalArgumentException("양의 정수만 가능합니다. 현재: " + operand);
        }
        this.operands.add(operand);
    }

    public static class CalculatorBuilder {
        private final List<String> separators = new ArrayList<>();
        private final List<Long> operands = new ArrayList<>();

        public CalculatorBuilder separator(String separator) {
            this.separators.add(separator);
            return this;
        }

        public CalculatorBuilder operand(long operand) {
            this.operands.add(operand);
            return this;
        }

        public Calculator build() {
            return new Calculator(this);
        }
    }

}
