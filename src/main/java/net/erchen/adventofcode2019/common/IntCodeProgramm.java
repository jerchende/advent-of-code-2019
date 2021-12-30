package net.erchen.adventofcode2019.common;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static java.util.stream.Collectors.joining;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class IntCodeProgramm {

    private final Map<Long, Long> program;
    private final AtomicLong relativeBase = new AtomicLong(0);

    public static IntCodeProgramm fromInput(String input) {
        AtomicLong lineCouter = new AtomicLong(0);
        Map<Long, Long> program = new TreeMap<>();
        Arrays.stream(input.split(",")).mapToLong(Long::parseLong).forEach(l -> program.put(lineCouter.getAndIncrement(), l));
        return new IntCodeProgramm(program);
    }

    public String toString() {
        return program.values().stream().map(String::valueOf).collect(joining(","));
    }

    private long readMemory(long instructionPointer) {
        return program.getOrDefault(instructionPointer, 0L);
    }

    @SneakyThrows
    public void execute(Supplier<Long> input, Consumer<Long> output) {
        relativeBase.set(0);
        long instructionPointer = 0;
        while (true) {
            var operator = program.get(instructionPointer) % 100;
            if (operator == 1) {
                writeParameter(instructionPointer, 3, readParameter(instructionPointer, 1) + readParameter(instructionPointer, 2));
                instructionPointer += 4;
            } else if (operator == 2) {
                writeParameter(instructionPointer, 3, readParameter(instructionPointer, 1) * readParameter(instructionPointer, 2));
                instructionPointer += 4;
            } else if (operator == 3) {
                writeParameter(instructionPointer, 1, input.get());
                instructionPointer += 2;
            } else if (operator == 4) {
                output.accept(readParameter(instructionPointer, 1));
                instructionPointer += 2;
            } else if (operator == 5) {
                if (readParameter(instructionPointer, 1) != 0) {
                    instructionPointer = readParameter(instructionPointer, 2);
                } else {
                    instructionPointer += 3;
                }
            } else if (operator == 6) {
                if (readParameter(instructionPointer, 1) == 0) {
                    instructionPointer = readParameter(instructionPointer, 2);
                } else {
                    instructionPointer += 3;
                }
            } else if (operator == 7) {
                writeParameter(instructionPointer, 3, readParameter(instructionPointer, 1) < readParameter(instructionPointer, 2) ? 1L : 0L);
                instructionPointer += 4;
            } else if (operator == 8) {
                writeParameter(instructionPointer, 3, readParameter(instructionPointer, 1) == readParameter(instructionPointer, 2) ? 1L : 0L);
                instructionPointer += 4;
            } else if (operator == 9) {
                relativeBase.addAndGet(readParameter(instructionPointer, 1));
                instructionPointer += 2;
            } else if (operator == 99) {
                break;
            } else {
                throw new IllegalStateException("Unknown operator: " + operator);
            }
        }
    }

    private long readParameter(long instructionPointer, int parameterOffset) {
        return readMemory(memoryAddress(instructionPointer, parameterOffset));
    }

    private void writeParameter(long instructionPointer, int parameterOffset, Long value) {
        program.put(memoryAddress(instructionPointer, parameterOffset), value);
    }

    private long memoryAddress(long instructionPointer, int parameterOffset) {
        var parameterMode = (int) Math.floorDiv(readMemory(instructionPointer), tenPow(parameterOffset + 1)) % 10;
        return switch (parameterMode) {
            case 0 -> readMemory(instructionPointer + parameterOffset);
            case 1 -> instructionPointer + parameterOffset;
            case 2 -> relativeBase.get() + readMemory(instructionPointer + parameterOffset);
            default -> throw new IllegalStateException("Unexpected parameter mode: " + parameterMode);
        };
    }

    private static int tenPow(long exponent) {
        return (int) Math.pow(10, exponent);
    }

}
