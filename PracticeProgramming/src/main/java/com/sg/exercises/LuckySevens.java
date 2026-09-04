package com.sg.exercises;

import java.util.Random;
import java.util.Scanner;

public class LuckySevens {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random rng = new Random();

        int currentRoll = 0;
        int[] maxRoll = {0,0}; // contains roll at max value, and max value

        System.out.print("How many dollars do you have? ");
        int money = Integer.parseInt(scanner.nextLine());

        while (money > 0) {
            if (money > maxRoll[1]) {
                maxRoll[0] = currentRoll;
                maxRoll[1] = money;
            }
            int diceValue = rng.nextInt(6) + rng.nextInt(6) + 2; // rolls both dice and adds 1 each to account for zero indexing
            currentRoll++;

            if (diceValue == 7) {
                money += 4;
                continue;
            }

            money--;
        }

        System.out.println("You are broke after " + currentRoll + " rolls.");
        System.out.println("You should have quit after " + maxRoll[0] + " rolls when you had $" + maxRoll[1] + ".");


    }
}
