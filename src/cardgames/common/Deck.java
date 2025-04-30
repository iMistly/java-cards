package cardgames.common;
import java.util.Vector;
import java.util.Random;

public class Deck {
    Vector<Card> cards = new Vector<>(52);
    
    // Constructor to initialize the deck with 52 cards
    public Deck(){
        this.initializeDeck();
    }

    public void initializeDeck() {
        cards.clear();
        for (String suit : Card.VALID_SUITS) {
            for (String rank : Card.VALID_RANKS) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    // Cards are shuffled at random
    public void shuffle() { 
        Random rand = new Random();
        for (int i = cards.size() - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);

            Card temp = cards.get(i);
            cards.set(i, cards.get(j));
            cards.set(j, temp);
        }
    }

    // Cards are dealt from the top of deck
    // if deck is empty return card
    public Card dealCard() {
        if (this.isEmpty()) {
            return null;
        }
        Card card_to_deal = cards.get(0);
        cards.remove(0);
        return card_to_deal;
    }

    // checks if deck is empty
    // returns true if empty
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    // returns num of cards left in deck
    public int remainingCards() {
        return cards.size();
    }
}