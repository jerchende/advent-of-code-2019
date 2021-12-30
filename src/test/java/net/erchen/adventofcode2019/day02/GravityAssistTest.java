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
        intCodeProgramm.getProgram()[1] = 12;
        intCodeProgramm.getProgram()[2] = 2;

        intCodeProgramm.execute(() -> null, x -> {
        });
        assertThat(intCodeProgramm.getProgram()[0]).isEqualTo(6327510);
    }

    @Test
    void shouldFindNounAndVerb() {
        for (int noun = 0; noun <= 99; noun++) {
            for (int verb = 0; verb <= 99; verb++) {
                var intCodeProgramm = IntCodeProgramm.fromInput(solutionInput());
                intCodeProgramm.getProgram()[1] = noun;
                intCodeProgramm.getProgram()[2] = verb;
                intCodeProgramm.execute(() -> null, x -> {
                });
                if (intCodeProgramm.getProgram()[0] == 19690720) {
                    assertThat(noun).isEqualTo(41);
                    assertThat(verb).isEqualTo(12);
                    return;
                }
            }

        }
        throw new IllegalStateException("Could not find noun and verb :(");
    }
}