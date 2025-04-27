package cardgames;

// Removing 'public' makes the Deck class package-private
class Deck {
    Card[] cards = new Card[52];
    int topCard = 0; // Index of the top card in the deck
    
    // Constructor to initialize the deck with 52 cards
    public Deck(){
        int index = 0;
        for (String suit : Card.VALID_SUITS) {
            for (String rank : Card.VALID_RANKS) {
                cards[index++] = new Card(rank, suit);
            }
        }
    }

    public void show_deck(){
        for (int i = 0; i < cards.length; i++) {
            System.out.print(cards[i].rank + cards[i].suit + " ");
        }
    }

    // Cards are shuffled at random
    public void shuffle() { 
        Random rand = new Random();
        for (int i = cards.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);

            Card temp = cards[i];
            card[i] = cards[j];
            cards[j] = temp;
        }
        topCard = 0;
        System.out.println("Deck is shuffled.");
    }

    // Cards are dealt from the top of deck
    // if deck is empty return card
    public Card dealC() {
        if (topCard >= cards.length) {
            return null;
        }
        return cards[topCard++];
    }

    // checks if deck is empty
    // returns true if empty
    public boolean isEmpty() {
        return topCard >= cards.length;
    }

    // returns num of cards left in deck
    public int remaningC() {
        return cards.length - topCard;
    }
}