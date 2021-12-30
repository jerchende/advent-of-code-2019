package net.erchen.adventofcode2019.common;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class IntCodeProgrammTest {

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

        intCodeProgramm.execute(() -> null, x -> {
        });

        assertThat(intCodeProgramm.toString()).isEqualTo(output);
    }

    @Test
    void shouldPassInputAndOutput() {
        var intCodeProgramm = IntCodeProgramm.fromInput("3,0,4,0,99");

        List<Long> output = new LinkedList<>();
        intCodeProgramm.execute(() -> 42L, output::add);

        assertThat(output).containsExactly(42L);
    }

    @Test
    void shouldSupportParametersInRelativeModeA() {
        var intCodeProgramm = IntCodeProgramm.fromInput("109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99");

        List<Long> output = new LinkedList<>();
        intCodeProgramm.execute(() -> null, output::add);

        assertThat(output).containsExactly(109L, 1L, 204L, -1L, 1001L, 100L, 1L, 100L, 1008L, 100L, 16L, 101L, 1006L, 101L, 0L, 99L);
    }

    @Test
    void shouldSupportLargeNumbersInOutput() {
        var intCodeProgramm = IntCodeProgramm.fromInput("1102,34915192,34915192,7,4,7,99,0");

        List<Long> output = new LinkedList<>();
        intCodeProgramm.execute(() -> null, output::add);

        assertThat(output).containsExactly(1219070632396864L);
    }

    @Test
    void shouldSupportLargeNumbersInInput() {
        var intCodeProgramm = IntCodeProgramm.fromInput("104,1125899906842624,99");

        List<Long> output = new LinkedList<>();
        intCodeProgramm.execute(() -> null, output::add);

        assertThat(output).containsExactly(1125899906842624L);
    }
}