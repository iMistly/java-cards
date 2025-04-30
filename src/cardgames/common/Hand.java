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

    public Card[] clear() {
        // Fix: Use the overloaded toArray method to ensure the correct type
        Card[] removed_cards = this.cards.toArray(new Card[0]);
        this.cards.clear();
        return removed_cards;
    }

    // Toggle flip
    public void flipCard(int index){
        Card card = this.cards.get(index);
        card.setFaceDown(!card.isFaceDown());
    }

    // Force flip
    public void flipCard(int index, boolean isFaceDown){
        this.cards.get(index).setFaceDown(isFaceDown);
    }

    public void showHand() {
        GameTools.printCardSequence(this.cards.toArray(new Card[0]));
    }
}