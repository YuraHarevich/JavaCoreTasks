package org.example.Skynet;



import org.example.Skynet.faction.Faction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ProductionProcess {

    private final int days = 100;
    private final int productionPerDay = 10;
    private final int shipmentPerDay = 5;
    private final List<Faction> factions;
    private static final Random RANDOM = new Random();

    public ProductionProcess(List<Faction> factions) {
        this.factions = factions;
    }

    public void simulateProductionCycle() {
        System.out.println("--- Запуск симуляции производственного процесса на " + days + " дней ---");

        for (int day = 1; day <= days; day++) {
            System.out.println("\n*** День " + day + " ***");

            System.out.println("-> Начало дня. Запуск производства.");
            List<Part> producedParts = produceParts(shipmentPerDay * factions.size());
            System.out.println("Произвели " + factions);
            System.out.println("<- Конец дня. Прибыли фракции для отгрузки.");

            AtomicInteger counter = new AtomicInteger(0);
            factions.forEach(faction -> {
                int fromIndex = shipmentPerDay * counter.get();
                counter.getAndIncrement();
                int toIndex = shipmentPerDay * counter.get();
                List<Part> parts = producedParts.subList(fromIndex, toIndex);
                faction.addDetails(parts);
            });

        }

        factions.forEach(faction -> {
            System.out.println(faction.getName());
            long minCount = faction.getParts().stream()
                    .collect(Collectors.groupingBy(part -> part, Collectors.counting()))
                    .values()
                    .stream()
                    .mapToLong(Long::longValue)
                    .min()
                    .orElse(0L);
            System.out.printf("количество роботов - %d\n", minCount);
        });
    }

    public List<Part> produceParts(int count) {
        List<Part> parts = new ArrayList<>();
        for(int i = 0; i < count; i++) {
            parts.add(Part.fromCode(RANDOM.nextInt(Part.values().length)).orElseThrow(() -> new RuntimeException("упс")));
        }
        return parts;
    }
}
