public class Shop {
    private Weapon weapon;
    private Armor armor;
    private Wand wand;
    public Shop(Weapon wpn, Armor amr, Wand wnd) {
        weapon = wpn;
        armor = amr;
        wand = wnd;
    }
    public Shop() {
        weapon = new Weapon(0, "nothing", 0);
    }
    public void setWeapon(Weapon newWeapon)
    {
        weapon = newWeapon;
        //filler
    }
    public void setWeaponPrice(int newWeaponPrice)
    {
        weapon.setPrice(newWeaponPrice);
        //filler
    }
    public void setArmor(Armor newArmor)
    {
        armor = newArmor;
        //filler
    }
    public void setArmorPrice(int newArmorPrice)
    {
        armor.setPrice(newArmorPrice);
        //filler
    }
    public void setWand(Wand newWand) {
        wand = newWand;
        //filler
    }
    public void setWandPrice(int newWandPrice) {
        wand.setPrice(newWandPrice);
        //filler
    }
    public int getWandPrice() {
        return wand.getPrice();
        //filler
    }
    public Wand getWand() {
        return wand;
        //filler
    }
    public Weapon getWeapon()
    {
        return weapon;
        //filler
    }
    public int getWeaponPrice()
    {
        return weapon.getPrice();
        //filler
    }
    public Armor getArmor()
    {
        return armor;
        //filler
    }
    public int getArmorPrice()
    {
        return armor.getPrice();
        //filler
    }
}