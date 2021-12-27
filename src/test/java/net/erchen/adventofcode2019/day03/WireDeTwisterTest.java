package net.erchen.adventofcode2019.day03;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WireDeTwisterTest {

    @SneakyThrows
    static List<String> solutionInput() {
        return Files.readAllLines(Path.of("src/test/resources/day03/input.txt"));
    }

    @Test
    void shouldParseWires() {
        var wireDeTwister = WireDeTwister.fromInput(List.of("R8,U5,L5,D3"));

        assertThat(wireDeTwister.getWires()).hasSize(21);
    }

    @ParameterizedTest
    @CsvSource(delimiterString = "#", value = {
            "R8,U5,L5,D3#U7,R6,D4,L4#6",
            "R75,D30,R83,U83,L12,D49,R71,U7,L72#U62,R66,U55,R34,D71,R55,D58,R83#159",
            "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51#U98,R91,D20,R16,D67,R40,U7,R15,U6,R7#135"

    })
    void shouldFindNearestCrossing(String input1, String input2, int expected) {
        var wireDeTwister = WireDeTwister.fromInput(List.of(input1, input2));

        assertThat(wireDeTwister.closestCrossing()).isEqualTo(expected);
    }

    @Test
    void shouldFindNearestCrossing_Solution() {
        var wireDeTwister = WireDeTwister.fromInput(solutionInput());

        assertThat(wireDeTwister.closestCrossing()).isEqualTo(1064);
    }

    @ParameterizedTest
    @CsvSource(delimiterString = "#", value = {
            "R8,U5,L5,D3#U7,R6,D4,L4#30",
            "R75,D30,R83,U83,L12,D49,R71,U7,L72#U62,R66,U55,R34,D71,R55,D58,R83#610",
            "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51#U98,R91,D20,R16,D67,R40,U7,R15,U6,R7#410"

    })
    void shouldFindLessStepsWire(String input1, String input2, int expected) {
        var wireDeTwister = WireDeTwister.fromInput(List.of(input1, input2));

        assertThat(wireDeTwister.lessStepsCrossing()).isEqualTo(expected);
    }

    @Test
    void shouldFindLessStepsWire_Solution() {
        var wireDeTwister = WireDeTwister.fromInput(solutionInput());

        assertThat(wireDeTwister.lessStepsCrossing()).isEqualTo(25676);
    }
}