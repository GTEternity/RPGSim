import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Enemy {
    private int health;
    private int damage;
    private int maxHealth;
    private String name;
    private int expAmount;
    private String weakness = null;
    private ArrayList<Item> items = new ArrayList<Item>();
    private Random rand = new Random();
    public Enemy(int hp, int dmg, String enemyName, int expAmt) {
        health = hp;
        maxHealth = hp;
        damage = dmg;
        name = enemyName;
        expAmount = expAmt;
    }
    public Enemy() {
        health = 10;
        damage = 3;
        name = "Monster";
    }
    public void setHealth(int newHealth)
    {
        health = newHealth;
        //filler
    }
    public void setMaxHealth(int newMaxHealth) {
        maxHealth = newMaxHealth;
        //filler
    }
    public void setDamage(int newDamage)
    {
        damage = newDamage;
        //filler
    }
    public void setName(String newName)
    {
        name = newName;
        //filler
    }
    public void setExpAmount(int newExp) {
        expAmount = newExp;
        //filler
    }

    public void setItemDrops(ArrayList<Item> drops) {
        items = drops;
    }

    public ArrayList<Item> getItemDrops() {
        return items;
    }

    public void setWeakness(String attackName) {
        weakness = attackName;
    }

    public int getHealth()
    {
        return health;
        //filler
    }
    public int getMaxHealth() {
        return maxHealth;
        //filler
    }
    public int getDamage()
    {
        return damage;
        //filler
    }
    public String getName()
    {
        return name;
        //filler
    }
    public int getExpAmount() {
        return expAmount;
        //filler
    }

    public String getWeakness() {
        return weakness;
    }

    public void attack(Player player)
    {
        int multiplier = 1;
        if (crit()) {
            multiplier *=2;
            System.out.println("Ow! Critical hit...");
        }
        if (miss()) {
            multiplier *=0;
            System.out.println("Yes! It missed!");
        }
        if (damage - player.getCurrArmor().getDefense() >= 0)
            player.setHealth(player.getHealth() - (damage) * multiplier);
        else
            System.out.println("Your defense is so high, you took no damage!");
    }
    public boolean crit() {
        return rand.nextInt(100) == 1;
    }
    public boolean miss() {
        return rand.nextInt(100) == 1;
    }

    public void drop(Player player) {
        for (Item e : items) {
            if (rand.nextInt(100) < e.getDropChance()) {
                if (e.getClass() == Weapon.class)
                    player.addWeapon((Weapon) e, e.getAmount());
                else if (e.getClass() == Armor.class)
                    player.addArmor((Armor) e, e.getAmount());
                else if (e.getClass() == Wand.class)
                    player.addWand((Wand) e, e.getAmount());
                else
                    player.addItem(e, e.getAmount());
            }
        }
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enemy enemy = (Enemy) o;
        return health == enemy.health && damage == enemy.damage && maxHealth == enemy.maxHealth && expAmount == enemy.expAmount && Objects.equals(name, enemy.name) && Objects.equals(weakness, enemy.weakness);
    }

    public void addItem(Item item) {
        if (items.contains(item)) {
            for (int c = 0; c < items.size(); c++) {
                if (items.get(c).equals(item)) {
                    items.get(c).setAmount(items.get(c).getAmount() + item.getAmount());
                }
            }
        }
        else
            items.add(item);
    }
    public void removeItem(Item item, int amt) {
        for (int removed = 0; removed < amt && items.contains(item); removed++) {
            for (int c = 0; c < items.size(); c++) {
                if (items.get(c).equals(item)) {
                    if (items.get(c).getAmount() > 1 && items.get(c).getAmount() - amt > 0) {
                        items.get(c).setAmount(items.get(c).getAmount() - amt);
                    }
                    else
                        items.remove(item);
                }
            }
        }
    }
}