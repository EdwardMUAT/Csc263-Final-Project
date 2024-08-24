public class Mage extends Player {
    private int mana;

    public Mage(String name, int mana) {
        super(name); // Call the constructor of the superclass (Player)
        this.mana = mana;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage); // Call the takeDamage method of the superclass
        System.out.println("Mage's mana after damage: " + this.mana);
    }

    @Override
    public void specialAbility() {
        System.out.println("Mage casts a powerful spell!");
    }
}
