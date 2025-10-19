package calculator;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    @Test
    void 커스텀_구분자_사용() {
        assertSimpleTest(() -> {
            run("//;\\n1");
            assertThat(output()).contains("결과 : 1");
        });
    }

    @Test
    void 빈_문자열() {
        assertSimpleTest(() -> {
            run("\n");
            assertThat(output()).contains("결과 : 0");
        });
    }

    @Test
    void 정수가_int_범위_넘는_경우() {
        assertSimpleTest(() -> {
            run("2147483647:1");
            assertThat(output()).contains("결과 : 2147483648");
        });
    }

    @Test
    void 구분자_연속_경우() {
        assertSimpleTest(() -> {
            run("1:2,,3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 구분자가_공백문자_경우() {
        assertSimpleTest(() -> {
            run("// \\n1 2 3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 마지막_문자가_구분자_경우() {
        assertSimpleTest(() -> {
            run("1,2,3,");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("-1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_숫자만_입력() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("123"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_중간에_공백문자_입력() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1,2: 3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_커스텀_선언_잘못된_끝() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//t//1,2t3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_커스텀_잘못된_시작() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("/t\\n1:2t3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_구분자만_있는_경우() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException(",,"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_구분자_길이() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;[\\n1"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_숫자가_구분자() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//2\\n122"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_선언되지_않은_구분자() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//t\\n1:2s3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_문자열길이_3미만() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1:"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
