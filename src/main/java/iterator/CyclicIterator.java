package iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class CyclicIterator<T> implements Iterator<T> {

    private final List<T> data;
    private int index = 0;

    public CyclicIterator(List<T> data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return !data.isEmpty();
    }

    @Override
    public T next() {
        if (data.isEmpty()) {
            throw new NoSuchElementException("No elements in the iterator");
        }
        T value = data.get(index);
        index = (index + 1) % data.size();
        return value;
    }
}