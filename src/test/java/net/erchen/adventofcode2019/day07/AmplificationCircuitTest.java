package net.erchen.adventofcode2019.day07;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AmplificationCircuitTest {

    @SneakyThrows
    static String solutionInput() {
        return Files.readString(Path.of("src/test/resources/day07/input.txt")).trim();
    }

    @Test
    void shouldCalculateThrusterSignalA() {

        var amplificationCircuit = new AmplificationCircuit("3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0");

        assertThat(amplificationCircuit.thrusterSignal(List.of(4L, 3L, 2L, 1L, 0L))).isEqualTo(43210L);
    }

    @Test
    void shouldCalculateThrusterSignalB() {

        var amplificationCircuit = new AmplificationCircuit("3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0");

        assertThat(amplificationCircuit.thrusterSignal(List.of(0L, 1L, 2L, 3L, 4L))).isEqualTo(54321L);
    }

    @Test
    void shouldCalculateThrusterSignalC() {

        var amplificationCircuit = new AmplificationCircuit("3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0");

        assertThat(amplificationCircuit.thrusterSignal(List.of(1L, 0L, 4L, 3L, 2L))).isEqualTo(65210L);
    }

    @Test
    void shouldGetPermutations2() {
        var permutations = AmplificationCircuit.permutations(List.of(1L, 2L));

        assertThat(permutations).containsExactlyInAnyOrder(List.of(1L, 2L), List.of(2L, 1L));
    }

    @Test
    void shouldGetPermutations3() {
        var permutations = AmplificationCircuit.permutations(List.of(1L, 2L, 3L));

        assertThat(permutations).containsExactlyInAnyOrder(List.of(1L, 2L, 3L), List.of(1L, 3L, 2L), List.of(2L, 1L, 3L), List.of(2L, 3L, 1L), List.of(3L, 1L, 2L), List.of(3L, 2L, 1L));
    }

    @Test
    void shouldGetPermutations5() {
        var permutations = AmplificationCircuit.permutations(List.of(1L, 2L, 3L, 4L, 5L));

        assertThat(permutations).hasSize(120);
    }

    @Test
    void shouldCalculateMaxThrusterSignal() {

        var amplificationCircuit = new AmplificationCircuit("3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0");

        assertThat(amplificationCircuit.maxThrusterSignal(false)).isEqualTo(65210);
    }

    @Test
    void shouldCalculateMaxThrusterSignal_Solution() {

        var amplificationCircuit = new AmplificationCircuit(solutionInput());

        assertThat(amplificationCircuit.maxThrusterSignal(false)).isEqualTo(206580);
    }

    @Test
    void shouldCalculateMaxThrusterSignal_Solution_Feedback() {

        var amplificationCircuit = new AmplificationCircuit(solutionInput());

        assertThat(amplificationCircuit.maxThrusterSignal(true)).isEqualTo(2299406);
    }

}