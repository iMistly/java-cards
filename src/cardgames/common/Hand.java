package cardgames.common;
import java.util.Vector;

public class Hand {
    private Vector<Card> cards;

    public Hand() {
        this.cards = new Vector<>();
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public Card[] getCards() {
        return this.cards.toArray(new Card[0]);
    }

    public Card[] clear() {
        // Fix: Use the overloaded toArray method to ensure the correct type
        Card[] removed_cards = this.getCards();
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

    // Force hand flip
    public void setCardsFaceDown(boolean isFaceDown){
        for(Card card : this.cards){
            card.setFaceDown(isFaceDown);
        }
    }

    public void showHand() {
        GameTools.printCardSequence(this.cards.toArray(new Card[0]));
    }
}