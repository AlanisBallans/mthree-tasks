package com.sg.flowcontrol.methods;

import java.util.Random;

public class BarelyControlledChaos {

    Random rng = new Random();

    public static void main(String[] args) {
        BarelyControlledChaos bcc = new BarelyControlledChaos();
        String color = bcc.color();
        String animal = bcc.animal();
        String colorAgain = bcc.color();
        int weight = bcc.number(5, 200);
        int distance = bcc.number(10, 20);
        int number = bcc.number(10000,20000);
        int time = bcc.number(2,6);

        System.out.println("Once, when I was very small...");

        System.out.println("I was chased by a " + color + ", " + weight + "lb miniature " + animal + " for over " + distance + " miles!!");

        System.out.println("I had to hide in a field of over " + number + " " + colorAgain + " poppies for nearly " + time + " hours until it left me alone!");

        System.out.println("\nIt was QUITE the experience, " + "let me tell you!");
    }

    public String color() {
        int num = rng.nextInt(5);
        switch (num) {
            case 0:
                return "red";
            case 1:
                return "blue";
            case 2:
                return "orange";
            case 3:
                return "pink";
            case 4:
                return "yellow";
            default:
                return "transparent";
        }
    }

    public String animal() {
        int num = rng.nextInt(5);
        switch (num) {
            case 0:
                return "binturong";
            case 1:
                return "bat";
            case 2:
                return "moth";
            case 3:
                return "vinegaroon";
            case 4:
                return "otter";
            default:
                return "cryptid";
        }
    }

    public int number(int min, int max) {
        int rngRange = max - min + 1;

        return rng.nextInt(rngRange) + min;
    }
}
