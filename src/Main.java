
// Typical Java imports
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

// Additional card tools
import cardgames.common.GameTools;
import cardgames.common.Card;

// Card game imports
import cardgames.blackjack.BlackJack;
import cardgames.common.Game;
import cardgames.highcard.HighCard;

// For testing and/or initiating a card game.
public class Main {
    final static Object[] splashScreen = new Object[] {
        new Card("A", "S"), 
        new Card("K", "H"), 
        new Card("Q", "D"),
        new Card("J", "C")
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        Map<Integer, String> options = new HashMap<>();
        options.put(0, "Exit");
        options.put(1, "BlackJack");
        options.put(2, "HighCard");

        Game game;

        // ASCII config
        GameTools.clearConsole();
        System.out.println("Before we begin.");
        while (true) {
            System.out.println("Can you see these symbols? (y/n) -> ♣♥♠♦");
            String input = scanner.next().toLowerCase();
            if(input.equals("y")){
                GameTools.display_type = GameTools.DISPLAY_TYPES[1];
                break;
            }
            else if (input.equals("n")){
                GameTools.display_type = GameTools.DISPLAY_TYPES[0];
                break;
            }
            System.out.println("Invalid input!");
        }

        // Main game loop
        while (true) {
            GameTools.clearConsole();
            GameTools.printCardSequence(splashScreen);
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
            } else if (choice == 2) {
                game = new HighCard();
            } else {
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