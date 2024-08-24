public class Goblin extends Enemy {
    public Goblin() {
        super("Goblin", 120);
    }

    @Override
    public void attack(Player player) {
        System.out.println(name + " throws a spear at the player!");
        player.takeDamage(35);
    }
}
