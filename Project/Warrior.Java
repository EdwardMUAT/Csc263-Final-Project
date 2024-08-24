public class Warrior extends Player {
    private int strength;

    // Constructor
    public Warrior(String name, int strength) {
        super(name); // Call the constructor of the superclass (Player)
        this.strength = strength;
    }

    // Getter for strength
    public int getStrength() {
        return strength;
    }

    // Setter for strength
    public void setStrength(int strength) {
        this.strength = strength;
    }

    // Overriding move method to add additional functionality
    @Override
    public void move(int steps) {
        super.move(steps); // Call the move method of the superclass
        System.out.println("Warrior's strength: " + this.strength);
    }

    // Implement the abstract method from Player
    @Override
    public void specialAbility() {
        System.out.println("Warrior uses a powerful attack!");
    }
}
