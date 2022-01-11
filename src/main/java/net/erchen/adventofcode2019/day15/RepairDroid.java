package net.erchen.adventofcode2019.day15;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import net.erchen.adventofcode2019.common.IntCodeProgramm;
import net.erchen.codingpuzzlescommon.matrix.Matrix;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class RepairDroid {

    private final IntCodeProgramm programm = IntCodeProgramm.fromInput(
            "3,1033,1008,1033,1,1032,1005,1032,31,1008,1033,2,1032,1005,1032,58,1008,1033,3,1032,1005,1032,81,1008,1033,4,1032,1005,1032,104,99,101,0,1034,1039,1001,1036,0,1041,1001,1035,-1,1040,1008,1038,0,1043,102,-1,1043,1032,1,1037,1032,1042,1105,1,124,1002,1034,1,1039,1001,1036,0,1041,1001,1035,1,1040,1008,1038,0,1043,1,1037,1038,1042,1106,0,124,1001,1034,-1,1039,1008,1036,0,1041,101,0,1035,1040,1001,1038,0,1043,1002,1037,1,1042,1105,1,124,1001,1034,1,1039,1008,1036,0,1041,1002,1035,1,1040,101,0,1038,1043,1001,1037,0,1042,1006,1039,217,1006,1040,217,1008,1039,40,1032,1005,1032,217,1008,1040,40,1032,1005,1032,217,1008,1039,33,1032,1006,1032,165,1008,1040,33,1032,1006,1032,165,1101,2,0,1044,1106,0,224,2,1041,1043,1032,1006,1032,179,1101,0,1,1044,1106,0,224,1,1041,1043,1032,1006,1032,217,1,1042,1043,1032,1001,1032,-1,1032,1002,1032,39,1032,1,1032,1039,1032,101,-1,1032,1032,101,252,1032,211,1007,0,68,1044,1106,0,224,1101,0,0,1044,1105,1,224,1006,1044,247,1002,1039,1,1034,102,1,1040,1035,1001,1041,0,1036,1002,1043,1,1038,1001,1042,0,1037,4,1044,1105,1,0,67,55,37,80,63,12,30,78,95,7,20,63,83,54,86,58,97,11,84,24,11,77,42,78,22,54,89,52,44,28,93,30,81,60,58,78,87,60,54,59,78,96,17,82,74,85,66,41,89,96,54,40,82,17,22,89,65,96,71,55,81,34,90,11,85,44,58,83,79,93,30,76,62,80,16,73,20,43,40,73,69,39,39,15,93,39,99,8,74,33,97,84,24,50,91,5,71,34,81,76,22,98,50,93,80,36,76,16,76,43,19,71,63,41,21,99,40,75,55,27,82,80,83,54,66,75,61,86,14,10,74,38,92,31,49,97,20,98,15,71,59,96,53,86,35,60,6,73,71,59,79,10,84,69,23,82,14,7,76,99,45,19,96,92,14,63,55,71,46,71,34,74,73,22,95,89,10,24,59,69,17,42,96,12,92,94,66,1,69,91,36,90,94,13,17,33,46,20,89,90,24,12,94,92,83,42,73,43,70,83,55,17,92,66,23,74,99,1,92,82,54,71,96,1,22,78,74,94,66,78,40,87,13,87,73,74,89,26,26,70,42,79,3,9,84,72,55,98,56,27,73,74,57,85,66,76,88,55,58,30,97,40,71,76,6,10,55,71,43,36,99,46,59,34,37,84,61,85,90,62,98,18,39,46,84,23,70,93,9,71,5,71,94,5,59,40,71,26,90,12,45,57,74,5,92,86,32,99,20,92,82,22,44,88,29,41,89,7,86,81,72,76,9,94,94,3,8,94,71,12,93,6,82,91,91,20,86,86,38,85,95,42,86,85,19,57,90,17,85,6,84,17,81,42,77,63,26,59,9,24,85,22,31,35,93,64,90,4,16,91,67,83,23,43,63,75,3,88,93,52,14,84,85,36,95,12,51,79,54,1,16,72,1,76,79,88,63,95,77,6,91,86,23,92,54,91,51,82,45,14,98,89,74,47,52,82,80,65,74,44,58,90,14,98,42,91,6,50,88,29,81,96,25,1,97,62,62,73,61,48,82,76,93,98,49,14,74,6,97,30,47,73,77,8,89,10,17,65,21,74,95,43,83,89,72,96,27,59,20,58,80,10,70,86,42,92,26,50,98,85,3,62,20,93,86,78,19,78,91,23,90,37,71,66,97,97,95,86,40,46,79,70,37,14,98,51,91,81,4,9,77,93,19,53,70,87,40,11,95,25,93,90,17,98,39,76,92,55,57,93,39,76,13,99,58,92,26,88,80,65,34,71,62,72,17,64,38,97,85,32,4,88,69,82,51,63,61,71,77,33,90,59,74,49,76,8,76,93,55,36,71,84,7,67,47,3,85,98,9,99,32,8,79,18,28,55,77,10,30,79,77,4,1,99,82,66,90,41,64,22,82,33,20,87,24,29,80,53,72,27,17,85,84,70,16,94,11,81,92,48,85,61,47,83,21,45,92,92,38,61,75,98,52,73,80,29,82,94,29,85,61,69,59,35,84,86,60,98,63,83,69,39,10,15,64,18,85,88,63,97,95,56,13,43,75,93,13,34,85,57,37,96,39,65,60,73,73,82,11,81,80,38,88,76,23,88,19,70,2,93,46,28,79,92,91,18,6,92,96,50,77,56,45,77,36,64,83,91,64,75,48,72,71,17,69,40,82,7,6,92,70,25,23,72,9,23,84,16,17,75,76,70,60,61,99,86,21,27,85,63,80,81,55,87,93,97,53,78,53,97,14,97,49,85,65,91,72,72,5,93,34,81,10,85,86,81,19,87,61,84,11,99,96,94,8,78,13,84,9,70,0,0,21,21,1,10,1,0,0,0,0,0,0");

    public Matrix<Field> discoverOxygenGenerator() {
        var map = Matrix.fromInitValue(42, () -> Field.UNDISCOVERED);

        var input = new LinkedBlockingQueue<Integer>();
        var output = new LinkedBlockingQueue<Integer>();
        CompletableFuture.runAsync(() -> this.programm.execute(() -> pollFromQueueWithTimeout(input), output::add));

        int currentX = map.dimension() / 2, currentY = map.dimension() / 2;
        map.setFieldValue(currentX, currentY, Field.EMPTY);

        search:
        while (true) {
            for (int direction = 1; direction <= 8; direction++) {
                if (direction == 5) {
                    detectDeadEnd(map, currentX, currentY);
                }

                int newX = currentX, newY = currentY;
                switch ((direction - 1) % 4 + 1) {
                case 1 -> newY--;
                case 2 -> newY++;
                case 3 -> newX--;
                case 4 -> newX++;
                }

                if (map.fieldValue(newX, newY) == Field.DEADEND || map.fieldValue(newX, newY) == Field.WALL) {
                    continue;
                }

                if (direction < 5 && map.fieldValue(newX, newY) != Field.UNDISCOVERED) {
                    continue;
                }

                input.add((direction - 1) % 4 + 1);
                var outputField = Field.fromOutput(pollFromQueueWithTimeout(output));

                map.setFieldValue(newX, newY, outputField);

                if (outputField != Field.WALL) {
                    currentX = newX;
                    currentY = newY;
                }

                if (outputField == Field.OXYGEN) {
                    break search;
                }
                break;
            }
        }

        return map;
    }

    private void detectDeadEnd(Matrix<Field> map, int currentX, int currentY) {
        var field = map.field(currentX, currentY);

        if (field.getAdjacents().filter(f -> f.getValue() == Field.DEADEND || f.getValue() == Field.WALL).count() == 3L) {
            map.setFieldValue(currentX, currentY, Field.DEADEND);
        }
    }

    public static int repairOxygenGenerator(Matrix<Field> map) {
        int minutes = 0;
        List<Matrix<Field>.Field> nextToFloat = List.of(map.allFields().filter(f -> f.getValue() == Field.OXYGEN).findFirst().orElseThrow());
        while (true) {
            nextToFloat = nextToFloat.stream().flatMap(Matrix.Field::getAdjacents).filter(f -> f.getValue() == Field.EMPTY || f.getValue() == Field.DEADEND).toList();
            if (nextToFloat.size() == 0) {
                break;
            }
            nextToFloat.forEach(f -> map.setFieldValue(f.getX(), f.getY(), Field.OXYGEN));
            minutes++;
        }
        return minutes;
    }

    @SneakyThrows
    private int pollFromQueueWithTimeout(BlockingQueue<Integer> queue) {
        return queue.poll(1, TimeUnit.SECONDS);
    }

    @Getter
    @RequiredArgsConstructor
    public enum Field {
        UNDISCOVERED(" "), WALL("#"), EMPTY("."), OXYGEN("O"), DROID("D"), DEADEND("*");

        private final String print;

        public static Field fromOutput(int output) {
            return switch (output) {
                case 0 -> WALL;
                case 1 -> EMPTY;
                case 2 -> OXYGEN;
                default -> throw new IllegalArgumentException();
            };
        }

        @Override public String toString() {
            return getPrint();
        }
    }
}
