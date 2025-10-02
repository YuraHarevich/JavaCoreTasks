package org.example.Skynet.faction.impl;



import org.example.Skynet.Part;
import org.example.Skynet.faction.Faction;

import java.util.ArrayList;
import java.util.List;

public class Wednesday implements Faction {
    private List<Part> parts;

    public Wednesday() {
        parts = new ArrayList<>();
    }

    public int addDetails(List<Part> additionalParts) {
        parts.addAll(additionalParts);
        return parts.size();
    }

    public String getName() {
        return "Wednesday";
    }

    public List<Part> getParts() {
        return parts;
    }
}
