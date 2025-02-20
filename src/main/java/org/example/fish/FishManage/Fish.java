package org.example.fish.FishManage;

import java.util.Random;

public class Fish implements Runnable {
    private final String gender;
    private int lifespan;
    private int x, y, z;
    private static final Random random = new Random();
    private static final Aquarium aquarium = Aquarium.getInstance();

    protected Fish(String gender, int lifespan) {
        this.gender = gender;
        this.lifespan = lifespan;

        this.x = random.nextInt(aquarium.getMaxX() + 1);
        this.y = random.nextInt(aquarium.getMaxY() + 1);
        this.z = random.nextInt(aquarium.getMaxZ() + 1);

        aquarium.addFish(this);
    }

    @Override
    public void run() {
        System.out.println(gender + " baliq akvariumga qo‘shildi. Yashash muddati: " + lifespan + "s");
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
        aquarium.removeFish(this);

        checkAquariumStatus();
    }

    private void moveToSomewhere() {
        x = random.nextInt(aquarium.getMaxX() + 1);
        y = random.nextInt(aquarium.getMaxY() + 1);
        z = random.nextInt(aquarium.getMaxZ() + 1);
        System.out.println(gender + " baliq yangi joyga o'tdi: (" + x + ", " + y + ", " + z + ")");
    }

    private void checkCollision() {
        for (Fish otherFish : aquarium.getAllFishes()) {
            if (otherFish != this &&
                    this.x == otherFish.x &&
                    this.y == otherFish.y &&
                    this.z == otherFish.z) {
                if (!this.gender.equals(otherFish.gender)) {
                    System.out.println("To‘qnashuv: " + this.gender + " va " + otherFish.gender + " baliqlar uchrashdi.");
                    Fish newFish = FishFactory.createRandomFish();
                    aquarium.addFish(newFish);
                }
            }
        }
    }

    private void checkAquariumStatus() {
        if (aquarium.checkIfEmpty()) {
            System.out.println("Akvarium bo‘sh! Dastur yopilmoqda...");
            System.exit(0);
        }
        if (aquarium.getSize() >= aquarium.getMaxCapacity()) {
            System.out.println("Akvarium to‘ldi! Baliqlar sig‘mayapti.");
            System.exit(0);
        }
    }
}
