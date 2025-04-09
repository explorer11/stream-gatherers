package org.example;

import java.util.List;

public class StreamExample {

    private StreamExample() {
        throw new UnsupportedOperationException();
    }

    public static <T, Z> List<Pair<T,Z>> crossWith(List<T> list1, List<Z> list2) {
        return list1.stream().flatMap(m -> list2.stream().map(z -> Pair.of(m, z))).toList();
    }

    public static List<String> distinctByLength(List<String> list) {
        return list.stream()
                .map(DistinctByLength::new)
                .distinct()
                .map(DistinctByLength::str)
                .toList();
    }

    public static int selectOne(List<Integer> list) {
        int state = -1;
        for (int i=0; i < list.size() - 2; i++) {
            Integer item = list.get(i);
            Integer next = list.get(i + 1);
            state = item;
            if(state == next) {
                return state;
            }
        }
        return state;
    }

    private static class DistinctByLength {

        private final String str;

        private DistinctByLength(String str) {
            this.str = str;
        }

        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (! (o instanceof DistinctByLength)) return false;
            return str.length() == (((DistinctByLength) o).str).length();
        }

        @Override public int hashCode() {
            return str == null ? 0 : Integer.hashCode(str.length());
        }

        public String str() {
            return str;
        }
    }
}
