package net.erchen.adventofcode2019.day02;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class IntCodeProgrammTest {

    @SneakyThrows
    static String solutionInput() {
        return Files.readAllLines(Path.of("src/test/resources/day02/input.txt")).get(0);
    }

    @ParameterizedTest
    @CsvSource(delimiterString = "#", value = {
            "1,9,10,3,2,3,11,0,99,30,40,50#3500,9,10,70,2,3,11,0,99,30,40,50",
            "1,0,0,0,99#2,0,0,0,99",
            "2,3,0,3,99#2,3,0,6,99",
            "2,4,4,5,99,0#2,4,4,5,99,9801",
            "1,1,1,4,99,5,6,0,99#30,1,1,4,2,5,6,0,99"

    })
    void shouldRunProgramm(String input, String output) {
        var intCodeProgramm = IntCodeProgramm.fromInput(input);

        intCodeProgramm.execute();

        assertThat(intCodeProgramm.toString()).isEqualTo(output);
    }

    @Test
    void shouldRestoreGravityAssistProgramm() {
        var intCodeProgramm = IntCodeProgramm.fromInput(solutionInput());
        intCodeProgramm.getProgram()[1] = 12;
        intCodeProgramm.getProgram()[2] = 2;

        intCodeProgramm.execute();
        assertThat(intCodeProgramm.getProgram()[0]).isEqualTo(6327510);
    }

    @Test
    void shouldFindNounAndVerb() {
        for (int noun = 0; noun <= 99; noun++) {
            for (int verb = 0; verb <= 99; verb++) {
                var intCodeProgramm = IntCodeProgramm.fromInput(solutionInput());
                intCodeProgramm.getProgram()[1] = noun;
                intCodeProgramm.getProgram()[2] = verb;
                intCodeProgramm.execute();
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