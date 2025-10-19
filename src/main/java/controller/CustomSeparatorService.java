package controller;

public class CustomSeparatorService {
    private CustomSeparatorService() {
    }

    public static String getCustomSeparator(String inputedString) {
        int start = inputedString.indexOf("//");

        if (!inputedString.startsWith("\\n", start + 3)) {
            throw new IllegalArgumentException("커스텀 구분자 지정 명령어가 잘못되었습니다.");
        }

        int end = inputedString.indexOf("\\n");
        String customSeparator = inputedString.substring(start + 2, end);

        return customSeparator;
    }
}


