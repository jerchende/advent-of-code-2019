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

        assertThat(amplificationCircuit.thrusterSignal(List.of(4, 3, 2, 1, 0))).isEqualTo(43210);
    }

    @Test
    void shouldCalculateThrusterSignalB() {

        var amplificationCircuit = new AmplificationCircuit("3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0");

        assertThat(amplificationCircuit.thrusterSignal(List.of(0, 1, 2, 3, 4))).isEqualTo(54321);
    }

    @Test
    void shouldCalculateThrusterSignalC() {

        var amplificationCircuit = new AmplificationCircuit("3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0");

        assertThat(amplificationCircuit.thrusterSignal(List.of(1, 0, 4, 3, 2))).isEqualTo(65210);
    }

    @Test
    void shouldGetPermutations2() {
        var permutations = AmplificationCircuit.permutations(List.of(1, 2));

        assertThat(permutations).containsExactlyInAnyOrder(List.of(1, 2), List.of(2, 1));
    }

    @Test
    void shouldGetPermutations3() {
        var permutations = AmplificationCircuit.permutations(List.of(1, 2, 3));

        assertThat(permutations).containsExactlyInAnyOrder(List.of(1, 2, 3), List.of(1, 3, 2), List.of(2, 1, 3), List.of(2, 3, 1), List.of(3, 1, 2), List.of(3, 2, 1));
    }

    @Test
    void shouldGetPermutations5() {
        var permutations = AmplificationCircuit.permutations(List.of(1, 2, 3, 4, 5));

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