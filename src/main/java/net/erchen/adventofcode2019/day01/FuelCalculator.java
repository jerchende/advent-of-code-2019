package net.erchen.adventofcode2019.day01;

import java.util.List;

public class FuelCalculator {

    public static int calculateFuel(List<String> modules) {
        return modules.stream().mapToInt(Integer::parseInt).map(FuelCalculator::calculateFuel).sum();
    }

    public static int calculateFuel(int mass) {
        return Math.max(Math.floorDiv(mass, 3) - 2, 0);
    }

    public static int calculateFuelRecursive(List<String> modules) {
        return modules.stream().mapToInt(Integer::parseInt).map(FuelCalculator::calculateFuelRecursive).sum();
    }

    public static int calculateFuelRecursive(int mass) {
        var fuel = calculateFuel(mass);
        if (fuel > 0) {
            return calculateFuelRecursive(fuel) + fuel;
        }
        return fuel;
    }
}
