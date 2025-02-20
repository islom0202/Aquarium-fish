package org.example.fish.FishManage;

import java.util.Random;

public class FishFactory {
    private static final Random random = new Random();


    public static Fish createRandomFish(){
        String gender = random.nextBoolean() ? "male" : "female";
        int lifespan = random.nextInt(5) + 5;
        Fish fish = new Fish(gender, lifespan);
        Thread thread = new Thread(fish);
        thread.start();
        return fish;
    }
}
