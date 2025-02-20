package org.example.fish;

import org.example.fish.FishManage.FishFactory;

public class Main {
    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            FishFactory.createRandomFish();
        }
    }
}
