package org.example.Skynet.faction;


import org.example.Skynet.Part;

import java.util.List;

public interface Faction {
    List<Part> getParts();
    String getName();
    int addDetails(List<Part> parts);
}
