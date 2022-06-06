import java.io.Serializable;
import java.util.Objects;

public class Wand extends Item implements Serializable {
    private int damage;
    private boolean canSell;
    public Wand(int dmg, String wandName, int value) {
        super(wandName, value);
        damage = dmg;
    }
    public Wand(int dmg, String wandName, int value, boolean sellable) {
        this(dmg, wandName, value);
        canSell = sellable;
    }
    public Wand(int dmg, String wandName, int value, int chance) {
        this(dmg, wandName, value);
        super.setDropChance(chance);
    }
    public Wand(int dmg, String wandName, int value, int chance, boolean sellable) {
        this (dmg, wandName, value, chance);
        canSell = sellable;
    }
    public Wand(Item item) {
        super(item);
    }
    public Wand(Item item, int damage) {
        this(item);
        this.damage = damage;
    }
    public Wand(Item item, int damage, boolean sellable) {
        this(item, damage);
        canSell = sellable;
    }
    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
    public boolean sellable() {
        return canSell;
    }
    public void setSellable(boolean sellable) {
        canSell = sellable;
    }
    public String toString() {
        String sellable;
        if (canSell) {
            sellable = "can be sold";
        }
        else {
            sellable = "can't be sold";
        }
        return "Weapon name: " + super.getName() + ", amount: " + super.getAmount() + ", price: " + super.getPrice() + ", damage: " + damage + ", " + sellable + ".";
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Wand wand = (Wand) o;
        return damage == wand.damage && canSell == wand.canSell && super.getName().equals(wand.getName()) && super.getPrice() == wand.getPrice();
    }
}
