package org.example.Skynet;

import java.util.Arrays;
import java.util.Optional;

public enum Part {
    HEAD(0),
    TORSO(1),
    HAND(2),
    FEET(3);

    private final int partCode;

    Part(int partCode) {
        this.partCode = partCode;
    }

    public static Optional<Part> fromCode(int code) {
        return Arrays.stream(Part.values())
                .filter(part -> part.partCode == code)
                .findAny();
    }

}
