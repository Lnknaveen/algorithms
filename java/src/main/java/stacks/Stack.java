package stacks;

import java.util.ArrayList;
import java.util.List;

public class Stack<T> {
    List<T> list = new ArrayList<T>();

    public void push(T value) {
        list.add(value);
    }

    public T peek() {
        return list.get(list.size() - 1);
    }

    public int size() {
        return list.size();
    }

    public T pop() {
        T returnValue = null;

        T value = list.get(list.size() - 1);
        if (list.remove(value)) {
            returnValue = value;
        }

        return returnValue;
    }

}