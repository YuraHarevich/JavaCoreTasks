package org.example.Skynet.ObjectFactory.impl;

import org.example.Skynet.ObjectFactory.ObjectFactory;
import org.example.Skynet.Part;

import java.util.Random;

public class NeutralFactory implements ObjectFactory {
    private final Random random = new Random();
    private final Part[] parts = Part.values();


    public Part produce() {
        return parts[random.nextInt(parts.length)];
    }

}
