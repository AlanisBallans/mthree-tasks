package com.sg.flowcontrol.random;

import java.util.Random;
import java.util.Scanner;

public class GuessMeMore {

    public static void main(String[] args) {
        Random rng = new Random();
        int num = rng.nextInt(201) - 100;

        System.out.println("I've chosen a number between -100 and 100. Bet you can't guess it!");
        System.out.print("Your guess: ");

        Scanner scanner = new Scanner(System.in);
        int guess = Integer.parseInt(scanner.nextLine());

        if (guess != num) {
            if (guess < num) {
                System.out.println("Ha, nice try - too low! Try again!");
            } else {
                System.out.println("You've overshot a little, try again!");
            }
            System.out.print("Your guess: ");

            guess = Integer.parseInt(scanner.nextLine());
            if (guess != num) {
                System.out.println("You didn't get it! The number was " + num);
                return;
            }
            System.out.println("Wow, nice guess! That was it!");
            return;
        }
        System.out.println("Wow, first try! That was it!");
    }
}
