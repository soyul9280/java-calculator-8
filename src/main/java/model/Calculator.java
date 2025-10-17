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

  public static class CalculatorBuilder{
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
