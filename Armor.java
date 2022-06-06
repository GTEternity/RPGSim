import java.io.Serializable;
import java.util.Objects;

public class Armor extends Item implements Serializable {
    private int defense;
    private boolean sellable;

    public Armor(int def, String name, int value) {
        super(name, value);
        defense = def;
    }
    public Armor(int def, String name, int value, boolean canSell) {
        this(def, name, value);
        sellable = canSell;
    }
    public Armor(int def, String name, int value, int amount) {
        super(name, value, amount);
        defense = def;
    }
    public Armor(int def, String name, int value, int amount, boolean canSell) {
        this(def, name, value, amount);
        sellable = canSell;
    }
    public Armor(Item item) {
        super(item);
    }

    public int getDefense() {
        return defense;
    }
    public boolean isSellable() {
        return sellable;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
    public void setSellable(boolean sellable) {
        this.sellable = sellable;
    }
    public String toString() {
        String canSell;
        if (sellable) {
            canSell = "can be sold";
        }
        else {
            canSell = "can't be sold";
        }
        return "Weapon name: " + super.getName() + ", amount: " + super.getAmount() + ", price: " + super.getPrice() + ", damage: " + defense + ", " + canSell + ".";
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Armor armor = (Armor) o;
        return defense == armor.defense && sellable == armor.sellable && super.getName().equals(armor.getName()) && super.getPrice() == armor.getPrice();
    }
}
