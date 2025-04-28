package cardgames;
import java.util.Vector;

class Hand {
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
        for (Card card : cards) {
            System.out.print(card.getValue() + " ");
        }
        System.out.println();
    } 
}

