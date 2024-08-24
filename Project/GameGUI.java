import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class GameGUI {
    private static Player player;
    private static Enemy enemy;
    private static JTextArea dialogueArea;
    private static JButton moveForwardButton;
    private static JButton restButton;
    private static JButton attackButton;
    private static JButton defendButton;
    private static JButton exitButton;
    private static JButton startGameButton;
    private static JButton loadGameButton;
    private static JButton warriorButton;
    private static JButton mageButton;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameGUI::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Game Interface");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3, 10, 10));

        // Text area for dialogue
        dialogueArea = new JTextArea();
        dialogueArea.setEditable(false);
        dialogueArea.setFont(new Font("Arial", Font.PLAIN, 16));
        dialogueArea.setBackground(new Color(240, 240, 240));
        JScrollPane scrollPane = new JScrollPane(dialogueArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Buttons
        startGameButton = createButton("Start Game", Color.LIGHT_GRAY);
        loadGameButton = createButton("Load Game", Color.LIGHT_GRAY);
        exitButton = createButton("Exit", Color.LIGHT_GRAY);
        warriorButton = createButton("Warrior", Color.GREEN);
        mageButton = createButton("Mage", Color.MAGENTA);
        moveForwardButton = createButton("Move Forward", Color.YELLOW);
        restButton = createButton("Rest", Color.LIGHT_GRAY);
        attackButton = createButton("Attack", Color.RED);
        defendButton = createButton("Defend", Color.BLUE);

        buttonPanel.add(startGameButton);
        buttonPanel.add(loadGameButton);
        buttonPanel.add(exitButton);
        buttonPanel.add(warriorButton);
        buttonPanel.add(mageButton);
        buttonPanel.add(moveForwardButton);
        buttonPanel.add(restButton);
        buttonPanel.add(attackButton);
        buttonPanel.add(defendButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);

        // Initial setup
        showMainMenu();
    }

    private static JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(color);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(150, 50));

        button.addActionListener(e -> handleButtonAction(text));
        button.setActionCommand(text);

        return button;
    }

    private static void handleButtonAction(String action) {
        switch (action) {
            case "Start Game" -> showCharacterSelection();
            case "Load Game" -> loadGame();
            case "Exit" -> System.exit(0);
            case "Warrior" -> startGame("Warrior");
            case "Mage" -> startGame("Mage");
            case "Move Forward" -> moveForward();
            case "Rest" -> rest();
            case "Attack" -> attack();
            case "Defend" -> defend();
        }
    }

    private static void showMainMenu() {
        dialogueArea.setText("1. Start Game\n2. Load Game\n3. Exit");
        setButtonsVisibility(true, false, false, false, false);
    }

    private static void showCharacterSelection() {
        dialogueArea.setText("Choose your class:\n1. Warrior\n2. Mage");
        setButtonsVisibility(false, true, true, false, false);
    }

    private static void startGame(String className) {
        dialogueArea.setText("Enter your character's name:");
        setButtonsVisibility(false, false, false, false, true);
        // Implement character creation based on className (Warrior or Mage)
        player = new Player(className);
        showGameControls();
    }

    private static void showGameControls() {
        dialogueArea.setText("Starting adventure for " + player.getName() + "...\nCurrent Location: 0");
        setButtonsVisibility(false, false, false, false, true);
        // Initial visibility settings for game controls
    }

    private static void setButtonsVisibility(boolean start, boolean warrior, boolean mage, boolean exit, boolean gameControls) {
        startGameButton.setVisible(start);
        loadGameButton.setVisible(start);
        exitButton.setVisible(exit);
        warriorButton.setVisible(warrior);
        mageButton.setVisible(mage);
        moveForwardButton.setVisible(gameControls);
        restButton.setVisible(gameControls);
        attackButton.setVisible(gameControls);
        defendButton.setVisible(gameControls);
    }

    private static void loadGame() {
        // Implement game loading logic
    }

    private static void moveForward() {
        dialogueArea.append("\nPlayer moves forward.");
        encounterMonster(); // Example method call
    }

    private static void rest() {
        dialogueArea.append("\nPlayer rests and recovers health.");
        player.restoreHealth(20); // Example healing amount
    }

    private static void attack() {
        if (enemy != null) {
            int playerDamage = player.attack();
            int enemyDamage = enemy.attack();
            enemy.takeDamage(playerDamage);
            if (!defending) {
                player.takeDamage(enemyDamage);
            } else {
                dialogueArea.append("\nDamage was negated due to defense!");
                defending = false; // Reset defense status
            }

            dialogueArea.append("\nPlayer attacks the enemy for " + playerDamage + " damage!");
            dialogueArea.append("\nEnemy attacks back for " + (defending ? 0 : enemyDamage) + " damage!");

            if (enemy.getHealth() <= 0) {
                victory();
            }
        }
    }

    private static void defend() {
        defending = true;
        dialogueArea.append("\nPlayer defends against the enemy attack.");
    }

    private static void encounterMonster() {
        dialogueArea.append("\nAn encounter happened!\n");
        setButtonsVisibility(false, false, false, true, true);
        moveForwardButton.setVisible(false);
        restButton.setVisible(false);
        attackButton.setVisible(true);
        defendButton.setVisible(true);
        
        // Randomly choose enemy type and set initial health
        enemy = new Enemy(new Random().nextBoolean() ? "Goblin" : "Wolf");
    }

    private static void victory() {
        dialogueArea.append("\nYou have defeated the enemy!");
        setButtonsVisibility(false, false, false, false, true);
        JOptionPane.showMessageDialog(null, "Congratulations! You have brought peace to the land.");
    }

    // Player class
    static class Player {
        private final String name;
        private int health;
        private final int attackPower;

        public Player(String name) {
            this.name = name;
            this.health = 100; // Example health
            this.attackPower = 10; // Example attack power
        }

        public String getName() {
            return name;
        }

        public int attack() {
            Random rand = new Random();
            return attackPower + rand.nextInt(10); // Example fluctuating attack power
        }

        public void takeDamage(int damage) {
            health -= damage;
            if (health <= 0) {
                health = 0;
                // Handle player defeat logic if needed
            }
        }

        public void restoreHealth(int amount) {
            health += amount;
            // Optionally cap health at a maximum value
        }
    }

    // Enemy class
    static class Enemy {
        private int health;
        private final int attackPower;

        public Enemy(String type) {
            if (type.equals("Goblin")) {
                this.health = 50; // Example health for Goblin
                this.attackPower = 5; // Example attack power for Goblin
            } else {
                this.health = 80; // Example health for Wolf
                this.attackPower = 10; // Example attack power for Wolf
            }
        }

        public int attack() {
            Random rand = new Random();
            return attackPower + rand.nextInt(5); // Example fluctuating attack power
        }

        public void takeDamage(int damage) {
            health -= damage;
            if (health <= 0) {
                health = 0;
                // Handle enemy defeat logic if needed
            }
        }

        public int getHealth() {
            return health;
        }
    }

    // To track defense status
    private static boolean defending = false;
}
