package woowacourse.shoppingcart.domain.customer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NicknameTest {

    @DisplayName("닉네임에 null 을 입력하면 안된다.")
    @Test
    void nicknameNullException() {
        // when & then
        assertThatThrownBy(() -> new Nickname(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("닉네임을 입력해주세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("닉네임에 빈값을 입력하면 안된다.")
    void nicknameBlankException(String nickname) {
        // when & then
        assertThatThrownBy(() -> new Nickname(nickname))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("닉네임을 입력해주세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "aaaaaaaaaaa", "!@#$"})
    @DisplayName("닉네임이 영문, 한글, 숫자를 조합하여 2 ~ 10 자가 아니면 안된다.")
    void nicknameFormatException(String nickname) {
        // when & then
        assertThatThrownBy(() -> new Nickname(nickname))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("닉네임은 영문, 한글, 숫자를 조합하여 2 ~ 10 자를 입력해주세요.");
    }
}
