package org.example.fish.FishManage;

import lombok.Getter;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter
public class Aquarium {
    private static Aquarium instance;
    private final Random random = new Random();
    private final int maxX, maxY, maxZ, maxCapacity;
    private final List<Fish> fishes;

    private Aquarium() {
        this.maxX = random.nextInt(10) + 1;
        this.maxY = random.nextInt(10) + 1;
        this.maxZ = random.nextInt(10) + 1;
        this.maxCapacity = random.nextInt(10) + 5;

        fishes = new CopyOnWriteArrayList<>();
    }

    //todo : make a brainstorming here
    public static synchronized Aquarium getInstance() {
        if (instance == null) {
            instance = new Aquarium();
        }
        return instance;
    }

    public void addFish(Fish fish) {
        if (fishes.size() < maxCapacity) {
            fishes.add(fish);
        } else {
            System.out.println("Akvarium to‘la! Yangi baliq qo‘shib bo‘lmaydi.");
        }
    }

    public void removeFish(Fish fish) {
        fishes.remove(fish);
    }

    public List<Fish> getAllFishes() {
        return fishes;
    }

    public boolean checkIfEmpty() {
        return fishes.isEmpty();
    }

    public int getSize() {
        return fishes.size();
    }
}
