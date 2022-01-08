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

        assertThat(nanofactory.calculateOreConsumptionForFuel(1)).isEqualTo(31);
    }

    @Test
    void shouldCalculateFuelConsumption_Sample2() {
        var nanofactory = new Nanofactory(sampleInput2());

        assertThat(nanofactory.calculateOreConsumptionForFuel(1)).isEqualTo(165);
    }

    @Test
    void shouldCalculateFuelConsumption_Sample3() {
        var nanofactory = new Nanofactory(sampleInput3());

        assertThat(nanofactory.calculateOreConsumptionForFuel(1)).isEqualTo(13312);
    }

    @Test
    void shouldCalculateFuelConsumption_Sample4() {
        var nanofactory = new Nanofactory(sampleInput4());

        assertThat(nanofactory.calculateOreConsumptionForFuel(1)).isEqualTo(180697);
    }

    @Test
    void shouldCalculateFuelConsumption_Sample5() {
        var nanofactory = new Nanofactory(sampleInput5());

        assertThat(nanofactory.calculateOreConsumptionForFuel(1)).isEqualTo(2210736);
    }

    @Test
    void shouldCalculateFuelConsumption_Solution() {
        var nanofactory = new Nanofactory(solutionInput());

        assertThat(nanofactory.calculateOreConsumptionForFuel(1)).isEqualTo(532506);
    }

    @Test
    void shouldCalculateMaximumFuelForOre_Sample3() {
        var nanofactory = new Nanofactory(sampleInput3());

        assertThat(nanofactory.maximumFuelForOre(1000000000000L)).isEqualTo(82892753L);
    }

    @Test
    void shouldCalculateMaximumFuelForOre_Sample4() {
        var nanofactory = new Nanofactory(sampleInput4());

        assertThat(nanofactory.maximumFuelForOre(1000000000000L)).isEqualTo(5586022);
    }

    @Test
    void shouldCalculateMaximumFuelForOre_Sample5() {
        var nanofactory = new Nanofactory(sampleInput5());

        assertThat(nanofactory.maximumFuelForOre(1000000000000L)).isEqualTo(460664);
    }

    @Test
    void shouldCalculateMaximumFuelForOre_Solution() {
        var nanofactory = new Nanofactory(solutionInput());

        assertThat(nanofactory.maximumFuelForOre(1000000000000L)).isEqualTo(2595245);
    }
}