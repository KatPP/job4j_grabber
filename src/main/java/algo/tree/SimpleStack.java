package algo.tree;

import java.util.Arrays;

public class SimpleStack<T> {
    private Object[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public SimpleStack() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public void push(T value) {
        if (size == elements.length) {
            resize();
        }
        elements[size++] = value;
    }

    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        T value = (T) elements[--size];
        elements[size] = null;
        return value;
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return (T) elements[size - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void resize() {
        elements = Arrays.copyOf(elements, elements.length * 2);
    }
}