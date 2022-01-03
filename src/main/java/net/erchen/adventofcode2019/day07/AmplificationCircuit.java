package net.erchen.adventofcode2019.day07;

import lombok.SneakyThrows;
import net.erchen.adventofcode2019.common.IntCodeProgramm;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class AmplificationCircuit {

    private final IntCodeProgramm[] amplifier;

    public AmplificationCircuit(String input) {
        this.amplifier = new IntCodeProgramm[] { IntCodeProgramm.fromInput(input), IntCodeProgramm.fromInput(input), IntCodeProgramm.fromInput(input), IntCodeProgramm.fromInput(input), IntCodeProgramm.fromInput(input) };
    }

    @SneakyThrows
    public long thrusterSignal(List<Long> phases) {
        if (phases.size() != 5) {
            throw new IllegalArgumentException();
        }

        var inputs =
                IntStream.range(0, 5).mapToObj(i -> {
                    var queue = new LinkedTransferQueue<Long>();
                    queue.add((long) phases.get(i));
                    return queue;
                }).toArray(BlockingQueue[]::new);

        inputs[0].add(0L);

        var forkJoinPool = new ForkJoinPool(5);
        forkJoinPool.submit(() ->
                IntStream.range(0, 5).parallel().forEach(i ->
                        amplifier[i].execute(() -> pollFromQueueWithTimeout(inputs[i]), output -> inputs[i == 4 ? 0 : i + 1].add(output))
                )
        ).get();

        return pollFromQueueWithTimeout(inputs[0]);

    }

    @SneakyThrows
    private long pollFromQueueWithTimeout(BlockingQueue<Long> input) {
        return input.poll(2, TimeUnit.SECONDS);
    }

    public long maxThrusterSignal(boolean feedbackLoopMode) {
        return permutations(feedbackLoopMode ? List.of(5L, 6L, 7L, 8L, 9L) : List.of(0L, 1L, 2L, 3L, 4L))
                .mapToLong(this::thrusterSignal)
                .max()
                .orElseThrow();
    }

    static Stream<List<Long>> permutations(List<Long> input) {
        if (input.size() == 1) {
            return Stream.of(input);
        }
        return input.stream().flatMap(first -> permutations(input.stream().filter(a -> !a.equals(first)).toList()).map(LinkedList::new).peek(l -> l.addFirst(first)));
    }
}
