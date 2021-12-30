package net.erchen.adventofcode2019.day08;

import lombok.Getter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
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
        return IntStream.range(0, height).boxed().map(y ->
                IntStream.range(0, width).boxed().map(x ->
                        IntStream.range(0, layerCount).map(layer -> image[layer * layerSize + y * width + x])
                                .filter(pixel -> pixel != 2)
                                .mapToObj(pixel -> pixel == 0 ? "⬛️" : "⬜️")
                                .findFirst()
                                .orElse("🟦")
                ).collect(Collectors.joining())
        ).collect(Collectors.joining("\n"));
    }
}
