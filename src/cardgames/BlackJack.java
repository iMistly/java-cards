package cardgames;

public class BlackJack {
    // Necessary parts of the game
    private Deck deck;
    private BlackJackHand playerHand;
    private BlackJackHand dealerHand;
    
    // Game variables
    boolean playerTurn;
    boolean gameActive;
    
    private static final int BLACKJACK = 21;
    private static final int DEALER_STAND = 17;

    // Initialize the game
    public BlackJack(){
        this.deck = new Deck();
        this.playerHand = new BlackJackHand();
        this.dealerHand = new BlackJackHand(true);
    }

    // Start a new game
    public void newGame() {
        System.out.println("Starting a new game of BlackJack...");
        this.deck.initializeDeck();
        this.deck.shuffle();
        this.playerHand.clear();
        this.dealerHand.clear();
        this.dealInitialCards();
        this.showHands();
        this.playerTurn = true;
        this.gameActive = true;
    }

    private void dealInitialCards() {
        for (int i = 0; i < 2; i++) {
            this.playerHand.addCard(this.deck.dealCard());
            this.dealerHand.addCard(this.deck.dealCard());
        }
    }

    private void showHands() {
        System.out.printf("Player's Hand: == %d\n", this.playerHand.getHandValue());
        this.playerHand.showHand();
        System.out.printf("Dealer's Hand: >= %d\n", this.dealerHand.getHandValue());
        this.dealerHand.showHand();
    }

    public void play() {
        while(gameActive){

        }
    }
}
