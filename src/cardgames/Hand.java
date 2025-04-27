package cardgames;

public class Hand {
    private Card[] cards;
    private int cardCount;

    private static final int MAX_HAND_SIZE = 11;

    public Hand() {
        this.cards = new Card[MAX_HAND_SIZE];
        this.cardCount = 0;
    }

    public void addCard(Card card) {
        if (cardCound < cards.length) {
            this.cards[carCound] = card;
            cardCount++;
        } else {
            System.out.println("Hand is full. Cant add more cards!");
        }
    }

    public void clear() {
        this.cards = new Card[MAX_HAND_SIZE];
        this.cardCount = 0;
    }

    public int getVal() {
        int value = o;
        int aceCount = 0;
    }
}
