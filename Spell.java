import java.io.Serializable;
import java.util.Objects;

public class Spell implements Serializable {
    private String name;
    private int damage;
    private int manaCost;

    public Spell(String n, int dmg, int cost) {
        name = n;
        damage = dmg;
        manaCost = cost;
    }

    public Spell() {
        name = "";
        damage = 0;
        manaCost = 0;
    }

    public void setDamage(int dmg) {
        damage = dmg;
        //filler
    }
    public void setName(String n) {
        name = n;
        //filler
    }
    public void setManaCost(int cost) {
        manaCost = cost;
        //filler
    }

    public int getDamage() {
        return damage;
        //filler
    }
    public int getManaCost() {
        return manaCost;
        //filler
    }
    public String getName() {
        return name;
        //filler
    }

    public void heal(Player p, int amt) {
        p.setHealth(p.getHealth() + amt);
        //filler
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spell spell = (Spell) o;
        return damage == spell.damage && manaCost == spell.manaCost && Objects.equals(name, spell.name);
    }
}