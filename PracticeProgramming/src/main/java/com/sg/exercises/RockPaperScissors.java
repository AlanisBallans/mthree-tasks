package com.sg.exercises;

import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {

    private Scanner scanner = new Scanner(System.in);
    private Random rng = new Random();

    public static void main(String[] args) {

        RockPaperScissors rps = new RockPaperScissors();

        while (true) { // Plays a game on run, and as long as the answer is "Yes"
            rps.playGame();

            System.out.print("Would you like to play again? ");
            String playAgain = rps.scanner.nextLine();

            if (!playAgain.equals("Yes")) {
                System.out.println("Thanks for playing!");
                return;
            }
        }

    }

    void playGame() {
        int[] winDrawLoss = {0, 0, 0}; // Tracks *user's* w/d/l statistic

        System.out.print("How many rounds would you like to play (1-10)? ");
        int rounds = -1;

        try {
            rounds = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.err.print("Valid integer not entered");
            return;
        }

        if (rounds < 1 || rounds > 10) {
            System.err.println("Valid number of rounds not entered");
            return;
        }

        for (int i = 0; i < rounds; i++) {

            int userChoice = -1;

            try {
                System.out.println("What do you play: Rock (1), Paper (2) or Scissors (3)? ");
                userChoice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Valid integer not entered");
                i--;
                continue;
            }

            if (userChoice < 1 || userChoice > 3) {
                System.err.println("Valid move not entered");
                i--;
                continue;
            }

            int computerChoice = rng.nextInt(3) + 1;
            String stringComputerChoice = "";
            switch (computerChoice) {
                case 1:
                    stringComputerChoice = "Rock";
                    break;
                case 2:
                    stringComputerChoice = "Paper";
                    break;
                case 3:
                    stringComputerChoice = "Scissors";
                    break;
            }

            System.out.println("Your opponent chose... " + stringComputerChoice);

            winDrawLoss[determineWinner(userChoice, computerChoice)]++;
        }

        System.out.println("Overall...");
        System.out.println("You won " + winDrawLoss[0] + " rounds,");
        System.out.println("You drew " + winDrawLoss[1] + " rounds,");
        System.out.println("And you lost " + winDrawLoss[2] + " rounds.");

        if (winDrawLoss[0] < winDrawLoss[2]) {
            System.out.println("You lost Rock Paper Scissors!");
            return;
        }
        if (winDrawLoss[0] == winDrawLoss[2]) {
            System.out.println("You tied at Rock Paper Scissors!");
            return;
        }
        System.out.println("You won Rock Paper Scissors!");
    }

    // Returns index of which of win/draw/loss occurred this round
    int determineWinner(int player, int computer) {
        if (player == computer) { // Draw if equal
            System.out.println("It's a draw!");
            return 1;
        }

        if ((player + 1) % 3 == computer % 3) {  // Treating this cyclically, rock loses to paper loses to
            System.out.println("You lose!");    // scissors loses to rock, so it'll be a loss if the computer
            return 2;                           // picks the option one "above"
        }

        System.out.println("You win!");
        return 0; // If not a loss or draw, it's a win
    }
}
