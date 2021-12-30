package net.erchen.adventofcode2019.day05;

import lombok.SneakyThrows;
import net.erchen.adventofcode2019.common.IntCodeProgramm;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;

class ThermalDiagnosticTest {

    @SneakyThrows
    static String solutionInput() {
        return Files.readAllLines(Path.of("src/test/resources/day05/input.txt")).get(0);
    }

    @Test
    void shouldRunDiagnosticProgramm() {
        var intCodeProgramm = IntCodeProgramm.fromInput(solutionInput());
        var output = new LinkedList<>();
        intCodeProgramm.execute(() -> 1L, output::add);

        assertThat(output).containsExactly(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 15314507L);
    }

    @Test
    void shouldRunDiagnosticProgrammForSystem5() {
        var intCodeProgramm = IntCodeProgramm.fromInput(solutionInput());
        var output = new LinkedList<>();
        intCodeProgramm.execute(() -> 5L, output::add);

        assertThat(output).containsExactly(652726L);
    }
}