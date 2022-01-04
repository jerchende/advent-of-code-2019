package net.erchen.adventofcode2019.day12;

import net.erchen.adventofcode2019.day12.MoonSimulator.Moon;
import net.erchen.adventofcode2019.day12.MoonSimulator.XYZ;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MoonSimulatorTest {

    static final XYZ ZERO_XYZ = new XYZ(0, 0, 0);

    /*
    <x=-1, y=0, z=2>
    <x=2, y=-10, z=-7>
    <x=4, y=-8, z=8>
    <x=3, y=5, z=-1>
     */
    static final Moon[] sampleMoons = new Moon[] {
            new Moon(new XYZ(-1, 0, 2), ZERO_XYZ),
            new Moon(new XYZ(2, -10, -7), ZERO_XYZ),
            new Moon(new XYZ(4, -8, 8), ZERO_XYZ),
            new Moon(new XYZ(3, 5, -1), ZERO_XYZ)
    };

    /*
    <x=0, y=4, z=0>
    <x=-10, y=-6, z=-14>
    <x=9, y=-16, z=-3>
    <x=6, y=-1, z=2>
    */
    static final Moon[] solutionMoons = new Moon[] {
            new Moon(new XYZ(0, 4, 0), ZERO_XYZ),
            new Moon(new XYZ(-10, -6, -14), ZERO_XYZ),
            new Moon(new XYZ(9, -16, -3), ZERO_XYZ),
            new Moon(new XYZ(6, -1, 2), ZERO_XYZ)
    };

    @Test
    void shouldSimulateMoon() {
        var moonSimulator = new MoonSimulator(sampleMoons);

        moonSimulator.simulate();
        assertThat(moonSimulator.getMoons()).containsExactly(
                new Moon(new XYZ(2, -1, 1), new XYZ(3, -1, -1)),
                new Moon(new XYZ(3, -7, -4), new XYZ(1, 3, 3)),
                new Moon(new XYZ(1, -7, 5), new XYZ(-3, 1, -3)),
                new Moon(new XYZ(2, 2, 0), new XYZ(-1, -3, 1))
        );

        moonSimulator.simulate();
        assertThat(moonSimulator.getMoons()).containsExactly(
                new Moon(new XYZ(5, -3, -1), new XYZ(3, -2, -2)),
                new Moon(new XYZ(1, -2, 2), new XYZ(-2, 5, 6)),
                new Moon(new XYZ(1, -4, -1), new XYZ(0, 3, -6)),
                new Moon(new XYZ(1, -4, 2), new XYZ(-1, -6, 2))
        );
    }

    @Test
    void shouldSimulateMoonTenTimes() {
        var moonSimulator = new MoonSimulator(sampleMoons);

        moonSimulator.simulate(10);

        assertThat(moonSimulator.getMoons()).containsExactly(
                new Moon(new XYZ(2, 1, -3), new XYZ(-3, -2, 1)),
                new Moon(new XYZ(1, -8, 0), new XYZ(-1, 1, 3)),
                new Moon(new XYZ(3, -6, 1), new XYZ(3, 2, -3)),
                new Moon(new XYZ(2, 0, 4), new XYZ(1, -1, -1))
        );

        assertThat(moonSimulator.totalEnergy()).isEqualTo(179);
    }

    @Test
    void shouldCalculatePotentialEnergy() {
        var moon = new Moon(new XYZ(2, 1, -3), new XYZ(-4, 5, -6));

        assertThat(moon.potentialEnergy()).isEqualTo(6);
    }

    @Test
    void shouldCalculateKineticEnergy() {
        var moon = new Moon(new XYZ(2, 1, -3), new XYZ(-4, 5, -6));

        assertThat(moon.kineticEnergy()).isEqualTo(15);
    }

    @Test
    void shouldCalculateTotalEnergy() {
        var moon = new Moon(new XYZ(2, 1, -3), new XYZ(-4, 5, -6));

        assertThat(moon.totalEnergy()).isEqualTo(90);
    }

    @Test
    void shouldSimulateMoonTousandTimes() {
        var moonSimulator = new MoonSimulator(solutionMoons);

        moonSimulator.simulate(1000);

        assertThat(moonSimulator.totalEnergy()).isEqualTo(13500);
    }

    @Test
    void shouldCalculateGcd() {
        assertThat(MoonSimulator.gcd(9, 6)).isEqualTo(3);
        assertThat(MoonSimulator.gcd(9, 15)).isEqualTo(3);
    }

    @Test
    void shouldCalculateLcm() {
        assertThat(MoonSimulator.lcm(9, 6)).isEqualTo(18);
        assertThat(MoonSimulator.lcm(9, 15)).isEqualTo(45);
    }

    @Test
    void shouldCalculateCyclesUntilPositionsRepeat_Sample() {
        var moonSimulator = new MoonSimulator(sampleMoons);
        assertThat(moonSimulator.calculateCyclesUntilPositionsRepeat()).isEqualTo(2772);
    }

    @Test
    void shouldCalculateCyclesUntilPositionsRepeat_Solution() {
        var moonSimulator = new MoonSimulator(solutionMoons);
        assertThat(moonSimulator.calculateCyclesUntilPositionsRepeat()).isEqualTo(278013787106916L);
    }

}