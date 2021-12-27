package net.erchen.adventofcode2019.day02;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

import static java.util.stream.Collectors.joining;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class IntCodeProgramm {

    private final int[] program;

    public static IntCodeProgramm fromInput(String input) {
        return new IntCodeProgramm(Arrays.stream(input.split(",")).mapToInt(Integer::parseInt).toArray());
    }

    public String toString() {
        return Arrays.stream(program).mapToObj(String::valueOf).collect(joining(","));
    }

    public void execute() {
        for (int i = 0; i < program.length; i += 4) {
            if (program[i] == 1) {
                program[program[i + 3]] = program[program[i + 1]] + program[program[i + 2]];
            } else if (program[i] == 2) {
                program[program[i + 3]] = program[program[i + 1]] * program[program[i + 2]];
            } else if (program[i] == 99) {
                return;
            } else {
                throw new IllegalStateException();
            }
        }
    }
}
