import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
    private int health;
    private int maxHealth;
    private int damage;
    private Spell[] spells = {new Spell("nothing", 0 , 0)};
    private int exp;
    private int neededExp;
    private int coins;
    private String name;
    private int totalMana;
    private int currentMana;
    private int level;
    private int weaponsBought = 0;
    private int armorBought = 0;
    private int wandsBought = 0;
    private ArrayList<Quest> quests = new ArrayList<Quest>();
    private ArrayList<Item> items= new ArrayList<Item>();
    private ArrayList<Weapon> weapons = new ArrayList<Weapon>();
    private ArrayList<Armor> armors = new ArrayList<Armor>();
    private ArrayList<Wand> wands = new ArrayList<Wand>();
    private Weapon currWeapon;
    private Armor currArmor;
    private Wand currWand;

    public Player(int maxHp, int dmg, int expNeeded, int manaTotal, String playerName) {
        level = 1;
        health = maxHp;
        maxHealth = maxHp;
        damage = dmg;
        name = playerName;
        currWeapon = new Weapon(0, "nothing", 0, false);
        currArmor = new Armor(0, "regular clothes", 0, false);
        currWand = new Wand(0, "twig", 0, false);
        exp = 0;
        neededExp = expNeeded;
        coins = 0;
        totalMana = manaTotal;
        currentMana = totalMana;
    }
    public Player() {
        level = 1;
        health = 20;
        damage = 4;
        name = "Player";
        totalMana = 20;
        currentMana = totalMana;
        currWeapon = new Weapon(0, "nothing", 0, false);
        currArmor = new Armor(0, "regular clothes", 0, false);
        currWand = new Wand(0, "twig", 0, false);
        exp = 0;
        neededExp = 1;
        coins = 0;
    }
    public void setLevel(int newLevel) {
        level = newLevel;
    }
    public void setHealth(int newHealth) {
        health = newHealth;
    }
    public void setMaxHealth(int newMaxHealth) {
        maxHealth = newMaxHealth;
    }
    public void setCurrentMana(int newCurrMana) {
        currentMana = newCurrMana;
    }
    public void setDamage(int newDamage) {
        damage = newDamage;
    }
    public void setSpells(Spell[] spellArr) {
        spells = spellArr;
    }
    public void setTotalMana(int newTotalMana) {
        totalMana = newTotalMana;
    }
    public void setCoins(int amtCoins) {
        coins = amtCoins;
    }
    public void setWeapons(ArrayList<Weapon> weapons) {
        this.weapons = weapons;
    }
    public void setArmors(ArrayList<Armor> armors) {
        this.armors = armors;
    }
    public void setWands(ArrayList<Wand> wands) {
        this.wands = wands;
    }

    public void setQuests(ArrayList<Quest> newQuests) {
        quests = newQuests;
    }
    public void equipWeapon(Weapon weapon) {
        addWeapon(currWeapon, 1);
        removeWeapon(weapon, 1);
        currWeapon = weapon;
    }
    public void equipWeapon(int index){
        equipWeapon(weapons.get(index));
    }
    public void equipArmor(Armor armor) {
        addArmor(currArmor, 1);
        removeArmor(armor, 1);
        currArmor = armor;
    }
    public void equipArmor(int index) {
        equipArmor(armors.get(index));
    }
    public void equipWand(Wand wand) {
        addWand(currWand, 1);
        removeWand(wand, 1);
        currWand = wand;
    }
    public void equipWand(int index) {
        equipWand(wands.get(index));
    }
    public Weapon getCurrWeapon() {
        return currWeapon;
    }
    public Armor getCurrArmor() {
        return currArmor;
    }
    public Wand getCurrWand() { return currWand;}
    public int getLevel() {
        return level;
    }
    public int getCurrentMana() {
        return currentMana;
    }
    public int getMaxHealth() {
        return maxHealth;
    }
    public int getCoins() {
        return coins;
    }
    public ArrayList<Quest> getQuests() {
        return quests;
    }
    public Spell[] getSpells() {
        return spells;
    }
    public int getTotalMana() {
        return totalMana;
    }
    public void setName(String newName) {
        name = newName;
    }
    public void setExp(int newExp) {
        exp = newExp;
    }
    public void setNeededExp(int newNeededExp) {
        neededExp = newNeededExp;
    }
    public int getExp() {
        return exp;
    }
    public int getNeededExp() {
        return neededExp;
    }
    public int getHealth() {
        return health;
    }
    public int getDamage() {
        return damage;
    }
    public String getName() {
        return name;
    }
    public int getWeaponsBought() {
        return weaponsBought;
    }
    public int getArmorBought() {
        return armorBought;
    }
    public int getWandsBought() {
        return wandsBought;
    }
    public void setArmorBought(int armorBought) {
        this.armorBought = armorBought;
    }
    public void setWandsBought(int wandsBought) {
        this.wandsBought = wandsBought;
    }
    public void setWeaponsBought(int weaponsBought) {
        this.weaponsBought = weaponsBought;
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }
    public ArrayList<Armor> getArmors() {
        return armors;
    }
    public ArrayList<Wand> getWands() {
        return wands;
    }

    public void addWeapon(Weapon weapon, int amt) {
        for (int added = 0; added < amt; added++) {
            if (weapons.contains(weapon)) {
                for (int c = 0; c < weapons.size(); c++) {
                    if (weapons.get(c).equals(weapon)) {
                        weapons.get(c).setAmount(weapons.get(c).getAmount() + weapon.getAmount());
                    }
                }
            }
            else
                weapons.add(weapon);
        }
    }
    public void addArmor(Armor armor, int amt) {
        for (int added = 0; added < amt; added++) {
            if (armors.contains(armor)) {
                for (int c = 0; c < armors.size(); c++) {
                    if (armors.get(c).equals(armor)) {
                        armors.get(c).setAmount(armors.get(c).getAmount() + armor.getAmount());
                    }
                }
            }
            else
                armors.add(armor);
        }
    }
    public void addWand(Wand wand, int amt) {
        for (int added = 0; added < amt; added++) {
            if (wands.contains(wand)) {
                for (int c = 0; c < wands.size(); c++) {
                    if (wands.get(c).equals(wand)) {
                        wands.get(c).setAmount(wands.get(c).getAmount() + wand.getAmount());
                    }
                }
            }
            else
                wands.add(wand);
        }
    }
    public void removeWeapon(Weapon weapon, int amt) {
        for (int removed = 0; removed < amt && weapons.contains(weapon); removed++) {
            for (int c = 0; c < weapons.size(); c++) {
                if (weapons.get(c).equals(weapon)) {
                    if (weapons.get(c).getAmount() > 1) {
                        weapons.get(c).setAmount(weapons.get(c).getAmount() - 1);
                    }
                    else
                        weapons.remove(weapon);
                }
            }
        }
    }
    public void removeArmor(Armor armor, int amt) {
        for (int removed = 0; removed < amt && armors.contains(armor); removed++) {
            for (int c = 0; c < armors.size(); c++) {
                if (armors.get(c).equals(armor)) {
                    if (armors.get(c).getAmount() > 1) {
                        armors.get(c).setAmount(armors.get(c).getAmount() - 1);
                    }
                    else
                        armors.remove(armor);
                }
            }
        }
    }
    public void removeWand(Wand wand, int amt) {
        for (int removed = 0; removed < amt && wands.contains(wand); removed++) {
            for (int c = 0; c < wands.size(); c++) {
                if (wands.get(c).equals(wand)) {
                    if (wands.get(c).getAmount() > 1) {
                        wands.get(c).setAmount(wands.get(c).getAmount() - 1);
                    }
                    else
                        wands.remove(wand);
                }
            }
        }
    }
    public void attack(Enemy enemy)
    {
        int multiplier = 1;
        if (crit()) {
            multiplier *= 2;
            System.out.println("Critical hit!");
        }
        if (miss()) {
            multiplier *= 0;
            System.out.println("It missed...");
        }
        enemy.setHealth(enemy.getHealth() - (damage + currWeapon.getDamage()) * multiplier);
        //filler
    }
    public void spellAttack(Enemy enemy, int spellChoice) {
        spellChoice--;
        int multiplier = 1;
        try {
            if (spells[spellChoice].getManaCost() <= currentMana) {
                if (spells[spellChoice].getName().equals(enemy.getWeakness())) {
                    multiplier *= 2;
                    System.out.println("\nVery effective!");
                }
                if (crit()) {
                    multiplier *= 2;
                    System.out.println("Critical hit!");
                }
                if (miss()) {
                    multiplier *= 0;
                    System.out.println("It missed...");
                }
                enemy.setHealth(enemy.getHealth() - (spells[spellChoice].getDamage() + currWand.getDamage()) * multiplier);
                currentMana -= spells[spellChoice].getManaCost();
            }
            else {
                System.out.println("Not enough mana...");
            }
            if (currentMana < 0)
                currentMana = 0;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Not a valid option, please choose a number from 1 to the amount of spells, inclusive.");
        }
    }

    public boolean crit() {
        return ((int)(Math.random() * 100) == 1);
    }
    public boolean miss() {
        return ((int)(Math.random() * 100) == 1);
    }

    public void buyWeapon(Shop weaponShop) {
        if (coins >= weaponShop.getWeaponPrice()) {
            addWeapon(weaponShop.getWeapon(), 1);
            coins -= weaponShop.getWeaponPrice();
            System.out.println("You bought a " + weaponShop.getWeapon());
        }
        else {
            System.out.println("You can't afford this.");
        }
    }
    public void buyArmor(Shop armorShop) {
        if (coins >= armorShop.getArmorPrice()) {
            armors.add(armorShop.getArmor());
            coins -= armorShop.getArmorPrice();
            System.out.println("You bought a " + armorShop.getArmor());
        }
        else {
            System.out.println("You can't afford this.");
        }
    }
    public void buyWand(Shop wandShop) {
        if (coins >= wandShop.getWandPrice()) {
            addWand(wandShop.getWand(), 1);
            coins -= wandShop.getWandPrice();
            System.out.println("You bought a " + wandShop.getWand());
        }
        else {
            System.out.println("You can't afford this.");
        }
    }
    public void sellWeapon(Weapon weapon, int amt) {
        if (weapon.sellable()) {
            if (weapon.getAmount() - amt > 0)
                coins += weapon.getPrice() * amt;
            else
                coins += weapon.getPrice() * weapon.getAmount();
            removeWeapon(weapon, amt);
        }
        else
            System.out.println("Item isn't sellable!");
    }
    public void sellArmor(Armor armor, int amt) {
        if (armor.isSellable()) {
            if (armor.getAmount() - amt > 0)
                coins += armor.getPrice() * amt;
            else
                coins += armor.getPrice() * armor.getAmount();
        }
        else
            System.out.println("Item isn't sellable!");
    }
    public void sellWand(Wand wand, int amt) {
        if (wand.sellable()) {
            if (wand.getAmount() - amt > 0)
                coins += wand.getPrice() * amt;
            else
                coins += wand.getPrice() * wand.getAmount();
        }
        else
            System.out.println("Item isn't sellable!");
    }
    public boolean hasQuest(Quest quest) {
        for(Quest check: quests) {
            if (check.equals(quest))
                return true;
        }
        return false;
    }
    public void resolveQuest(int index) {
        if (quests.get(index).isDone()) {
            coins += quests.get(index).getCoinReward();
            exp += quests.get(index).getExpReward();
            System.out.println("Quest \"" + quests.get(index).getQuest() + "\" resolved!");
            quests.remove(index);
        }
    }
    public void addQuest(Quest quest) {
        quests.add(quest);
    }
    public void setQuest(int place, Quest quest) {
        quests.set(place, quest);
    }
    public void removeQuest(int place) {
        quests.remove(place);
    }
    public void removeQuest(Quest quest) {
        quests.remove(quest);
    }
    public void setItems(ArrayList<Item> itemList) {
        items = itemList;
    }
    public ArrayList<Item> getItems() {
        return items;
    }
    public void addItem(Item item, int amt) {
        for (int added = 0; added < amt; added++) {
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
    }
    public void removeItem(Item item, int amt) {
        for (int removed = 0; removed < amt && items.contains(item); removed++) {
            for (int c = 0; c < items.size(); c++) {
                if (items.get(c).equals(item)) {
                    if (items.get(c).getAmount() > 1) {
                        items.get(c).setAmount(items.get(c).getAmount() - 1);
                    }
                    else
                        items.remove(item);
                }
            }
        }
    }
    public void sellItem(Item item, int amt) {
        if (item.getAmount() - amt > 0)
            coins += item.getPrice() * amt;
        else
            coins += item.getPrice() * item.getAmount();
        removeItem(item, amt);
    }
    public Item findItem(String name) {
        for (Item i : items) {
            if(i.getName().equals(name))
                return i;
        }
        return (new Item("nothing", 0, 0, 0));
    }
    public Weapon findWeapon(String name) {
        for (Weapon w : weapons) {
            if(w.getName().equals(name))
                return w;
        }
        return null;
    }public Weapon findWeaponLower(String name) {
        for (Weapon w : weapons) {
            if(w.getName().toLowerCase().equals(name))
                return w;
        }
        return null;
    }
    public Armor findArmor(String name) {
        for (Armor a : armors) {
            if(a.getName().equals(name))
                return a;
        }
        return null;
    }public Armor findArmorLower(String name) {
        for (Armor a : armors) {
            if(a.getName().toLowerCase().equals(name))
                return a;
        }
        return null;
    }
    public Wand findWand(String name) {
        for (Wand w : wands) {
            if(w.getName().equals(name))
                return w;
        }
        return null;
    }public Wand findWandLower(String name) {
        for (Wand w : wands) {
            if(w.getName().toLowerCase().equals(name))
                return w;
        }
        return null;
    }
    public Item findItemLower(String lower) {
        for (Item i : items) {
            if(i.getName().toLowerCase().equals(lower))
                return i;
        }
        return (new Item("nothing", 0, 0, 0));
    }
}