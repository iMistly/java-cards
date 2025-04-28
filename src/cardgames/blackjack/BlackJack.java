package cardgames.blackjack;
import cardgames.common.GameTools;
import cardgames.common.Deck;
import java.util.Scanner;

public class BlackJack {
    // Necessary parts of the game
    private Deck deck;
    private BlackJackHand playerHand;
    private BlackJackHand dealerHand;
    
    // Game variables
    boolean playerTurn;
    boolean gameActive;
    
    private static final int BLACKJACK = 21;
    // Officially, the dealer must stand on 17 or higher
    private static final int DEALER_STAND = 17;

    // Initialize the game
    public BlackJack(){
        this.deck = new Deck();
        this.playerHand = new BlackJackHand();
        this.dealerHand = new BlackJackHand(true);
    }

    // Start a new game
    public void newGame() {
        GameTools.clearConsole();
        System.out.print("Starting a new game of BlackJack");
        GameTools.wait(2000, true);
        this.deck.initializeDeck();
        System.out.print("Shuffling the deck");
        GameTools.wait(2000, true);
        this.deck.shuffle();
        this.playerHand.clear();
        this.dealerHand.clear();
        this.dealInitialCards();
        this.playerTurn = true;
        this.gameActive = true;
        this.play();
    }

    private void dealInitialCards() {
        System.out.print("Dealing initial cards");
        GameTools.wait(2000, true);
        // Deal two cards to the player and dealer
        for (int i = 0; i < 2; i++) {
            this.playerHand.addCard(this.deck.dealCard());
            this.dealerHand.addCard(this.deck.dealCard());
        }
        GameTools.wait(1000);
        // Check for blackjack
        if (this.playerHand.getHandValue() == BLACKJACK && this.dealerHand.getHandValue() == BLACKJACK) {
            System.out.println("Both player and dealer have blackjack! That's crazy! It's a tie!");
            this.showHands(true);
            gameActive = false;
        } 
        else if (this.playerHand.getHandValue() == BLACKJACK) {
            System.out.println("Player has a blackjack! That's amazing! Player wins!");
            this.showHands(true);
            gameActive = false;
        } 
        else if (this.dealerHand.getHandValue() == BLACKJACK) {
            System.out.println("Dealer has a blackjack! Sucks to be you! Dealer wins!");
            this.showHands(true);
            gameActive = false;
        }
    }

    private void showHands() {
        System.out.printf("Player's Hand: == %d\n", this.playerHand.getHandValue());
        this.playerHand.showHand();
        System.out.printf("Dealer's Hand: >= %d\n", this.dealerHand.getHandValue());
        this.dealerHand.showHand();
    }

    private void showHands(boolean showAll) {
        System.out.printf("Player's Hand: == %d\n", this.playerHand.getHandValue());
        this.playerHand.showHand();
        System.out.printf("Dealer's Hand: == %d\n", this.dealerHand.getHandValue());
        this.dealerHand.showHand(showAll);
    }

    public void play() {
        GameTools.clearConsole();

        Scanner scanner = new Scanner(System.in);

        while(gameActive){
            
            if(playerTurn){
                this.showHands();
                System.out.println("Player's turn. Choose an action: (h)it, (s)tand");
                
                String action = scanner.nextLine(); // Get user input
                // Small delay to simulate thinking time
                GameTools.wait(200);
                if(action.equals("h")){
                    this.playerHand.addCard(this.deck.dealCard());
                    this.showHands();
                    if(this.playerHand.getHandValue() > BLACKJACK){
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("Player busts! Dealer wins.");
                        this.showHands(true);
                        GameTools.wait(4000);
                        gameActive = false;
                    }
                }
                else if(action.equals("s")){
                    playerTurn = false;
                }
                // Clear the console after player's turn
                GameTools.clearConsole();
            }
            else{
                dealerTurn();
            }
        }
        scanner.close();
    }

    private void dealerTurn() {
        while(this.dealerHand.getHandValue() < DEALER_STAND){
            GameTools.clearConsole();
            System.out.print("Dealer's turn. Must hit until reaching " + DEALER_STAND);
            // Wait for a moment before dealer's turn
            GameTools.wait(1500, true);
            this.dealerHand.addCard(this.deck.dealCard());
            this.showHands();
            // Give the player a moment to see the dealer's hand
            GameTools.wait(1000);
        }
        determineWinner();
    }
    private void determineWinner() {
        int playerValue = this.playerHand.getHandValue();
        int dealerValue = this.dealerHand.getHandValue();

        GameTools.clearConsole();

        if(dealerValue > BLACKJACK){
            System.out.println("Dealer busts! Player wins.");
        }
        else if(playerValue > dealerValue){
            System.out.println("Player wins!");
        }
        else if(playerValue < dealerValue){
            System.out.println("Dealer wins!");
        }
        else{
            System.out.println("It's a tie!");
        }
        GameTools.wait(1000);
        System.out.println("Final Hands:");
        this.showHands(true);
        gameActive = false;
    }

    public void endGame() {
        System.out.println("Game over.");
        this.gameActive = false;
    }

    public boolean isGameActive() {
        return gameActive;
    }
}
