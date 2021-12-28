package net.erchen.adventofcode2019.day05;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class IntCodeProgrammTest {

    @SneakyThrows
    static String solutionInput() {
        return Files.readAllLines(Path.of("src/test/resources/day05/input.txt")).get(0);
    }

    @ParameterizedTest
    @CsvSource(delimiterString = "#", value = {
            "1,9,10,3,2,3,11,0,99,30,40,50#3500,9,10,70,2,3,11,0,99,30,40,50",
            "1,0,0,0,99#2,0,0,0,99",
            "2,3,0,3,99#2,3,0,6,99",
            "2,4,4,5,99,0#2,4,4,5,99,9801",
            "1,1,1,4,99,5,6,0,99#30,1,1,4,2,5,6,0,99",
            "1002,4,3,4,33#1002,4,3,4,99",
            "1101,100,-1,4,0#1101,100,-1,4,99"

    })
    void shouldRunProgramm(String input, String output) {
        var intCodeProgramm = IntCodeProgramm.fromInput(input);

        intCodeProgramm.execute(List.of());

        assertThat(intCodeProgramm.toString()).isEqualTo(output);
    }

    @Test
    void shouldPassInputAndOutput() {
        var intCodeProgramm = IntCodeProgramm.fromInput("3,0,4,0,99");

        var output = intCodeProgramm.execute(List.of(42));

        assertThat(output).containsExactly(42);
    }

    @Test
    void shouldRunDiagnosticProgramm() {
        var intCodeProgramm = IntCodeProgramm.fromInput(solutionInput());
        var output = intCodeProgramm.execute(List.of(1));

        assertThat(output).containsExactly(0, 0, 0, 0, 0, 0, 0, 0, 0, 15314507);
    }

    @Test
    void shouldRunDiagnosticProgrammForSystem5() {
        var intCodeProgramm = IntCodeProgramm.fromInput(solutionInput());
        var output = intCodeProgramm.execute(List.of(5));

        assertThat(output).containsExactly(652726);
    }
}