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

        List<Integer> output = new LinkedList<>();
        intCodeProgramm.execute(() -> 42, output::add);

        assertThat(output).containsExactly(42);
    }
}