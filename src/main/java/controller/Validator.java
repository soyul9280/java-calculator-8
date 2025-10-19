package controller;

public class Validator {
    private Validator() {
    }

    public static String validate(String inputedString) {

        if (isEmpty(inputedString)) {
            return "";
        }

        if (isNumber(inputedString)) {
            throw new IllegalArgumentException("구분자를 입력해주세요. 현재: " + inputedString);
        }

        if (!inputedString.startsWith("//") && !isNumber(inputedString.substring(0, 1))) {
            throw new IllegalArgumentException("문자열 선언이 잘못되었습니다. // 혹은 숫자로 시작 가능합니다. " + inputedString);
        }
        return inputedString;
    }

    public static boolean isEmpty(String inputedString) {
        return inputedString == null || inputedString.isEmpty();
    }

    public static boolean isNumber(String startValue) {
        try {
            Integer.parseInt(startValue);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}
