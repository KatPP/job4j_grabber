package algo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TreeAVLMapTest {

    @Test
    void whenEmptyTree() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        assertThat(tree.values()).isEmpty();
        assertThat(tree.keySet()).isEmpty();
    }

    @Test
    void whenAddOneElemThenOk() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        assertThat(tree.put(1, "11")).isTrue();
        assertThat(tree.values()).hasSize(1)
                .containsExactly("11");
        assertThat(tree.keySet()).hasSize(1)
                .containsExactly(1);
    }

    @Test
    void whenAddSevenElemThenOk() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        assertThat(tree.put(1, "11")).isTrue();
        assertThat(tree.put(2, "22")).isTrue();
        assertThat(tree.put(3, "33")).isTrue();
        assertThat(tree.put(4, "44")).isTrue();
        assertThat(tree.put(5, "55")).isTrue();
        assertThat(tree.put(6, "66")).isTrue();
        assertThat(tree.put(7, "77")).isTrue();
        assertThat(tree.values()).hasSize(7)
                .containsExactly("11", "22", "33", "44", "55", "66", "77");
        assertThat(tree.keySet()).hasSize(7)
                .containsExactly(1, 2, 3, 4, 5, 6, 7);
    }

    @Test
    void whenGetKeyThenOk() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        tree.put(1, "11");
        tree.put(2, "22");
        tree.put(3, "33");
        tree.put(4, "44");
        tree.put(5, "55");
        tree.put(6, "66");
        tree.put(7, "77");
        assertThat(tree.get(5)).isEqualTo("55");
        assertThat(tree.get(0)).isNull();
    }

    @Test
    void whenAddDuplicateKeyThenUpdateValue() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        assertThat(tree.put(1, "11")).isTrue();
        assertThat(tree.put(1, "111")).isFalse(); // Обновление значения
        assertThat(tree.get(1)).isEqualTo("111");
    }

    @Test
    void whenRemoveRootThenOk() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        tree.put(2, "22");
        tree.put(1, "11");
        tree.put(3, "33");
        assertThat(tree.remove(2)).isTrue(); // Удаление корня
        assertThat(tree.get(2)).isNull();
        assertThat(tree.keySet()).containsExactly(1, 3);
    }

    @Test
    void whenRemoveNonExistentKeyThenReturnFalse() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        tree.put(1, "11");
        tree.put(2, "22");
        assertThat(tree.remove(3)).isFalse(); // Попытка удалить несуществующий ключ
        assertThat(tree.keySet()).containsExactly(1, 2);
    }

    @Test
    void whenRemoveAllThenTreeShouldBeEmpty() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        tree.put(1, "11");
        tree.put(2, "22");
        tree.put(3, "33");
        assertThat(tree.remove(1)).isTrue();
        assertThat(tree.remove(2)).isTrue();
        assertThat(tree.remove(3)).isTrue();
        assertThat(tree.values()).isEmpty();
        assertThat(tree.keySet()).isEmpty();
    }

    @Test
    void whenAddMultipleElementsThenGetShouldReturnCorrectValue() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        tree.put(10, "10");
        tree.put(20, "20");
        tree.put(30, "30");
        assertThat(tree.get(10)).isEqualTo("10");
        assertThat(tree.get(20)).isEqualTo("20");
        assertThat(tree.get(30)).isEqualTo("30");
    }

}
