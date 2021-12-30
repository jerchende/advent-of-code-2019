package net.erchen.adventofcode2019.day11;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.erchen.adventofcode2019.common.IntCodeProgramm;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class RegistrationIdentifier {

    private final IntCodeProgramm programm = IntCodeProgramm.fromInput(
            "3,8,1005,8,327,1106,0,11,0,0,0,104,1,104,0,3,8,102,-1,8,10,1001,10,1,10,4,10,108,0,8,10,4,10,1001,8,0,28,1006,0,42,2,1104,11,10,1006,0,61,2,1005,19,10,3,8,1002,8,-1,10,1001,10,1,10,4,10,1008,8,1,10,4,10,102,1,8,65,1006,0,4,3,8,1002,8,-1,10,1001,10,1,10,4,10,108,1,8,10,4,10,1002,8,1,89,1,1108,10,10,1,1103,11,10,1,109,18,10,1006,0,82,3,8,102,-1,8,10,1001,10,1,10,4,10,108,0,8,10,4,10,102,1,8,126,2,109,7,10,1,104,3,10,1006,0,64,2,1109,20,10,3,8,1002,8,-1,10,101,1,10,10,4,10,108,1,8,10,4,10,101,0,8,163,3,8,102,-1,8,10,1001,10,1,10,4,10,108,1,8,10,4,10,1002,8,1,185,2,1109,12,10,2,103,16,10,1,107,11,10,3,8,102,-1,8,10,1001,10,1,10,4,10,108,0,8,10,4,10,1001,8,0,219,1,1005,19,10,3,8,102,-1,8,10,1001,10,1,10,4,10,108,1,8,10,4,10,102,1,8,245,2,1002,8,10,1,2,9,10,1006,0,27,1006,0,37,3,8,1002,8,-1,10,1001,10,1,10,4,10,108,0,8,10,4,10,102,1,8,281,1006,0,21,3,8,102,-1,8,10,101,1,10,10,4,10,108,0,8,10,4,10,1001,8,0,306,101,1,9,9,1007,9,1075,10,1005,10,15,99,109,649,104,0,104,1,21102,1,847069852568,1,21101,344,0,0,1105,1,448,21101,0,386979963688,1,21101,355,0,0,1105,1,448,3,10,104,0,104,1,3,10,104,0,104,0,3,10,104,0,104,1,3,10,104,0,104,1,3,10,104,0,104,0,3,10,104,0,104,1,21102,46346031251,1,1,21101,0,402,0,1105,1,448,21102,1,29195594775,1,21101,0,413,0,1105,1,448,3,10,104,0,104,0,3,10,104,0,104,0,21101,0,868498428772,1,21101,0,436,0,1106,0,448,21102,718170641172,1,1,21102,1,447,0,1105,1,448,99,109,2,21202,-1,1,1,21102,40,1,2,21102,1,479,3,21102,1,469,0,1105,1,512,109,-2,2105,1,0,0,1,0,0,1,109,2,3,10,204,-1,1001,474,475,490,4,0,1001,474,1,474,108,4,474,10,1006,10,506,1101,0,0,474,109,-2,2106,0,0,0,109,4,2102,1,-1,511,1207,-3,0,10,1006,10,529,21101,0,0,-3,22101,0,-3,1,22101,0,-2,2,21101,0,1,3,21101,548,0,0,1106,0,553,109,-4,2106,0,0,109,5,1207,-3,1,10,1006,10,576,2207,-4,-2,10,1006,10,576,21202,-4,1,-4,1106,0,644,22101,0,-4,1,21201,-3,-1,2,21202,-2,2,3,21102,1,595,0,1105,1,553,21201,1,0,-4,21101,0,1,-1,2207,-4,-2,10,1006,10,614,21102,1,0,-1,22202,-2,-1,-2,2107,0,-3,10,1006,10,636,22102,1,-1,1,21102,1,636,0,106,0,511,21202,-2,-1,-2,22201,-4,-2,-4,109,-5,2105,1,0");

    private final Set<XY> whitePixel = new HashSet<>();

    public void runPaintProgramm() {
        var position = new Holder<>(new XY(0, 0));
        var direction = new Holder<>(Direction.UP);
        var outputCounter = new AtomicInteger(0);
        whitePixel.add(position.getValue());

        programm.execute(() -> whitePixel.contains(position.getValue()) ? 1L : 0L, o -> {
            if (outputCounter.getAndIncrement() % 2 == 0) {
                if (o == 0L) {
                    whitePixel.remove(position.getValue());
                } else {
                    whitePixel.add(position.getValue());
                }
            } else {
                if (o == 0L) {
                    direction.setValue(direction.getValue().turnLeft());
                } else {
                    direction.setValue(direction.getValue().turnRight());
                }
                position.setValue(position.getValue().moveTo(direction.getValue()));
            }
        });
    }

    @Override
    public String toString() {
        var xStats = whitePixel.stream().mapToInt(XY::x).summaryStatistics();
        var yStats = whitePixel.stream().mapToInt(XY::y).summaryStatistics();

        var sb = new StringBuilder();

        for (int y = yStats.getMin(); y <= yStats.getMax(); y++) {
            for (int x = xStats.getMin(); x <= xStats.getMax(); x++) {
                sb.append(whitePixel.contains(new XY(x, y)) ? "⬛️" : "⬜️");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    @Data
    @AllArgsConstructor
    public static class Holder<T> {
        private T value;
    }

    public record XY(int x, int y) {

        public XY moveTo(Direction d) {
            return new XY(x + d.moveX, y + d.getMoveY());
        }

    }

    @Getter
    @RequiredArgsConstructor
    public enum Direction {

        UP(0, -1), RIGHT(1, 0), DOWN(0, 1), LEFT(-1, 0);

        private final int moveX;
        private final int moveY;

        public Direction turnLeft() {
            return Direction.values()[(this.ordinal() + 3) % 4];
        }

        public Direction turnRight() {
            return Direction.values()[(this.ordinal() + 1) % 4];
        }
    }

}

