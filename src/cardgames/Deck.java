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
}