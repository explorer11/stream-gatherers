package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StreamExampleTest {

    @Test
    void shouldCrossWith() {
        assertThat(
                StreamExample.crossWith(List.of("A", "B", "C"), List.of(1, 2, 3)))
                .containsExactly(Pair.of("A", 1), Pair.of("A", 2), Pair.of("A", 3),
                        Pair.of("B", 1), Pair.of("B", 2), Pair.of("B", 3),
                        Pair.of("C", 1), Pair.of("C", 2), Pair.of("C", 3));

    }

    @Test
    void shouldDistinctByLength() {
        assertThat(
                StreamExample.distinctByLength(List.of("red", "blue", "green", "white")))
                .containsExactly("red", "blue", "green");
    }

    @Test
    void shouldDistinctByLengthGather() {
        assertThat(
                StreamExample.distinctByLengthGather(List.of("red", "blue", "green", "white")))
                .containsExactly("red", "blue", "green");
    }
}