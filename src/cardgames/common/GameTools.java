package cardgames.common;

public class GameTools {
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
}
