public class Wolf extends Enemy {
    public Wolf() {
        super("Wolf", 150);
    }

    @Override
    public void attack(Player player) {
        System.out.println(name + " attacks the player!");
        player.takeDamage(40);
    }
}
