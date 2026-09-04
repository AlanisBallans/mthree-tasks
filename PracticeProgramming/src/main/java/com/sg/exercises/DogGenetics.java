package com.sg.exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class DogGenetics {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random rng = new Random();

        int total = 100;
        List<String> breeds = new ArrayList<>();
        breeds.add("Doberman");
        breeds.add("Shiba Inu");
        breeds.add("Weimeraner");
        breeds.add("Australian Shepherd");
        breeds.add("Border Collie");

        System.out.print("What is your dog's name? ");
        String name = scanner.nextLine();

        System.out.println(name + " is:");

        for (int i = 0; i < 4; i++) {
            int percentage = rng.nextInt(total - (4-i) + 1); // makes sure there is enough % left for the rest of the breeds
            total -= percentage;
            String breed = breeds.get(rng.nextInt(breeds.size()));
            breeds.remove(breed);
            System.out.println(percentage + "% " + breed);
        }
        System.out.println(total + "% " + breeds.get(0));

        System.out.println("Wow, that's QUITE the dog!");

    }
}
