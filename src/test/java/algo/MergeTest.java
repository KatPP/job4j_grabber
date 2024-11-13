package algo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MergeTest {

    @Test
    void whenSortedThenOk() {
        int[] array = {10, 4, 6, 4, 8, -13, 2, 3};
        assertThat(Merge.mergesort(array)).containsExactly(-13, 2, 3, 4, 4, 6, 8, 10);
    }

    @Test
    void whenEmptyArrayThenReturnEmpty() {
        int[] array = {};
        assertThat(Merge.mergesort(array)).containsExactly();
    }

    @Test
    void whenSingleElementThenReturnSame() {
        int[] array = {5};
        assertThat(Merge.mergesort(array)).containsExactly(5);
    }

    @Test
    void whenAlreadySortedThenReturnSame() {
        int[] array = {1, 2, 3, 4, 5};
        assertThat(Merge.mergesort(array)).containsExactly(1, 2, 3, 4, 5);
    }

    @Test
    void whenAllElementsAreSameThenReturnSame() {
        int[] array = {7, 7, 7, 7};
        assertThat(Merge.mergesort(array)).containsExactly(7, 7, 7, 7);
    }

    @Test
    void whenNegativeAndPositiveThenSortCorrectly() {
        int[] array = {3, -1, 0, -5, 2};
        assertThat(Merge.mergesort(array)).containsExactly(-5, -1, 0, 2, 3);
    }
}
