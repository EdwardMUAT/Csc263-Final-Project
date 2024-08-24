import java.util.InputMismatchException;
import java.util.Scanner;

public class MysticForest {
    public static void main(String[] args) {
        System.out.println("Welcome to Mystic Forest!");
        System.out.println("In this adventure, you will try to escape the mystical forest.");
        System.out.println("Navigate through puzzles, fight enemies, and find your way out.");
        System.out.println("\n1. Start Game\n2. Load Game\n3. Exit");

        try (Scanner scanner = new Scanner(System.in)) {
            int choice = getValidInt(scanner);

            switch (choice) {
                case 1 -> {
                    GameWorld game = new GameWorld();
                    game.start();
                }
                case 2 -> System.out.println("Loading game...");
                // Load game logic (This is optional if you haven't implemented it)
                case 3 -> {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                default -> {
                    System.out.println("Invalid option, exiting.");
                    System.exit(1);
                }
            }
        }
    }

    private static int getValidInt(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next(); // Clear the invalid input
            }
        }
    }
}
