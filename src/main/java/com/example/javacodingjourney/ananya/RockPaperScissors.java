package com.example.javacodingjourney.ananya;

import java.util.Scanner;
import java.util.Random;

public class RockPaperScissors {

    // Constants for the game choices
    private static final String ROCK = "ROCK";
    private static final String PAPER = "PAPER";
    private static final String SCISSORS = "SCISSORS";

    // Keep track of scores
    private int playerScore = 0;
    private int computerScore = 0;
    private int ties = 0;

    // Scanner for user input and Random for computer choice
    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();

    public static void main(String[] args) {
        RockPaperScissors game = new RockPaperScissors();
        game.startGame();
    }

    // Main game loop
    public void startGame() {
        System.out.println("🎮 Welcome to Rock-Paper-Scissors! 🎮");
        System.out.println("=====================================");

        boolean playAgain = true;

        while (playAgain) {
            playRound();
            displayScore();
            playAgain = askToPlayAgain();
        }

        System.out.println("\n🎯 Final Score 🎯");
        displayScore();
        System.out.println("\nThanks for playing! Goodbye! 👋");
        scanner.close();
    }

    // Play a single round
    private void playRound() {
        System.out.println("\n--- New Round ---");

        // Get player's choice
        String playerChoice = getPlayerChoice();

        // Get computer's choice
        String computerChoice = getComputerChoice();

        // Display choices with emojis
        System.out.println("\nYou chose: " + getChoiceWithEmoji(playerChoice));
        System.out.println("Computer chose: " + getChoiceWithEmoji(computerChoice));

        // Determine and display winner
        determineWinner(playerChoice, computerChoice);
    }

    // Get player's choice with input validation
    private String getPlayerChoice() {
        System.out.println("\nEnter your choice:");
        System.out.println("1. Rock 🪨");
        System.out.println("2. Paper 📄");
        System.out.println("3. Scissors ✂️");
        System.out.print("Your choice (1-3): ");

        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 1:
                        return ROCK;
                    case 2:
                        return PAPER;
                    case 3:
                        return SCISSORS;
                    default:
                        System.out.print("Invalid choice! Please enter 1, 2, or 3: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Please enter a number (1-3): ");
            }
        }
    }

    // Generate computer's random choice
    private String getComputerChoice() {
        int choice = random.nextInt(3); // 0, 1, or 2

        switch (choice) {
            case 0:
                return ROCK;
            case 1:
                return PAPER;
            case 2:
                return SCISSORS;
            default:
                return ROCK; // This shouldn't happen
        }
    }

    // Determine the winner of the round
    private void determineWinner(String playerChoice, String computerChoice) {
        if (playerChoice.equals(computerChoice)) {
            System.out.println("🤝 It's a TIE!");
            ties++;
        } else if (isPlayerWinner(playerChoice, computerChoice)) {
            System.out.println("🎉 You WIN this round!");
            playerScore++;
        } else {
            System.out.println("💻 Computer WINS this round!");
            computerScore++;
        }
    }

    // Check if player wins based on game rules
    private boolean isPlayerWinner(String player, String computer) {
        return (player.equals(ROCK) && computer.equals(SCISSORS)) ||
                (player.equals(PAPER) && computer.equals(ROCK)) ||
                (player.equals(SCISSORS) && computer.equals(PAPER));
    }

    // Add emoji to choice for fun display
    private String getChoiceWithEmoji(String choice) {
        switch (choice) {
            case ROCK:
                return "Rock 🪨";
            case PAPER:
                return "Paper 📄";
            case SCISSORS:
                return "Scissors ✂️";
            default:
                return choice;
        }
    }

    // Display current score
    private void displayScore() {
        System.out.println("\n📊 SCOREBOARD 📊");
        System.out.println("================");
        System.out.println("You: " + playerScore + " wins");
        System.out.println("Computer: " + computerScore + " wins");
        System.out.println("Ties: " + ties);
        System.out.println("================");
    }

    // Ask if player wants to play again
    private boolean askToPlayAgain() {
        System.out.print("\nDo you want to play again? (yes/no): ");
        String answer = scanner.nextLine().trim().toLowerCase();

        return answer.equals("yes") || answer.equals("y");
    }
}