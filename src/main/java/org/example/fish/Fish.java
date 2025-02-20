package org.example.fish;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

class Fish extends Thread {
    private final String gender;
    private int lifespan;
    private int x, y, z;
    private static int maxX, maxY, maxZ, maxCapacity;
    private static final Random random = new Random();
    private static List<Fish> aquarium = new CopyOnWriteArrayList<>();

    public Fish(String gender, int lifespan) {
        this.gender = gender;
        this.lifespan = lifespan;

        this.x = random.nextInt(maxX + 1);
        this.y = random.nextInt(maxY + 1);
        this.z = random.nextInt(maxZ + 1);

        aquarium.add(this);
    }


    @Override
    public void run() {
        System.out.println(gender + " baliq akvariumga qo'shildi. Yashash muddati: " + lifespan + "s");
        try {
            while (lifespan > 0) {
                Thread.sleep(1000);
                lifespan--;
                moveToSomewhere();
                checkCollision();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(gender + " baliq vafot etdi.");
        aquarium.remove(this);

        checkAquariumStatus();
    }

    private void moveToSomewhere() {
        x = random.nextInt(maxX + 1);
        y = random.nextInt(maxY + 1);
        z = random.nextInt(maxZ + 1);
        System.out.println(gender + " baliq yangi joyga harakat qildi: (" + x + ", " + y + ", " + z + ")");
    }

    private void checkCollision() {
        for (Fish fish : aquarium) {
            if (fish != this && fish.x == this.x && fish.y == this.y && fish.z == this.z) {
                if (!fish.gender.equals(this.gender)) {
                    spawnNewFish();
                }
            }
        }
    }

    private void spawnNewFish() {
        if (aquarium.size() >= maxCapacity) {
            System.out.println("Akvarium to'ldi! Yangi baliq yaratilmaydi.");
            return;
        }
        String newGender = random.nextBoolean() ? "Erkak" : "Urg'ochi";
        int newLifespan = random.nextInt(5) + 5;
        Fish newFish = new Fish(newGender, newLifespan);
        newFish.start();
        System.out.println("Yangi tug‘ilgan baliq: " + newGender);
    }

    private static void checkAquariumStatus() {
        if (aquarium.isEmpty()) {
            System.out.println("Barcha baliqlar vafot etdi. Akvarium yopilmoqda...");
            System.exit(0);
        }
    }

    public static void setAquariumSize(int length, int width, int height, int capacity) {
        maxX = length;
        maxY = width;
        maxZ = height;
        maxCapacity = capacity;
    }
}
