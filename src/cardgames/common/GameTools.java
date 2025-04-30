package cardgames.common;

public final class GameTools {
    public static String display_type;
    public static final String[] DISPLAY_TYPES = {"letter", "unicode", "letterASCII", "unicodeASCII"};
    
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
    public static void wait(int milliseconds, boolean animateDots) {
        int num_dots = 3;
        int interval = milliseconds / num_dots;
        if (animateDots){
            for (int i = 0; i < num_dots; i++) {
                wait(interval);
                System.out.print(".");
            }
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

    public static void printCard(Card card){
        if(display_type == "letter"){printCardLetter(card);}
        else if(display_type == "unicode"){printCardUnicode(card);}
    }

    public static void printCard(boolean faceDown){
        System.out.print("??");
    }

    private static void printCardLetter(Card card) {
        System.out.print(card.getValue());
    }

    private static void printCardUnicode(Card card) {
        String value = card.getValue();
        value = value.replace("C", "♣");
        value = value.replace("H", "♥");
        value = value.replace("S", "♠");
        value = value.replace("D", "♦");
        System.out.print(value);
    }
}
