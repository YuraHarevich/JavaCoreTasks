package org.example.Skynet;


import org.example.Skynet.faction.Faction;
import org.example.Skynet.faction.impl.Wednesday;
import org.example.Skynet.faction.impl.World;

import java.util.List;

public class RobotCalculator {
    public static void main(String[] args) {
        ProductionProcess process;
        List<Faction> factions = List.of(new Wednesday(), new World());
        process = new ProductionProcess(factions);

        process.simulateProductionCycle();
    }
}