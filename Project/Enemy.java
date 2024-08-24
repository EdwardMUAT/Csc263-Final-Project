abstract class Enemy {
    protected String name;
    protected int health;

    public Enemy(String name, int health) {
        this.name = name;
        this.health = health;
    }

    public abstract void attack(Player player);
}

class Wolf extends Enemy {
    public Wolf() {
        super("Wolf", 50);
    }

    @Override
    public void attack(Player player) {
        System.out.println(name + " attacks the player!");
        player.takeDamage(10);
    }
}

class Goblin extends Enemy {
    public Goblin() {
        super("Goblin", 40);
    }

    @Override
    public void attack(Player player) {
        System.out.println(name + " throws a spear at the player!");
        player.takeDamage(15);
    }
}
