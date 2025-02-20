package org.example.fish;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Akvarium uzunligini kiriting: ");
        int length = scanner.nextInt();
        System.out.print("Akvarium kengligini kiriting: ");
        int width = scanner.nextInt();
        System.out.print("Akvarium balandligini kiriting: ");
        int height = scanner.nextInt();
        System.out.print("Akvarium sig‘imini kiriting: ");
        int capacity = scanner.nextInt();

        Fish.setAquariumSize(length, width, height, capacity);

        System.out.print("Nechta baliq qo‘shmoqchisiz? \nerkak: ");
        int maleFishNumber = scanner.nextInt();

        System.out.print("urg`ochi: ");
        int femaleFishNumber = scanner.nextInt();

        for (int i = 0; i < maleFishNumber; i++) {
            Fish fish = new Fish("erkak", 8);
            fish.start();
        }

        for (int i = 0; i < femaleFishNumber; i++) {
            Fish fish = new Fish("urg`ochi", 8);
            fish.start();
        }
    }
}
