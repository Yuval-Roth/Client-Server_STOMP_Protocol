package bgu.spl.net.impl.stomp.Backend.utils;

import java.util.Arrays;

public class Tuple<T>{

    private final T[] values;

    public Tuple(T...values) {
        this.values = values;
    }
    public T get(int index) {
        return values[index];
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.deepHashCode(values);
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Tuple<T> other = (Tuple<T>) obj;
        if (!Arrays.deepEquals(values, other.values))
            return false;
        return true;
    }
}