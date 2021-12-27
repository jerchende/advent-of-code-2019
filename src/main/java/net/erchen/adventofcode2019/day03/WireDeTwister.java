package net.erchen.adventofcode2019.day03;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class WireDeTwister {

    private final Map<XY, WireStat> wires;

    public static WireDeTwister fromInput(List<String> input) {
        var wires = new HashMap<XY, WireStat>();
        input.forEach(wire -> {
            var fields = new HashMap<XY, WireStat>();
            int x = 0, y = 0, steps = 1;
            for (var point : wire.split(",")) {
                int moveX = 0, moveY = 0;
                switch (point.charAt(0)) {
                case 'R' -> moveX = 1;
                case 'U' -> moveY = -1;
                case 'L' -> moveX = -1;
                case 'D' -> moveY = 1;
                }
                for (int i = 0; i < Integer.parseInt(point.substring(1)); i++) {
                    x += moveX;
                    y += moveY;
                    fields.put(new XY(x, y), new WireStat(1, steps++));
                }
            }
            fields.forEach((key, value) -> wires.merge(key, value, WireStat::merge));
        });

        return new WireDeTwister(wires);
    }

    public int closestCrossing() {
        return wires.entrySet().stream().filter(e -> e.getValue().wireCount > 1).map(Map.Entry::getKey).mapToInt(XY::distance).min().orElseThrow();
    }

    public int lessStepsCrossing() {
        return wires.values().stream().filter(wireStat -> wireStat.wireCount > 1).mapToInt(WireStat::stepCount).min().orElseThrow();
    }

    public record XY(int x, int y) {

        public int distance() {
            return Math.abs(x) + Math.abs(y);
        }
    }

    public record WireStat(int wireCount, int stepCount) {

        public static WireStat merge(WireStat a, WireStat b) {
            return new WireStat(a.wireCount + b.wireCount, a.stepCount + b.stepCount);
        }
    }
}
