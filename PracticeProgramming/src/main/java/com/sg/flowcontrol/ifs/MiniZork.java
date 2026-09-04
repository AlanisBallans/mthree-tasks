package com.sg.flowcontrol.ifs;

import java.util.Scanner;

public class MiniZork {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        System.out.println("You are standing in an open field west of a white house,");
        System.out.println("With a boarded front door.");
        System.out.println("There is a small mailbox here.");
        System.out.println("Go to the house, or open the mailbox? ");

        String action = userInput.nextLine();

        if (action.equals("open the mailbox")) {
            System.out.println("You open the mailbox.");
            System.out.println("It's really dark in there.");
            System.out.println("Look inside or stick your hand in?");

            action = userInput.nextLine();

            if (action.equals("look inside")) {
                System.out.println("You peer inside the mailbox.");
                System.out.println("It's really dark. So... so very dark.");
                System.out.println("Run away or keep looking?");

                action = userInput.nextLine();

                if (action.equals("keep looking")) {
                    System.out.println("Turns out, hanging out around dark places isn't a good idea.");
                    System.out.println("You've been eaten by a grue.");
                } else if (action.equals("run away")) {
                    System.out.println("You run away screaming across the fields - looking very foolish.");
                    System.out.println("But you alive. Possibly a wise choice.");
                }
            }
        } else if (action.equals("go to the house")) {
            System.out.println("You walk up to the house.");
            System.out.println("It is dilapidated and smells rotten.");
            System.out.println("Kick the door in or climb in through the window? ");

            action = userInput.nextLine();

            if (action.equals("kick the door in")) {
                System.out.println("You kick the boarded door with all you have.");
                System.out.println("It didn't really work. You've injured your foot (ouch!)");
                System.out.println("Your bashing has disturbed the Resident. You were consumed.");

            } else if (action.equals("climb in through the window")) {
                System.out.println("You find a gap in the window to crawl through.");
                System.out.println("The inside of the house is even more miserable than the outside.");
                System.out.println("There is a foul odor coming from what you assume is the kitchen, and a suspicious creaking coming from upstairs.");
                System.out.println("Go to the kitchen or go upstairs?");

                action = userInput.nextLine();

                if (action.equals("go to the kitchen")) {
                    System.out.println("You enter the kitchen, where the stench is unbearable.");
                    System.out.println("The large, vaguely humanoid entity butchers its most recent catch: you.");
                } else if (action.equals("go upstairs")) {
                    System.out.println("You climb the creaky stairs.");
                    System.out.println("You hear creaking three steps behind you and know it is time to accept your fate.");
                }
            }
        }

    }
}
