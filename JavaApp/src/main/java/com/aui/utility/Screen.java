package com.aui.utility;

public class Screen {

    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public static void displayGreetings() {
        clear();
        color(GREEN, "\t\t\t---------------------------");
        color(GREEN, "\t\t\t-----" + RED + "PART TIME JOB CSP" + GREEN + "-----");
        color(GREEN, "\t\t\t-------------" + RED + "BY" + GREEN + "------------");
        color(GREEN, "\t\t\t------ " + RED + "AMMINE Bassma" + GREEN + " ------");
        color(GREEN, "\t\t\t------- " + RED + "EL AMRI Ali" + GREEN + " -------");
        color(GREEN, "\t\t\t-- " + RED + "TAHRI SQALLI Mohammed" + GREEN + " --");
        color(GREEN, "\t\t\t---------------------------");

        System.out.println("");

        System.out.println("Welcome to the Part Time Job CSP Solver");
        System.out.println();
        System.out.println("\t Hard Constraints");
        System.out.format(GREEN + "+------------------------+%n" + RESET);
        System.out.format(GREEN + "| " + YELLOW + "Hard Constraints       " + GREEN + "|%n");
        System.out.format(GREEN + "+------------------------+%n" + RESET);
        System.out.format(GREEN + "| " + RESET + "Grade       " + GREEN + "|%n");

    }

    private static void color(String color, String text) {
        System.out.println(color + text + RESET);
    }

    public static void clear() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
        }
    }

}
