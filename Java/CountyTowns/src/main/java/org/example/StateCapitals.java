package org.example;

import org.w3c.dom.ls.LSInput;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.*;

public class StateCapitals {

    public static void main(String[] args) {
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(new BufferedReader(new FileReader("StateCapitals.txt")));
        } catch (Exception e) {
            System.out.println();
            System.err.println("File reader error: " + e);
            return;
        }

        Map<String, String> stateCapitalPairs = new HashMap<>();

        while (fileScanner.hasNext()) {
            String[] scPairArr = fileScanner.nextLine().split("::");
            stateCapitalPairs.put(scPairArr[0], scPairArr[1]);
        }
        fileScanner.close();

        System.out.println(stateCapitalPairs.size() + " STATES AND CAPITALS ARE LOADED.");
        System.out.println("=======");
        System.out.println("HERE ARE THE STATES:");
        Set<String> states = stateCapitalPairs.keySet();
        for (String state : states) System.out.println(state + ", ");

        String[] stateArr = states.toArray(new String[0]);

        Random rng = new Random();
        String randomState = stateArr[rng.nextInt(stateArr.length)];
        System.out.println("READY TO TEST YOUR KNOWLEDGE? WHAT IS THE CAPITAL OF '" + randomState + "'?");

        Scanner inputScanner = new Scanner(System.in);
        String answer = inputScanner.nextLine();

        if (answer.equals(stateCapitalPairs.get(randomState))) {
            System.out.println("NICE WORK! " + answer + " IS CORRECT!");
            return;
        }
        System.out.println("THAT IS INCORRECT! THE ANSWER IS " + stateCapitalPairs.get(randomState));


    }
}
