package controller;

public class CustomSeparatorService {
    private CustomSeparatorService() {
    }

    public static String getCustomSeparator(String inputedString) {
        int start = inputedString.indexOf("//");
        int end = inputedString.indexOf("\\n");
        String customSeparator = inputedString.substring(start + 1, end);

        if (!inputedString.startsWith("\\n", end)) {
            throw new IllegalArgumentException("커스텀 구분자 지정 명령어가 잘못되었습니다.");
        }

        return customSeparator;
    }
}


