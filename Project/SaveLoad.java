import java.io.*;

public class SaveLoad {
    public void saveGame(Player player) {
        try (FileWriter writer = new FileWriter("savegame.txt")) {
            writer.write("Player Name: " + player.getName() + "\n");
            writer.write("Player Health: " + player.getHealth() + "\n");
            writer.write("Player Position: " + player.getPosition() + "\n");
            if (player instanceof Mage mage) {
                writer.write("Player Class: Mage\n");
                writer.write("Mage Mana: " + mage.getMana() + "\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving the game. Please try again.");
            // Optionally log the error for debugging purposes
            // e.printStackTrace(); 
        }
    }

    public Player loadGame() {
        try (BufferedReader reader = new BufferedReader(new FileReader("savegame.txt"))) {
            String name = reader.readLine().split(": ")[1];
            int health = Integer.parseInt(reader.readLine().split(": ")[1]);
            int position = Integer.parseInt(reader.readLine().split(": ")[1]);
            String playerClass = reader.readLine().split(": ")[1];
            Player player = null;

            if (playerClass.equals("Mage")) {
                int mana = Integer.parseInt(reader.readLine().split(": ")[1]);
                player = new Mage(name, mana);
            }

            if (player != null) {
                player.setHealth(health);
                player.setPosition(position);
            } else {
                System.out.println("Error: Player class not recognized or missing.");
            }

            return player;
        } catch (IOException e) {
            System.out.println("An error occurred while loading the game. Please try again.");
            // Optionally log the error for debugging purposes
            // e.printStackTrace(); 
            return null;
        } catch (NumberFormatException e) {
            System.out.println("Error: Data format is incorrect in the save file.");
            // Optionally log the error for debugging purposes
            // e.printStackTrace(); 
            return null;
        }
    }
}
