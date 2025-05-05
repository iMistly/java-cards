package cardgames.blackjack;
import cardgames.common.Hand;
import cardgames.common.Card;

class BlackJackHand extends Hand{
    public BlackJackHand() {
        super();
    }

    public int getHandValue() {
        int value = 0;
        int aceCount = 0;

        for(Card card : super.getCards()) {
            if(card.isFaceDown()){continue;}
            String rank = card.getRank();
            if(rank.equals("A")) {
                value += 11; // Ace is worth 11 points
                aceCount++;
            }
            else if(rank.equals("K") || rank.equals("Q") || rank.equals("J")) {
                value += 10; // Face cards are worth 10 points
            }
            else{
                value += Integer.parseInt(rank); // Number cards are worth their face value
            }
        }

        // Adjust for Aces if value exceeds 21
        while (value > 21 && aceCount > 0) {
            value -= 10; // Count Ace as 1 instead of 11
            aceCount--;
        }

        return value;
    }
}

//