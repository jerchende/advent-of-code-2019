package net.erchen.adventofcode2019.day06;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OrbitMapTest {

    @SneakyThrows
    static List<String> sampleInput() {
        return Files.readAllLines(Path.of("src/test/resources/day06/sample.txt"));
    }

    @SneakyThrows
    static List<String> sample2Input() {
        return Files.readAllLines(Path.of("src/test/resources/day06/sample2.txt"));
    }

    @SneakyThrows
    static List<String> solutionInput() {
        return Files.readAllLines(Path.of("src/test/resources/day06/input.txt"));
    }

    @Test
    void shouldCountAllOrbits() {
        var orbitMap = new OrbitMap(sampleInput());

        assertThat(orbitMap.countAllOrbits()).isEqualTo(42);
    }

    @Test
    void shouldCountAllOrbits_Solution() {
        var orbitMap = new OrbitMap(solutionInput());

        assertThat(orbitMap.countAllOrbits()).isEqualTo(204521);
    }

    @Test
    void shouldCountOrbitTransfers() {
        var orbitMap = new OrbitMap(sample2Input());

        assertThat(orbitMap.countOrbitTransfers()).isEqualTo(4);
    }

    @Test
    void shouldCountOrbitTransfers_Solution() {
        var orbitMap = new OrbitMap(solutionInput());

        assertThat(orbitMap.countOrbitTransfers()).isEqualTo(307);
    }
}