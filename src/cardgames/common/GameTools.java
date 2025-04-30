package cardgames.common;

public final class GameTools {
    public static String display_type;
    public static final String[] DISPLAY_TYPES = {"letter", "unicode"};
    
    // Method to wait for a specified number of milliseconds
    // Doesn't work without try catch
    public static void wait(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Evenly space out the dots over the specified time
    // wait "." wait "." wait "." wait
    public static void wait(int milliseconds, boolean animateDots) {
        int num_dots = 3;
        int interval = milliseconds / (num_dots+1);
        if (animateDots){
            for (int i = 0; i < num_dots; i++) {
                wait(interval);
                System.out.print(".");
            }
            wait(interval);
            System.out.println();
        }
        else{
            wait(milliseconds);
        }
    }

    // Method to clear the console
    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void printCardSequence(Object[] cards){
        String row0 = "";
        String row1 = "";
        String row2 = "";
        String row3 = "";
        String row4 = "";

        for(Object obj : cards){
            Card card = (Card) obj;
            row0 += "╓───┐ ";
            if (card.isFaceDown()){
                row1 += "║## │ ";
                row2 += "║###│ ";
                row3 += "║ ##│ ";
            }
            else{
                row1 += String.format("║%-2s │ ", card.getRank());
                row2 += String.format("║ %s │ ", card.getSuit());
                row3 += String.format("║ %2s│ ", card.getRank());
            }
            row4 += "╚═══╛ ";
        }

        if(display_type.equals(DISPLAY_TYPES[1])){
            row2 = row2.replace("H", "♥");
            row2 = row2.replace("S", "♠︎");
            row2 = row2.replace("C", "♣︎");
            row2 = row2.replace("D", "♦︎");
        }
        
        System.out.println(row0);
        System.out.println(row1);
        System.out.println(row2);
        System.out.println(row3);
        System.out.println(row4);
    }
}
