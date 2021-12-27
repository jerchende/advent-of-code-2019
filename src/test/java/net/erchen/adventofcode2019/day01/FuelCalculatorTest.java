package net.erchen.adventofcode2019.day01;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FuelCalculatorTest {

    @SneakyThrows
    static List<String> solutionInput() {
        return Files.readAllLines(Path.of("src/test/resources/day01/input.txt"));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "12,2",
            "14,2",
            "1969,654",
            "100756,33583" }
    )
    void shouldCalculateFuel(int input, int expected) {
        assertThat(FuelCalculator.calculateFuel(input)).isEqualTo(expected);
    }

    @Test
    void shouldCalculateFuelForModules() {
        assertThat(FuelCalculator.calculateFuel(List.of("12", "14"))).isEqualTo(4);
    }

    @Test
    void shouldCalculateFuelForModules_Solution() {
        assertThat(FuelCalculator.calculateFuel(solutionInput())).isEqualTo(3334282);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "3,0",
            "12,2",
            "14,2",
            "1969,966",
            "100756,50346" }
    )
    void shouldCalculateFuelRecursive(int input, int expected) {
        assertThat(FuelCalculator.calculateFuelRecursive(input)).isEqualTo(expected);
    }

    @Test
    void shouldCalculateFuelForModulesRecursive_Solution() {
        assertThat(FuelCalculator.calculateFuelRecursive(solutionInput())).isEqualTo(4998585);
    }
}