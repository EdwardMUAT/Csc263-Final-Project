import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GameWorld {
    private Player player;
    private final Scanner scanner;
    private final Random random;

    // Constructor
    public GameWorld() {
        player = new Mage("DefaultMage", 50); // Example using Mage subclass
        scanner = new Scanner(System.in);
        random = new Random();
    }

    // Start the game
    public void start() {
        System.out.println("Enter your character's name:");
        String name = scanner.nextLine();
        System.out.println("Choose your class (1. Warrior 2. Mage):");
        int choice = getValidInt();

        switch (choice) {
            case 1 -> {
                System.out.println("Enter your strength:");
                int strength = getValidInt();
                player = new Mage(name, strength);
            }
            case 2 -> {
                System.out.println("Enter your mana:");
                int mana = getValidInt();
                player = new Mage(name, mana);
            }
            default -> {
                System.out.println("Invalid class choice.");
                return;
            }
        }

        System.out.println("Starting adventure for " + player.getName() + "...");
        gameLoop();
    }

    // Main game loop
    private void gameLoop() {
        while (player.getHealth() > 0 && player.getPosition() < 4) {
            System.out.println("Current Location: " + player.getPosition());
            System.out.println("1. Move Forward\n2. Rest\n3. Attack\n4. Defend\n5. Exit");

            int choice = getValidInt();

            switch (choice) {
                case 1 -> {
                    player.move(1);
                    encounter();
                }
                case 2 -> {
                    System.out.println("You take a rest and regain some health.");
                    player.setHealth(player.getHealth() + 10); // Simple health regen logic
                }
                case 3 -> attackEnemy();
                case 4 -> defend();
                case 5 -> {
                    System.out.println("Exiting game...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid option.");
            }
        }

        if (player.getHealth() <= 0) {
            System.out.println("You have died. Game Over.");
        } else {
            System.out.println("Congratulations! You escaped the Mystic Forest.");
        }
    }

    // Encounter method for demonstration
    private void encounter() {
        // Implement encounter logic here
        System.out.println("An encounter happened!");
    }

    // Attack method
    private void attackEnemy() {
        Enemy enemy = random.nextBoolean() ? new Goblin() : new Wolf();
        int hitsRequired = random.nextInt(2) + 4; // 4 or 5 hits

        System.out.println("A " + enemy.name + " has appeared!");

        while (player.getHealth() > 0 && enemy.health > 0) {
            enemy.attack(player);
            if (player.getHealth() <= 0) {
                System.out.println("You have been defeated by the " + enemy.name + ". Game Over.");
                return;
            }
            hitsRequired--;
            if (hitsRequired <= 0) {
                System.out.println("Congratulations! You have defeated the " + enemy.name + ".");
                System.out.println("You have brought peace to the land.");
                return;
            }
        }
    }

    // Defend method
    private void defend() {
        System.out.println("You brace yourself for an incoming attack.");
        // Implement defense logic here
        // For now, it's a placeholder that doesn't affect gameplay
    }

    // Method to get a valid integer from the user
    private int getValidInt() {
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
