package cardgames;

class BlackJackHand extends Hand{
    public int MAX_HAND_SIZE = 11;
    private boolean isDealer;

    public BlackJackHand() {
        super();
        this.isDealer = false;
    }
    public BlackJackHand(boolean isDealer){
        super();
        this.isDealer = isDealer;
    }
    
    @Override
    public void addCard(Card card) {
        if (super.cards.size() < MAX_HAND_SIZE){
            super.cards.add(card);
        }
        else{
            System.out.println("Cannot add more cards to the hand.");
        }
    }

    @Override
    public void showHand() {
        if(isDealer){
            for(int i = 0; i < cards.size(); i++){
                if (i == 0){
                    System.out.print("[Hidden] ");
                }
                else{
                    System.out.print(cards.get(i).getValue() + " ");
                }
            }
            System.out.println();
        }
        else{
            super.showHand();
        }
    }

    public int getHandValue() {
        int value = 0;
        int aceCount = 0;

        for(Card card : cards) {
            // Skip the hidden card for the dealer
            if(isDealer && cards.indexOf(card) == 0) {
                continue; // Skip the first card for the dealer
            }
            String rank = card.rank;
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

    // method to get total hand value and dealers hidden card
    public int getFullHandVal() {
        int value = 0;
        int aceCount = 0;

        for (Card card : cards) {
            String rank = card.rank;
            if(rank.equals("A")) {
                value += 11;
                aceCount++;
            }
            else if(rank.equals("k") || rank.equals("Q") || rank.equals("J")) {
                value += 10;
            } else {
                value += Integer.parseInt(rank);
            }
        }

        // Adjust if we go over 21
        while (value > 21 && aceCount > 0) {
            value -= 10;
            aceCount--;
        }

        return value;
    }
}
