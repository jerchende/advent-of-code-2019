package net.erchen.adventofcode2019.day08;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class SpaceImageTest {

    @SneakyThrows
    static String solutionInput() {
        return Files.readString(Path.of("src/test/resources/day08/input.txt")).trim();
    }

    @Test
    void shouldParse() {
        SpaceImage image = new SpaceImage("123456789012", 3, 2);

        assertThat(image.getImage()).hasSize(12);
        assertThat(image.getWidth()).isEqualTo(3);
        assertThat(image.getHeight()).isEqualTo(2);
        assertThat(image.getLayerCount()).isEqualTo(2);
    }

    @Test
    void shouldReturnLayers() {
        SpaceImage image = new SpaceImage("123456789012", 3, 2);

        assertThat(image.layers()).containsExactly(new int[] { 1, 2, 3, 4, 5, 6 }, new int[] { 7, 8, 9, 0, 1, 2 });
    }

    @Test
    void shouldCalculateChecksum() {
        SpaceImage image = new SpaceImage("112011021000", 3, 2);

        assertThat(image.checkSum()).isEqualTo(4);
    }

    @Test
    void shouldCalculateChecksum_Solution() {
        SpaceImage image = new SpaceImage(solutionInput(), 6, 25);

        assertThat(image.checkSum()).isEqualTo(2016);
    }

    @Test
    void shouldPrintImage() {
        SpaceImage image = new SpaceImage("0222112222120000", 2, 2);

        assertThat(image.toString()).isEqualTo("⬛️⬜️\n⬜️⬛️");
    }

    @Test
    void shouldPrintImage_Solution() {
        SpaceImage image = new SpaceImage(solutionInput(), 25, 6);

        assertThat(image.toString()).isEqualTo("""
                ⬜️⬛️⬛️⬜️⬛️⬜️⬜️⬜️⬜️⬛️⬛️⬜️⬜️⬛️⬛️⬜️⬜️⬜️⬜️⬛️⬜️⬛️⬛️⬜️⬛️
                ⬜️⬛️⬛️⬜️⬛️⬛️⬛️⬛️⬜️⬛️⬜️⬛️⬛️⬜️⬛️⬛️⬛️⬛️⬜️⬛️⬜️⬛️⬛️⬜️⬛️
                ⬜️⬜️⬜️⬜️⬛️⬛️⬛️⬜️⬛️⬛️⬜️⬛️⬛️⬛️⬛️⬛️⬛️⬜️⬛️⬛️⬜️⬛️⬛️⬜️⬛️
                ⬜️⬛️⬛️⬜️⬛️⬛️⬜️⬛️⬛️⬛️⬜️⬛️⬛️⬛️⬛️⬛️⬜️⬛️⬛️⬛️⬜️⬛️⬛️⬜️⬛️
                ⬜️⬛️⬛️⬜️⬛️⬜️⬛️⬛️⬛️⬛️⬜️⬛️⬛️⬜️⬛️⬜️⬛️⬛️⬛️⬛️⬜️⬛️⬛️⬜️⬛️
                ⬜️⬛️⬛️⬜️⬛️⬜️⬜️⬜️⬜️⬛️⬛️⬜️⬜️⬛️⬛️⬜️⬜️⬜️⬜️⬛️⬛️⬜️⬜️⬛️⬛️""");
    }
}