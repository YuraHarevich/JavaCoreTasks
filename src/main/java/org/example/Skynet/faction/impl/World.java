package org.example.Skynet.faction.impl;


import org.example.Skynet.Part;
import org.example.Skynet.faction.Faction;

import java.util.ArrayList;
import java.util.List;

public class World implements Faction {

    private List<Part> parts;

    public World() {
        parts = new ArrayList<>();
    }

    public String getName() {
        return "World";
    }

    public int addDetails(List<Part> additionalParts) {
        parts.addAll(additionalParts);
        return parts.size();
    }

    public List<Part> getParts() {
        return parts;
    }
}
