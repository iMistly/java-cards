package cardgames.common;

public abstract class Game {
    public Deck deck;
    public boolean gameActive;

    // We have to pass the scanner around to avoid errors
    public Game() {
        this.deck = new Deck();
        this.gameActive = false;
    }

    // Initialize and start the game loop
    abstract public void newGame();
    // Game logic loop
    abstract public void play();
    
    public boolean isGameActive() {
        return gameActive;
    }

    public void endGame() {
        System.out.println("Game over.");
        this.gameActive = false;
    }
}
