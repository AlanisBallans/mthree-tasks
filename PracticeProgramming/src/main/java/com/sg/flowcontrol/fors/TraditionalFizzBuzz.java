package com.sg.flowcontrol.fors;
import java.util.Scanner;


public class TraditionalFizzBuzz {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("How many units of fizzing and buzzing do you need in your life? ");
        int max = Integer.parseInt(scanner.nextLine());

        int fizzBuzzCount = 0;
        int n = 1;
        System.out.println(0);
        while (fizzBuzzCount < max) {
            String output = "";
            if (n % 3 == 0) {
                output += "fizz ";
            }
            if (n % 5 == 0) {
                output += "buzz";
            }
            if (output.length() == 0) {
                output = Integer.toString(n);
            } else {
                fizzBuzzCount++;
            }
            System.out.println(output);
            n++;
        }
        System.out.println("TRADITION!!!!!");

    }
}
