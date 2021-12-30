package net.erchen.adventofcode2019.day08;

import lombok.Getter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Getter
public class SpaceImage {

    private final int[] image;
    private final int width;
    private final int height;
    private final int layerSize;
    private final int layerCount;

    public SpaceImage(String input, int width, int height) {
        this.width = width;
        this.height = height;
        this.image = input.trim().chars().map(c -> Character.getNumericValue((char) c)).toArray();
        this.layerSize = width * height;
        this.layerCount = this.image.length / this.layerSize;
    }

    public IntStream image() {
        return Arrays.stream(image);
    }

    public Stream<int[]> layers() {
        return IntStream.range(0, layerCount).mapToObj(i -> image().skip((long) i * layerSize).limit(layerSize).toArray());
    }

    public int checkSum() {
        return layers().min(Comparator.comparingInt(i -> countPixel(i, 0))).map(i -> countPixel(i, 1) * countPixel(i, 2)).orElseThrow();
    }

    private static int countPixel(int[] input, int countPixel) {
        return (int) Arrays.stream(input).filter(i -> i == countPixel).count();
    }

    public String toString() {
        var sb = new StringBuilder();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                layer:
                for (int layer = 0; layer < layerCount; layer++) {
                    switch (image[layer * layerSize + y * width + x]) {
                    case 0:
                        sb.append("⬛️");
                        break layer;
                    case 1:
                        sb.append("⬜️");
                        break layer;
                    }
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
