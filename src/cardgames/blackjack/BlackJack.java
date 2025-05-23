package cardgames.blackjack;
import cardgames.common.Game;
import cardgames.common.GameTools;
import java.util.Scanner;

public class BlackJack extends Game {
    // Necessary parts of the game
    private BlackJackHand playerHand;
    private BlackJackHand dealerHand;
    private Scanner scanner = new Scanner(System.in);

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
        GameTools.wait(1250, true);
        super.deck.initializeDeck();
        System.out.print("Shuffling the deck");
        GameTools.wait(1250, true);
        super.deck.shuffle();
        this.playerHand.clear();
        this.dealerHand.clear();
        super.gameActive = true;
        this.dealInitialCards();
        this.playerTurn = true;
        this.play();
    }

    private void dealInitialCards() {
        System.out.print("Dealing initial cards");
        GameTools.wait(1250, true);
        // Deal two cards to the player and dealer
        for (int i = 0; i < 2; i++) {
            this.playerHand.addCard(super.deck.dealCard());
            this.dealerHand.addCard(super.deck.dealCard());
        }
        GameTools.wait(1000);
        // Check for blackjack
        if (this.playerHand.getHandValue() == BLACKJACK && this.dealerHand.getHandValue() == BLACKJACK) {
            GameTools.clearConsole();
            gameActive = false;
            System.out.println("Both player and dealer have blackjack! What are the odds?!");
            this.showHands();
            GameTools.wait(4000, true);
            determineWinner();
        } 
        else if (this.playerHand.getHandValue() == BLACKJACK) {
            GameTools.clearConsole();
            gameActive = false;
            System.out.println("Player has a blackjack! That's amazing!");
            this.showHands();
            GameTools.wait(4000, true);
            determineWinner();
        } 
        else if (this.dealerHand.getHandValue() == BLACKJACK) {
            GameTools.clearConsole();
            gameActive = false;
            System.out.println("Dealer has a blackjack! Sucks to be you!");
            this.showHands();
            GameTools.wait(4000, true);
            determineWinner();
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
        

        while(gameActive){
            GameTools.clearConsole();
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
                        this.determineWinner();
                    }
                }
                else if(action.equals("s")){
                    playerTurn = false;
                }
            }
            else{
                dealerTurn();
            }
        }
        // Typically we'd close the scanner here, but we want to keep it open for the next game
        // scanner.close();
    }

    private void dealerTurn() {
        // Force flip dealer's hand
        this.dealerHand.setCardsFaceDown(false);

        GameTools.clearConsole();
        System.out.println("Dealer's turn. Must hit until reaching " + DEALER_STAND);
        this.showHands();
        GameTools.wait(2000, true);
        while(this.dealerHand.getHandValue() < DEALER_STAND){
            GameTools.clearConsole();
            System.out.println("Dealer's turn. Must hit until reaching " + DEALER_STAND);
            this.dealerHand.addCard(super.deck.dealCard());
            this.showHands();
            // Give the player a moment to see the dealer's hand
            GameTools.wait(2000);
        }
        determineWinner();
    }
    private void determineWinner() {
        // Force flip dealer's hand
        this.dealerHand.setCardsFaceDown(false);

        int playerValue = this.playerHand.getHandValue();
        int dealerValue = this.dealerHand.getHandValue();

        GameTools.clearConsole();

        if(dealerValue > BLACKJACK){
            System.out.println("Dealer busts! Player wins.");
        }
        else if(playerValue > BLACKJACK){
            System.out.println("Player busts! Dealer wins.");
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
        this.showHands();
        super.gameActive = false;
        System.out.println("\nPress Enter to continue.");
        // Clear previous line leftovers if any
        while (scanner.hasNextLine()) {
            scanner.nextLine(); // Clear the input
            break;
        }
    }
}
