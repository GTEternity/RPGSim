import java.io.Serializable;
import java.util.Objects;

public class Item implements Serializable {
    private String name;
    private int price;
    private int amount = 1;
    private int dropChance = 0;

    public Item(String n, int worth) {
        name = n;
        price = worth;
    }
    public Item(String n, int worth, int amt) {
        this(n, worth);
        amount = amt;
    }
    public Item(String n, int worth, int amt, int chance) {
        this(n, worth, amt);
        dropChance = chance;
    }
    public Item(Item item) {
        name = item.getName();
        price = item.getPrice();
        amount = item.getAmount();
        dropChance = item.getDropChance();
    }
    public Item() {
        name = "nothing";
        price = 0;
        amount = 1;
    }
    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }
    public int getAmount(){
        return amount;
    }

    public int getDropChance() {
        return dropChance;
    }

    public void setDropChance(int dropChance) {
        this.dropChance = dropChance;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return price == item.price && Objects.equals(name, item.name) ;
    }

    public String toString() {
        return "Item name: " + name + ", value: " + price + ", amount: " + amount + ", drop chance: "+ dropChance + ". ";
    }
}