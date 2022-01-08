package net.erchen.adventofcode2019.day14;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class NanofactoryTest {

    @SneakyThrows
    static List<String> solutionInput() {
        return Files.readAllLines(Path.of("src/test/resources/day14/input.txt"));
    }

    @SneakyThrows
    static List<String> sampleInput1() {
        return Files.readAllLines(Path.of("src/test/resources/day14/sample1.txt"));
    }

    @SneakyThrows
    static List<String> sampleInput2() {
        return Files.readAllLines(Path.of("src/test/resources/day14/sample2.txt"));
    }

    @SneakyThrows
    static List<String> sampleInput3() {
        return Files.readAllLines(Path.of("src/test/resources/day14/sample3.txt"));
    }

    @SneakyThrows
    static List<String> sampleInput4() {
        return Files.readAllLines(Path.of("src/test/resources/day14/sample4.txt"));
    }

    @SneakyThrows
    static List<String> sampleInput5() {
        return Files.readAllLines(Path.of("src/test/resources/day14/sample5.txt"));
    }

    @Test
    void shouldParse() {
        var nanofactory = new Nanofactory(sampleInput1());

        assertThat(nanofactory.getReactions()).hasSize(6);
    }

    @Test
    void shouldCalculateFuelConsumption_Sample1() {
        var nanofactory = new Nanofactory(sampleInput1());

        assertThat(nanofactory.calculateOreConsumptionForFuel()).isEqualTo(31);
    }

    @Test
    void shouldCalculateFuelConsumption_Sample2() {
        var nanofactory = new Nanofactory(sampleInput2());

        assertThat(nanofactory.calculateOreConsumptionForFuel()).isEqualTo(165);
    }

    @Test
    void shouldCalculateFuelConsumption_Sample3() {
        var nanofactory = new Nanofactory(sampleInput3());

        assertThat(nanofactory.calculateOreConsumptionForFuel()).isEqualTo(13312);
    }

    @Test
    void shouldCalculateFuelConsumption_Sample4() {
        var nanofactory = new Nanofactory(sampleInput4());

        assertThat(nanofactory.calculateOreConsumptionForFuel()).isEqualTo(180697);
    }

    @Test
    void shouldCalculateFuelConsumption_Sample5() {
        var nanofactory = new Nanofactory(sampleInput5());

        assertThat(nanofactory.calculateOreConsumptionForFuel()).isEqualTo(2210736);
    }

    @Test
    void shouldCalculateFuelConsumption_Solution() {
        var nanofactory = new Nanofactory(solutionInput());

        assertThat(nanofactory.calculateOreConsumptionForFuel()).isEqualTo(532506);
    }
}