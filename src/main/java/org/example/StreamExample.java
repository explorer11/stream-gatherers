package org.example;

import java.util.List;

public class StreamExample {

    public static <T, Z> List<Pair<T,Z>> crossWith(List<T> list1, List<Z> list2) {
        return list1.stream().flatMap(m -> list2.stream().map(z -> Pair.of(m, z))).toList();
    }
}
