// Typical Java imports
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

// Card game imports
import cardgames.common.GameTools;
import cardgames.blackjack.BlackJack;
import cardgames.common.Game;
import cardgames.highcard.HighCard;

// For testing and/or initiating a card game.
public class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        Map<Integer, String> options = new HashMap<>();
        options.put(0, "Exit");
        options.put(1, "BlackJack");
        options.put(2, "HighCard");

        Game game;

        while(true){
            GameTools.clearConsole();
            System.out.println("Welcome to our Card Game Suite!");
            System.out.println("Please select a number from the following games:");
            System.out.println("================================================");
            for (Map.Entry<Integer, String> entry : options.entrySet()) {
                System.out.println(entry.getKey() + ". " + entry.getValue());
            }
            System.out.println("================================================");

            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.print("Invalid input. Please enter a number");
                GameTools.wait(1000, true);
                scanner.next(); // Clear the invalid input
                continue;
            }

            if (choice == 0) {
                System.out.println("Exiting the game. Goodbye!");
                break;
            } else if (choice == 1) {
                game = new BlackJack();
            } else if (choice == 2) { // Add condition for HighCard
                game = new HighCard();
            } else{
                System.out.print("Invalid choice. Please try again");
                GameTools.wait(1000, true);
                continue;
            }
            // Start the game
            game.newGame();
            // Reset choice to prompt again
            choice = -1; 
        }
        scanner.close();
    }
}