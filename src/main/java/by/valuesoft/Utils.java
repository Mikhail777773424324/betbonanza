package by.valuesoft;


import java.util.concurrent.TimeUnit;

public class Utils {

    public static void sleep(long time) {
        try { TimeUnit.MILLISECONDS.sleep(time); } catch (Exception ignored) {}
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
