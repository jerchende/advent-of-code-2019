package net.erchen.adventofcode2019.day02;

import lombok.SneakyThrows;
import net.erchen.adventofcode2019.common.IntCodeProgramm;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class GravityAssistTest {

    @SneakyThrows
    static String solutionInput() {
        return Files.readAllLines(Path.of("src/test/resources/day02/input.txt")).get(0);
    }

    @Test
    void shouldRestoreGravityAssistProgramm() {
        var intCodeProgramm = IntCodeProgramm.fromInput(solutionInput());
        intCodeProgramm.getProgram().put(1L, 12L);
        intCodeProgramm.getProgram().put(2L, 2L);

        intCodeProgramm.execute(() -> null, x -> {
        });
        assertThat(intCodeProgramm.getProgram().get(0L)).isEqualTo(6327510L);
    }

    @Test
    void shouldFindNounAndVerb() {
        for (int noun = 0; noun <= 99; noun++) {
            for (int verb = 0; verb <= 99; verb++) {
                var intCodeProgramm = IntCodeProgramm.fromInput(solutionInput());
                intCodeProgramm.getProgram().put(1L, (long) noun);
                intCodeProgramm.getProgram().put(2L, (long) verb);
                intCodeProgramm.execute(() -> null, x -> {
                });
                if (intCodeProgramm.getProgram().get(0L) == 19690720L) {
                    assertThat(noun).isEqualTo(41);
                    assertThat(verb).isEqualTo(12);
                    return;
                }
            }

        }
        throw new IllegalStateException("Could not find noun and verb :(");
    }
}