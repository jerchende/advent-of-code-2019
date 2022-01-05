package net.erchen.adventofcode2019.day12;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.function.ToIntFunction;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

@Getter
public class MoonSimulator {

    private final Moon[] initialMoons;
    private final Moon[] moons;

    public MoonSimulator(Moon[] moons) {
        this.moons = moons.clone();
        this.initialMoons = moons.clone();
    }

    public void simulate(int steps) {
        for (int i = 0; i < steps; i++) {
            simulate();
        }
    }

    public void simulate() {
        XYZ[] velocities = new XYZ[moons.length];

        for (int i = 0; i < moons.length; i++) {
            velocities[i] = calculateVelocity(moons[i]);
        }

        for (int i = 0; i < moons.length; i++) {
            moons[i] = moons[i].update(velocities[i]);
        }
    }

    private XYZ calculateVelocity(Moon moon) {
        int x = moon.velocity.x, y = moon.velocity.y, z = moon.velocity.z;

        for (Moon otherMoon : moons) {
            if (otherMoon == moon) {
                continue;
            }
            x -= Integer.compare(moon.position.x, otherMoon.position.x);
            y -= Integer.compare(moon.position.y, otherMoon.position.y);
            z -= Integer.compare(moon.position.z, otherMoon.position.z);
        }

        return new XYZ(x, y, z);
    }

    public int totalEnergy() {
        return Arrays.stream(moons).mapToInt(Moon::totalEnergy).sum();
    }

    private String axisHash(ToIntFunction<XYZ> axisExtractor) {

        return Arrays.stream(moons)
                .flatMap(moon -> Stream.of(moon.position, moon.velocity))
                .mapToInt(axisExtractor)
                .mapToObj(Integer::toHexString)
                .collect(joining("#"));
    }

    public long calculateCyclesUntilPositionsRepeat() {
        return LongStream.of(calculateCyclesUntilPositionsRepeat(XYZ::x), calculateCyclesUntilPositionsRepeat(XYZ::y), calculateCyclesUntilPositionsRepeat(XYZ::z)).reduce(MoonSimulator::lcm).orElseThrow();
    }

    private int calculateCyclesUntilPositionsRepeat(ToIntFunction<XYZ> axisExtractor) {
        HashSet<String> positions = new HashSet<>();
        var moonSimulator = new MoonSimulator(initialMoons);
        while (true) {
            var currentPosition = moonSimulator.axisHash(axisExtractor);
            if (!positions.add(currentPosition)) {
                return positions.size();
            }
            moonSimulator.simulate();
        }
    }

    static long gcd(long a, long b) {
        if (a == 0) {
            return b;
        }
        return gcd(b % a, a);
    }

    static long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }

    public static record Moon(XYZ position, XYZ velocity) {

        public Moon update(XYZ velocity) {
            return new Moon(new XYZ(position.x + velocity.x, position.y + velocity.y, position.z + velocity.z), velocity);
        }

        public int totalEnergy() {
            return potentialEnergy() * kineticEnergy();
        }

        public int potentialEnergy() {
            return position.energy();
        }

        public int kineticEnergy() {
            return velocity.energy();
        }
    }

    public static record XYZ(int x, int y, int z) {

        public int energy() {
            return Math.abs(x) + Math.abs(y) + Math.abs(z);
        }

    }
}
