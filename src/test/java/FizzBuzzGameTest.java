import fizzbuzz.FizzBuzzGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FizzBuzzGameTest {
    private FizzBuzzGame game;

    @BeforeEach
    void setUp() {
        game = new FizzBuzzGame();
    }

    @Test
    void whenFizzBuzzThenCorrectAnswer() {
        String expected = game.getExpectedAnswer(15);
        assertThat(expected).isEqualTo("FizzBuzz");
    }

    @Test
    void whenFizzThenCorrectAnswer() {
        String expected = game.getExpectedAnswer(9);
        assertThat(expected).isEqualTo("Fizz");
    }

    @Test
    void whenBuzzThenCorrectAnswer() {
        String expected = game.getExpectedAnswer(10);
        assertThat(expected).isEqualTo("Buzz");
    }

    @Test
    void whenNumberThenCorrectAnswer() {
        String expected = game.getExpectedAnswer(7);
        assertThat(expected).isEqualTo("7");
    }
}
