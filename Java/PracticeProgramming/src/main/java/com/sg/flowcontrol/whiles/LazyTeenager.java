package com.sg.flowcontrol.whiles;

import java.util.Random;

public class LazyTeenager {
    public static void main(String[] args) {
        Random rng = new Random();

        boolean clean = false;
        int timesTold = 0;

        while (!clean) {
            System.out.print("Clean your room!!");
            timesTold++;
            if (timesTold > 7) {
                System.out.println("That's IT, I'm going it!!! YOU'RE GROUNDED AND I'M TAKING YOUR XBOX!");
                break;
            }
            System.out.println("(x" + timesTold + ")");
            double cleanValue = rng.nextDouble();
            if (cleanValue * 10 < timesTold) {
                System.out.println("FINE! I'LL CLEAN MY ROOM. BUT I REFUSE TO EAT MY PEAS.");
                clean = true;
            }
;

        }

    }
}
