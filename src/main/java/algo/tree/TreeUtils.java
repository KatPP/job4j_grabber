package algo.tree;

import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Consumer;

public class TreeUtils<T> {
    /**
     * Метод выполняет обход дерева и считает количество узлов
     *
     * @param root корневой узел дерева
     * @return количество узлов
     * @throws IllegalArgumentException если root является null
     */
    public int countNode(Node<T> root) {
        if (Objects.isNull(root)) {
            throw new IllegalArgumentException();
        }
        int[] count = {0};
        breadthWalk(root, v -> count[0]++);
        return count[0];
    }

    /**
     * Метод выполняет обход дерева и возвращает коллекцию ключей узлов дерева
     *
     * @param root корневой узел
     * @return коллекция с ключами, реализующая интерфейс Iterable<E>
     * @throws IllegalArgumentException если root является null
     */
    public Iterable<T> findAll(Node<T> root) {
        if (Objects.isNull(root)) {
            throw new IllegalArgumentException();
        }
        var result = new ArrayList<T>();
        breadthWalk(root, result::add);
        return result;
    }

    private void breadthWalk(Node<T> root, Consumer<T> consumer) {
        Queue<Node<T>> queue = new SimpleQueue<>();
        queue.push(root);
        while (queue.size() > 0) {
            var visit = queue.poll();
            consumer.accept(visit.getValue());
            visit.getChildren().forEach(queue::push);
        }
    }
}
