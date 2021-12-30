package net.erchen.adventofcode2019.day09;

import lombok.SneakyThrows;
import net.erchen.adventofcode2019.common.IntCodeProgramm;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SensorBoost {

    @SneakyThrows
    static String solutionInput() {
        return Files.readString(Path.of("src/test/resources/day09/input.txt")).trim();
    }

    @Test
    void shouldGetKeyCode() {
        var intCodeProgramm = IntCodeProgramm.fromInput(solutionInput());

        List<Long> output = new LinkedList<>();
        intCodeProgramm.execute(() -> 1L, output::add);

        assertThat(output).containsExactly(3546494377L);
    }

    @Test
    void shouldGetCoordinates() {
        var intCodeProgramm = IntCodeProgramm.fromInput(solutionInput());

        List<Long> output = new LinkedList<>();
        intCodeProgramm.execute(() -> 2L, output::add);

        assertThat(output).containsExactly(47253L);
    }
}
