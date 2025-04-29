package cardgames.highcard;

import cardgames.common.Card;
import cardgames.common.Game;
import cardgames.common.GameTools;
import java.util.Scanner;

public class HighCard extends Game {

    // Scanner for user input
    private Scanner scanner;

    // Initialize the game
    public HighCard() {
        super(); 
        this.scanner = new Scanner(System.in);
    }

    // Method to start the game
    public static void main(String[] args) {
        HighCard game = new HighCard();
        game.newGame();
    }

    // Start a new game
    @Override
    public void newGame() {
        GameTools.clearConsole(); // Clear console at the start of a new game
        System.out.print("Starting a new game of High Card");
        GameTools.wait(2000, true); 

        super.deck.initializeDeck(); // Initialize a new deck
        System.out.print("Shuffling the deck");
        GameTools.wait(2000, true); 
        super.deck.shuffle();

        System.out.println("\nDeck initialized and shuffled. There are " + deck.remainingCards() + " cards in the deck.");

        super.gameActive = true; 
        this.play();
    }

    @Override
    public void play() {
        while (isGameActive()) {
            GameTools.wait(1000); 

            // Check if there are enough cards to deal for a round
            if (deck.remainingCards() < 2) {
                System.out.println("Not enough cards left in the deck to play another round.");
                endGame(); 
                break; 
            }

            System.out.println("\n--- New Round ---");
            GameTools.wait(1000);

            // Deal cards to player and dealer
            Card playerCard = deck.dealCard();
            Card dealerCard = deck.dealCard();

            // Displays cards drawn
            System.out.println("You drew: " + playerCard.getValue());
            GameTools.wait(2000, true); 
            System.out.println("Dealer drew: " + dealerCard.getValue());
            GameTools.wait(2000);

            // Winner is determined
            RoundWinner(playerCard, dealerCard);

            System.out.println("Cards remaining in deck: " + deck.remainingCards());

            // Ask to play another round if there are enough cards
            if (deck.remainingCards() >= 2) { // Ensure enough cards for the next round
                System.out.print("Play another round? (y/n): ");
                String response = scanner.nextLine().trim().toLowerCase();
                if (!response.equals("y")) {
                    endGame(); // End game if user doesn't say "yes"
                }
            } else {
                System.out.println("Deck is almost empty.");
                endGame(); // End game if not enough cards for the next round
            }
             // Clear the console after each round
            GameTools.clearConsole();
        }
        //scanner.close(); 
    }

    // Winner method
    private void RoundWinner(Card playerCard, Card dealerCard) {
        int comparison = compareCards(playerCard, dealerCard);

        if (comparison > 0) {
            System.out.println("You win this round!");
        } else if (comparison < 0) {
            System.out.println("Dealer wins this round!");
        } else {
            System.out.println("It's a tie!");
        }
        GameTools.wait(2000); 
    }

    // Method to compare cards based on rank
    // Returns positive if card1 > card2, negative if card1 < card2, 0 if equal
    private int compareCards(Card card1, Card card2) {
        int rank1Value = getRankValue(card1.rank);
        int rank2Value = getRankValue(card2.rank);

        return Integer.compare(rank1Value, rank2Value);
    }

    // Helper method, gets numerical value for card rank
    private int getRankValue(String rank) {
        for (int i = 0; i < Card.VALID_RANKS.length; i++) {
            if (Card.VALID_RANKS[i].equals(rank)) {
                return i; // Return the index as the value (A=0, 2=1, ..., K=12)
            }
        }
        return -1; // Should not happen with valid cards
    }

}
