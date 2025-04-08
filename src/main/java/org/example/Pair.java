package org.example;

import java.util.Objects;

public class Pair<T,Z> {

    private final T t;
    private final Z z;

    private Pair(T t, Z z) {
        this.t = t;
        this.z = z;
    }

    public static <T,Z> Pair<T,Z> of(T t, Z z) {
        return new Pair<>(t, z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (! (o instanceof Pair)) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(t, pair.t) && Objects.equals(z, pair.z);
    }

    @Override
    public int hashCode() {
        return Objects.hash(t, z);
    }

    @Override
    public String toString() {
        return "Pair{" +
                "t=" + t +
                ", z=" + z +
                '}';
    }
}
