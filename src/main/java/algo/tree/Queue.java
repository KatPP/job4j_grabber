package algo.tree;

public interface Queue<T> {
    T poll();

    void push(T value);

    int size();
}
