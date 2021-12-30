package net.erchen.adventofcode2019.day11;

import net.erchen.adventofcode2019.day11.RegistrationIdentifier.Direction;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RegistrationIdentifierTest {

    @Test
    void shouldTurnLeft() {
        assertThat(Direction.UP.turnLeft()).isEqualTo(Direction.LEFT);
        assertThat(Direction.LEFT.turnLeft()).isEqualTo(Direction.DOWN);
        assertThat(Direction.DOWN.turnLeft()).isEqualTo(Direction.RIGHT);
        assertThat(Direction.RIGHT.turnLeft()).isEqualTo(Direction.UP);
    }

    @Test
    void shouldTurnRight() {
        assertThat(Direction.UP.turnRight()).isEqualTo(Direction.RIGHT);
        assertThat(Direction.RIGHT.turnRight()).isEqualTo(Direction.DOWN);
        assertThat(Direction.DOWN.turnRight()).isEqualTo(Direction.LEFT);
        assertThat(Direction.LEFT.turnRight()).isEqualTo(Direction.UP);
    }

    @Test
    void shouldPaintRegistrationIdentifier() {
        var registrationIdentifier = new RegistrationIdentifier();

        registrationIdentifier.runPaintProgramm();

        assertThat(registrationIdentifier.toString()).isEqualTo("""
                ⬛️⬜️⬜️⬛️⬜️⬛️⬛️⬛️⬜️⬜️⬛️⬜️⬜️⬛️⬜️⬛️⬛️⬛️⬛️⬜️⬜️⬛️⬛️⬜️⬜️⬛️⬛️⬛️⬛️⬜️⬛️⬛️⬛️⬜️⬜️⬛️⬜️⬜️⬛️
                ⬛️⬜️⬛️⬜️⬜️⬛️⬜️⬜️⬛️⬜️⬛️⬜️⬜️⬛️⬜️⬛️⬜️⬜️⬜️⬜️⬛️⬜️⬜️⬛️⬜️⬜️⬜️⬜️⬛️⬜️⬛️⬜️⬜️⬛️⬜️⬛️⬜️⬛️⬜️
                ⬛️⬛️⬜️⬜️⬜️⬛️⬛️⬛️⬜️⬜️⬛️⬜️⬜️⬛️⬜️⬛️⬛️⬛️⬜️⬜️⬛️⬜️⬜️⬜️⬜️⬜️⬜️⬛️⬜️⬜️⬛️⬛️⬛️⬜️⬜️⬛️⬛️⬜️⬜️
                ⬛️⬜️⬛️⬜️⬜️⬛️⬜️⬜️⬛️⬜️⬛️⬜️⬜️⬛️⬜️⬛️⬜️⬜️⬜️⬜️⬛️⬜️⬛️⬛️⬜️⬜️⬛️⬜️⬜️⬜️⬛️⬜️⬜️⬛️⬜️⬛️⬜️⬛️⬜️
                ⬛️⬜️⬛️⬜️⬜️⬛️⬜️⬜️⬛️⬜️⬛️⬜️⬜️⬛️⬜️⬛️⬜️⬜️⬜️⬜️⬛️⬜️⬜️⬛️⬜️⬛️⬜️⬜️⬜️⬜️⬛️⬜️⬜️⬛️⬜️⬛️⬜️⬛️⬜️
                ⬛️⬜️⬜️⬛️⬜️⬛️⬛️⬛️⬜️⬜️⬜️⬛️⬛️⬜️⬜️⬛️⬛️⬛️⬛️⬜️⬜️⬛️⬛️⬛️⬜️⬛️⬛️⬛️⬛️⬜️⬛️⬛️⬛️⬜️⬜️⬛️⬜️⬜️⬛️
                """);
    }
}