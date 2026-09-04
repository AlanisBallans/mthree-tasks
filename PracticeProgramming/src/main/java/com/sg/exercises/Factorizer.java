package com.sg.exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Factorizer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Factorizer f = new Factorizer();

        System.out.print("What number would you like to factor? ");
        int num = Integer.parseInt(scanner.nextLine());

        List<Integer> factors = new ArrayList<>();

        for (int i = 1; i < num; i++) {
            if (num % i == 0) {
                factors.add(i);
            }
        }

        System.out.println("The factors of " + num + " are:");
        for (int factor: factors) {
            System.out.print(factor + " ");
        }

        System.out.println("\n" + num + " has " + factors.size() + " factors.");
        f.printIsPerfect(num, factors);
        f.printIsPrime(num, factors);

    }

    public void printIsPerfect(int num, List<Integer> factors) {
        int total = 0;
        for (int factor : factors) {
            total += factor;
        }
        if (total == num) {
            System.out.println(num + " is a perfect number.");
            return;
        }
        System.out.println(num + " is not a perfect number.");
    }

    public void printIsPrime(int num, List<Integer> factors) {
        if (factors.size() == 1) {
            System.out.println(num + " is a prime number.");
            return;
        }
        System.out.println(num + " is not a prime number.");
    }
}
