package org.example;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Gatherer;

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

    public static List<String> distinctByLengthGather(List<String> list) {
        return list.stream()
                .gather(new Gatherer<String, Set<Integer>, String>() {

                    @Override
                    public Supplier<Set<Integer>> initializer() {
                        return HashSet::new;
                    }

                    @Override
                    public Integrator<Set<Integer>, String, String> integrator() {
                        return (state, element, downstream) -> {
                            boolean add = state.add(element.length());
                            if(add) {
                                downstream.push(element);
                            }
                            return true;
                        };
                    }
                })
                .toList();
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
