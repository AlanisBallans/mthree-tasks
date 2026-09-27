package com.sg.foundations.scanner;

import java.util.Scanner;

public class MiniMadLibs {
    public static void main(String[] args) {
        System.out.println("Let's play MAD LIBS!");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Gimme a noun! ");
        String word1 = scanner.nextLine();

        System.out.println("Gimme an adjective! ");
        String word2 = scanner.nextLine();

        System.out.println("Gimme another noun! ");
        String word3 = scanner.nextLine();

        System.out.println("Gimme a number! ");
        String word4 = scanner.nextLine();

        System.out.println("Gimme another adjective! ");
        String word5 = scanner.nextLine();

        System.out.println("Gimme a plural noun! ");
        String word6 = scanner.nextLine();

        System.out.println("Gimme another plural noun! ");
        String word7 = scanner.nextLine();

        System.out.println("Gimme ANOTHER plural noun! ");
        String word8 = scanner.nextLine();

        System.out.println("Gimme a verb (present tense)! ");
        String word9 = scanner.nextLine();

        System.out.println("Gimme that in past tense!! ");
        String word10 = scanner.nextLine();

        System.out.print("*** NOW LET'S GET MAD (libs) ***");
        System.out.println(word1 + ": the " + word2 + " frontier. These are the voyages of the starship " + word3 + ". Its " + word4 + "-year mission: to explore strange " + word5 + word6 + ", to seek out " + word5 + " " + word7 + " and " + word5 + word8 + ", to boldly " + word9 + " where no one has " + word10 + " before.");
    }
}
