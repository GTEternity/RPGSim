import java.io.Serializable;
import java.util.Objects;

public class Weapon extends Item implements Serializable {
    private int damage;
    private boolean canSell = false;
    public Weapon(int dmg, String weaponName, int value) {
        super(weaponName, value);
        damage = dmg;
    }
    public Weapon(int dmg, String weaponName, int value, boolean sellable) {
        this(dmg, weaponName, value);
        canSell = sellable;
    }
    public Weapon(int dmg, String weaponName, int value, int chance) {
        this(dmg, weaponName, value);
        super.setDropChance(chance);
    }
    public Weapon(int dmg, String weaponName, int value, int chance, boolean sellable) {
        this (dmg, weaponName, value, chance);
        canSell = sellable;
    }
    public Weapon(Item item) {
        super(item);
    }
    public Weapon(Item item, int damage) {
        this(item);
        this.damage = damage;
    }
    public Weapon(Item item, int damage, boolean sellable) {
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
        Weapon weapon = (Weapon) o;
        return damage == weapon.damage && canSell == weapon.canSell && super.getName().equals(weapon.getName()) && super.getPrice() == weapon.getPrice();
    }
}
