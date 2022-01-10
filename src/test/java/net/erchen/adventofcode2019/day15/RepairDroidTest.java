package net.erchen.adventofcode2019.day15;

import org.junit.jupiter.api.Test;

import static net.erchen.adventofcode2019.day15.RepairDroid.Field.EMPTY;
import static org.assertj.core.api.Assertions.assertThat;

class RepairDroidTest {

    @Test
    void shouldDiscoverOxygenGenerator() {

        var repairDroid = new RepairDroid();
        var map = repairDroid.discoverOxygenGenerator();

        assertThat(map.allFields().filter(f -> f.getValue() == EMPTY).count()).isEqualTo(308);

    }

    @Test
    void shouldRepairOxygenGenerator() {

        var repairDroid = new RepairDroid();
        var map = repairDroid.discoverOxygenGenerator();

        assertThat(RepairDroid.repairOxygenGenerator(map)).isEqualTo(328);
    }
}