package cardgames.blackjack;
import cardgames.common.Game;
import cardgames.common.GameTools;
import java.util.Scanner;

public class BlackJack extends Game {
    // Necessary parts of the game
    private BlackJackHand playerHand;
    private BlackJackHand dealerHand;
    
    // Game variables
    boolean playerTurn;
    
    private static final int BLACKJACK = 21;
    // Officially, the dealer must stand on 17 or higher
    private static final int DEALER_STAND = 17;

    // Initialize the game
    public BlackJack(){
        super();
        this.playerHand = new BlackJackHand();
        this.dealerHand = new BlackJackHand();
    }

    // Start a new game
    @Override
    public void newGame() {
        GameTools.clearConsole();
        System.out.print("Starting a new game of BlackJack");
        GameTools.wait(2000, true);
        super.deck.initializeDeck();
        System.out.print("Shuffling the deck");
        GameTools.wait(2000, true);
        super.deck.shuffle();
        this.playerHand.clear();
        this.dealerHand.clear();
        this.dealInitialCards();
        super.gameActive = true;
        this.playerTurn = true;
        this.play();
    }

    private void dealInitialCards() {
        System.out.print("Dealing initial cards");
        GameTools.wait(2000, true);
        // Deal two cards to the player and dealer
        for (int i = 0; i < 2; i++) {
            this.playerHand.addCard(super.deck.dealCard());
            this.dealerHand.addCard(super.deck.dealCard());
        }
        GameTools.wait(1000);
        // Check for blackjack
        if (this.playerHand.getHandValue() == BLACKJACK && this.dealerHand.getHandValue() == BLACKJACK) {
            System.out.println("Both player and dealer have blackjack! That's crazy! It's a tie!");
            this.showHands();
            gameActive = false;
        } 
        else if (this.playerHand.getHandValue() == BLACKJACK) {
            System.out.println("Player has a blackjack! That's amazing! Player wins!");
            this.showHands();
            gameActive = false;
        } 
        else if (this.dealerHand.getHandValue() == BLACKJACK) {
            System.out.println("Dealer has a blackjack! Sucks to be you! Dealer wins!");
            this.showHands();
            gameActive = false;
        }
        // Hide dealer's first card
        this.dealerHand.flipCard(0);
    }

    private void showHands() {
        System.out.printf("Player's Hand: == %d\n", this.playerHand.getHandValue());
        this.playerHand.showHand();
        System.out.printf("Dealer's Hand: >= %d\n", this.dealerHand.getHandValue());
        this.dealerHand.showHand();
    }

    @Override
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
                    this.playerHand.addCard(super.deck.dealCard());
                    this.showHands();
                    if(this.playerHand.getHandValue() > BLACKJACK){
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("Player busts! Dealer wins.");
                        this.showHands();
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
        // Typically we'd close the scanner here, but we want to keep it open for the next game
        // scanner.close();
    }

    private void dealerTurn() {
        while(this.dealerHand.getHandValue() < DEALER_STAND){
            GameTools.clearConsole();
            System.out.print("Dealer's turn. Must hit until reaching " + DEALER_STAND);
            // Wait for a moment before dealer's turn
            GameTools.wait(1500, true);
            this.dealerHand.addCard(super.deck.dealCard());
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
        this.dealerHand.flipCard(0);
        this.showHands();
        super.gameActive = false;
        System.out.print("Ending game");
        GameTools.wait(2000, true);
    }
}
