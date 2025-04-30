package cardgames.common;
import java.util.Vector;

public class Hand {
    public Vector<Card> cards;

    public Hand() {
        this.cards = new Vector<>();
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public void clear() {
        this.cards.clear();
    }

    public void showHand() {
        for (Card card : this.cards) {
            GameTools.printCard(card);
            System.out.print(" ");
        }
        System.out.println();
    }
}