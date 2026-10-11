import java.util.Scanner;

/** Central place for reading and validating console input. */
public class InputHelper {
    private static final Scanner SC = new Scanner(System.in);

    /** Reads a whole number, re-prompting until the input is valid. */
    public static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (!SC.hasNextLine()) {           // input stream closed
                System.out.println("\nNo more input. Exiting.");
                System.exit(0);
            }
            String line = SC.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    /** Reads a whole number that must be between min and max (inclusive). */
    public static int readIntInRange(String prompt, int min, int max) {
        while (true) {
            int v = readInt(prompt);
            if (v >= min && v <= max) return v;
            System.out.println("Please enter a number between " + min + " and " + max + ".");
        }
    }

    /** Reads a non-empty line of text. */
    public static String readNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (!SC.hasNextLine()) {
                System.out.println("\nNo more input. Exiting.");
                System.exit(0);
            }
            String line = SC.nextLine().trim();
            if (!line.isEmpty()) return line;
            System.out.println("Input cannot be empty.");
        }
    }
}
