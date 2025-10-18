package calculator;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    @Test
    @DisplayName("정상: 커스텀 구분자")
    void SuccessCustom() {
        assertSimpleTest(() -> {
            run("//;\\n1");
            assertThat(output()).contains("결과 : 1");
        });
    }

    @Test
    @DisplayName("예외: 음의 정수가 들어간 경우")
    void FailByMinus() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("-1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("예외: 숫자만 입력된 경우")
    void FailByOnlyNumber() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("123"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("예외: 커스텀 시작 문자 잘못된 경우 & 구분자만 있는 경우")
    void FailNotMatchStartString() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException(","))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("정상: 아무것도 입력안하고 입력하면 0 반환")
    void SuccessBlack() {
        assertSimpleTest(() -> {
            run("\n");
            assertThat(output()).contains("결과 : 0");
        });
    }

    @Test
    @DisplayName("예외: 구분자 길이가 1초과인 경우")
    void FailByCustomLength() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;[\\n1"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
