package net.erchen.adventofcode2019.day06;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;

@Slf4j
public class OrbitMap {

    private final Map<String, List<String>> orbits = new HashMap<>();

    public OrbitMap(List<String> input) {
        input.stream().map(line -> line.split("\\)")).forEach(r ->
                orbits.computeIfAbsent(r[0], (k) -> new LinkedList<>()).add(r[1])
        );

    }

    private Stream<String> childOrbit(String orbit) {
        return orbits.getOrDefault(orbit, emptyList()).stream();
    }

    private Optional<String> parentOrbit(String orbit) {
        return orbits.entrySet().stream().filter(e -> e.getValue().contains(orbit)).map(Map.Entry::getKey).findAny();
    }

    public int countAllOrbits() {
        return countAllOrbits("COM", 0);
    }

    private int countAllOrbits(String from, int depth) {
        return childOrbit(from).mapToInt(x -> countAllOrbits(x, depth + 1)).sum() + depth;
    }

    public int countOrbitTransfers() {
        var me = parentOrbit("YOU").orElseThrow();
        var santa = parentOrbit("SAN").orElseThrow();
        return countOrbitTransfers(me, santa, new Stack<>());
    }

    private int countOrbitTransfers(String from, String to, Stack<String> transfers) {
        if (from.equals(to)) {
            return transfers.size();
        }
        return Stream.concat(parentOrbit(from).stream(), childOrbit(from))
                .filter(next -> !transfers.contains(next))
                .mapToInt(next -> {
                    var t = new Stack<String>();
                    t.addAll(transfers);
                    t.add(next);
                    return countOrbitTransfers(next, to, t);
                })
                .min()
                .orElse(Integer.MAX_VALUE);

    }
}