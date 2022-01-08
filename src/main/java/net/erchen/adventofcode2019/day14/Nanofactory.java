package net.erchen.adventofcode2019.day14;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

import static java.lang.Integer.parseInt;

@Slf4j
@Getter
public class Nanofactory {

    private final List<Reaction> reactions;

    public Nanofactory(List<String> input) {
        this.reactions = input.stream()
                .map(line -> line.split(" => "))
                .map(split -> new Reaction(Arrays.stream(split[0].split(", ")).map(ChemicalAmount::fromInput).toList(), ChemicalAmount.fromInput(split[1])))
                .toList();
    }

    public long calculateOreConsumptionForFuel(long fuelAmount) {
        long oreConsumption = 0L;
        Queue<ChemicalAmount> toProduce = new LinkedList<>();
        Map<String, Long> notConsumed = new HashMap<>();

        toProduce.add(new ChemicalAmount(fuelAmount, "FUEL"));

        while (!toProduce.isEmpty()) {
            var todo = toProduce.poll();
            var reaction = reactionFor(todo.chemical);
            var needed = todo.amount - Optional.ofNullable(notConsumed.remove(todo.chemical)).orElse(0L);
            var quantity = ceilDiv(needed, reaction.output.amount);

            for (ChemicalAmount input : reaction.input) {
                //log.info("Need {} {} for {} {}", input.amount * quantity, input.chemical, reaction.output.amount, todo.chemical);
                if (input.chemical.equals("ORE")) {
                    oreConsumption += (long) input.amount * quantity;
                } else {
                    toProduce.add(new ChemicalAmount(input.amount * quantity, input.chemical));
                }
            }

            notConsumed.merge(todo.chemical, reaction.output.amount * quantity - needed, Long::sum);
        }

        return oreConsumption;
    }

    public long maximumFuelForOre(long ore) {
        var min = ore / calculateOreConsumptionForFuel(1);

        return (long) Math.floor(1.0 * ore * min / calculateOreConsumptionForFuel(min));
    }

    private static long ceilDiv(long a, long b) {
        return (a + b - 1) / b;
    }

    private Reaction reactionFor(String chemical) {
        return reactions.stream().filter(reaction -> reaction.output.chemical.equals(chemical)).findFirst().orElseThrow();
    }

    public static record Reaction(List<ChemicalAmount> input, ChemicalAmount output) {
    }

    public static record ChemicalAmount(long amount, String chemical) {

        public static ChemicalAmount fromInput(String input) {
            var split = input.split(" ");
            return new ChemicalAmount(parseInt(split[0]), split[1]);
        }

    }

}
