package cardgames.common;
import java.util.Scanner;

public abstract class Game {
    public Deck deck;
    public boolean gameActive;
    public Scanner scanner;

    // We have to pass the scanner around to avoid errors
    public Game(Scanner scanner) {
        this.deck = new Deck();
        this.gameActive = false;
        this.scanner = scanner;
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
