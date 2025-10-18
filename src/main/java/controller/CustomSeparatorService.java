package controller;

public class CustomSeparatorService {
    private CustomSeparatorService() {
    }

    public static String getCustomSeparator(String inputedString) {
        int start = inputedString.indexOf("//");
        int end = inputedString.indexOf("\\n");
        String customSeparator = inputedString.substring(start + 1, end);

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
        return customSeparator;
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


