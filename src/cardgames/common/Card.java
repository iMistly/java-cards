package cardgames.common;

public class Card {
    public static final String[] VALID_RANKS = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    public static final String[] VALID_SUITS = {"C", "H", "S", "D"};

    public String rank; // (A)ce, 2, 3, 4, 5, 6, 7, 8, 9, 10, (J)ack, (Q)ueen, (K)ing
    public String suit; // (C)lubs, (H)earts, (S)pades, (D)iamonds

    // Ensure that the requested card is valid
    public Card(String rank, String suit){
        if (isValidRank(rank) && isValidSuit(suit)) {
            this.rank = rank;
            this.suit = suit;
        } else {
            throw new IllegalArgumentException("Invalid card: " + rank + suit);
        }
    }
    
    private boolean isValidRank(String rank) {
        for (String validRank : VALID_RANKS) {
            if (validRank.equals(rank)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidSuit(String suit) {
        for (String validSuit : VALID_SUITS) {
            if (validSuit.equals(suit)) {
                return true;
            }
        }
        return false;
    }

    public String getValue() {
        return this.rank + this.suit;
    }
}
