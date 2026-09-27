package com.sg.exercises;

import java.util.Scanner;

public class InterestCalculator {

    public static void main(String[] args) {
        InterestCalculator ic = new InterestCalculator();
        Scanner scanner = new Scanner(System.in);
        System.out.print("How much do you want to invest? ");
        double principal = ic.readDouble(scanner);
        System.out.print("How many years are you investing? ");
        int years = ic.readInt(scanner);
        System.out.print("What is the annual interest rate % growth? ");
        int interestPercent = ic.readInt(scanner);

        System.out.print("Compounded quarterly, monthly, or daily (quarterly by default)? ");
        String stringCompoundInterval = scanner.nextLine();

        int compoundPerYear = 4;
        switch (stringCompoundInterval) {
            case "monthly":
                compoundPerYear = 12;
                break;
            case "daily":
                compoundPerYear = 365;
            default:
                break;
        }


        System.out.println("Calculating...");
        for (int i = 1; i <= years; i++) {
            System.out.println("Year " + i + ":");
            System.out.println("Began with $" + principal);
            double principalAfter = ic.calculateInterest(principal,interestPercent,compoundPerYear);
            System.out.println("Earned $" + (principalAfter - principal));
            System.out.println("Ended with $" + principalAfter);
            principal = principalAfter;
        }
    }

    public double calculateInterest(double principal, int interestPercent, int compoundPerYear) {
        double interest = (double) interestPercent / 100 / compoundPerYear;

        return (double) Math.round(principal * Math.pow(1 + interest, compoundPerYear) * 100) /100;
    }

    public int readInt(Scanner scanner) {
        return Integer.parseInt(scanner.nextLine());
    }

    public double readDouble(Scanner scanner) {
        return Double.parseDouble(scanner.nextLine());
    }
}
