package net.erchen.adventofcode2019.day07;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Supplier;

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

    @SneakyThrows public void execute(Supplier<Integer> input, Consumer<Integer> output) {
        for (int i = 0; i < program.length; ) {
            var operator = program[i] % 100;
            if (operator == 1) {
                program[program[i + 3]] = parameter(i, 1) + parameter(i, 2);
                i += 4;
            } else if (operator == 2) {
                program[program[i + 3]] = parameter(i, 1) * parameter(i, 2);
                i += 4;
            } else if (operator == 3) {
                program[program[i + 1]] = input.get();
                i += 2;
            } else if (operator == 4) {
                output.accept(parameter(i, 1));
                i += 2;
            } else if (operator == 5) {
                if (parameter(i, 1) != 0) {
                    i = parameter(i, 2);
                } else {
                    i += 3;
                }
            } else if (operator == 6) {
                if (parameter(i, 1) == 0) {
                    i = parameter(i, 2);
                } else {
                    i += 3;
                }
            } else if (operator == 7) {
                program[program[i + 3]] = parameter(i, 1) < parameter(i, 2) ? 1 : 0;
                i += 4;
            } else if (operator == 8) {
                program[program[i + 3]] = parameter(i, 1) == parameter(i, 2) ? 1 : 0;
                i += 4;
            } else if (operator == 99) {
                break;
            } else {
                throw new IllegalStateException("Unknown operator: " + operator);
            }
        }
    }

    private int parameter(int instructionPointer, int parameterOffset) {
        var immediateMode = (program[instructionPointer] % tenPow(parameterOffset + 2)) >= tenPow(parameterOffset + 1);
        if (immediateMode) {
            return program[instructionPointer + parameterOffset];
        } else {
            return program[program[instructionPointer + parameterOffset]];
        }
    }

    private static int tenPow(int exponent) {
        return (int) Math.pow(10, exponent);
    }

}
