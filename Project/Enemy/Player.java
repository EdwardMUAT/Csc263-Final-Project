public abstract class Player {
    private String name;
    private int health;
    private int position;

    public Player(String name) {
        this.name = name;
        this.health = 100; // Default health value
        this.position = 0; // Default starting position
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void move(int steps) {
        this.position += steps;
        System.out.println("Player moved to position: " + this.position);
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    public abstract void specialAbility();
}
