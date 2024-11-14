package algo.tree;

import java.util.NoSuchElementException;

public class SimpleQueue<T> implements Queue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int inSize;
    private int outSize;

    public T poll() {
        if (outSize == 0 && inSize == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        if (outSize == 0) {
            while (inSize > 0) {
                out.push(in.pop());
                inSize--;
                outSize++;
            }
        }
        outSize--;
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        inSize++;
    }

    @Override
    public int size() {
        return inSize + outSize;
    }
}
