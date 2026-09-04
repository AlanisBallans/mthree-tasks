package com.sg.exercises;

import java.util.Scanner;

public class HealthyHearts {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("What is your age? ");
        int age = Integer.parseInt(scanner.nextLine());

        int maxRate = 220 - age;
        int minTarget = Math.round((float) (maxRate * 0.5));
        int maxTarget = Math.round((float) (maxRate * 0.85));

        System.out.println("Your maximum heart rate should be " + maxRate + " beats per minute");
        System.out.println("Your target HR Zone is " + minTarget + " - " + maxTarget + " beats per minute");
    }
}
