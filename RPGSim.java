import java.nio.file.*;
import java.util.*;
import java.io.*;

public class RPGSim {
    static Scanner sc = new Scanner(System.in);
    static Player player;
    static boolean rapulaDefeated = false;
    static boolean challenge = false;
    static String curLocation;
    static final String GRASSLANDS = "the grasslands";
    static final String DARK_CAVE = "a dark cave";
    static final String ANCIENT_JUNGLE = "the Ancient Jungle";
    static final String CLOUD_KINGDOM = "the Cloud Kingdom";
    static final String RAPULA_CASTLE = "Rapula's Castle";
    static final String GREECE = "Greece";
    static Path musicPath = Paths.get("music");
    static Path savePath = Paths.get("saves");
    public static void main(String[] args) {
        //getting the player's name
        System.out.print("Ah, another traveller. Pray tell, what is your name? ");
        String playerName = sc.nextLine();
        //setting up stats
        player = new Player(10, 4, 20,10, playerName);
        Spell fire = new Spell("Fire", 3, 3);
        Spell lightning = new Spell("Lightning", 5, 5);
        Spell leech = new Spell("Leech", 2, 4);
        Spell[] spells = {fire, lightning, leech};
        player.setSpells(spells);
        player.addQuest(new Quest("Kill Rapula", 1, 0, 10000, 5000, new Enemy(250, 69, "Rapula", 500)));
        System.out.println("Welcome, " + playerName + ", to this world.\nRapula has been torturing this world with his terrible soundcloud raps.");
        System.out.println("Go! Save this world from Rapula's trash music!");
        while (true) {
            curLocation = GRASSLANDS;
            menu();
        }
    }
    public static void menu() {
        boolean invalidInput = true;
        Random rand = new Random();
        if (curLocation.equals(RAPULA_CASTLE))
            System.out.println("\nThis is it. Rapula's Castle. Go fulfill your destiny.");
        else if (curLocation.equals(GREECE))
            System.out.println("The ancient Greek dieties have had it good for too long... let's change that.");
        else
            System.out.println("\nYou are now in " + curLocation + ".");
        switch (curLocation) {
            case GRASSLANDS -> {
                Enemy e = new Enemy(20, 5, "goblin", 10);
                e.setWeakness(player.getSpells()[2].getName());
                Quest killGob = new Quest("Kill 5 Goblins", 5, 0, 10, 10, e);
                if (!player.hasQuest(killGob) && player.getLevel() >= 3)
                    player.addQuest(killGob);
                if (Music.isPlaying())
                    Music.stop();
                Music.play(musicPath.toAbsolutePath() + "\\grasslands.wav");
            }
            case DARK_CAVE -> {
                Enemy e = new Enemy(40, 20, "Elder Tarantula", 30);
                Quest killElder = new Quest("Defeat the Elder Tarantula", 1, 0, 25, 25, e);
                if (!player.hasQuest(killElder) && player.getLevel() >= 10)
                    player.addQuest(killElder);
                if (Music.isPlaying())
                    Music.stop();
                Music.play(musicPath.toAbsolutePath() + "\\cave.wav");
            }
            case ANCIENT_JUNGLE -> {
                Enemy e = new Enemy(100, 30, "Nature's Heart", 100);
                e.setWeakness(player.getSpells()[0].getName());
                Quest killHeart = new Quest("Defeat Nature's Heart", 1, 0, 50, 50, e);
                if (!player.hasQuest(killHeart) && player.getLevel() >= 20)
                    player.addQuest(killHeart);
                if (Music.isPlaying())
                    Music.stop();
                Music.play(musicPath.toAbsolutePath() + "\\jungle.wav");
            }
            case CLOUD_KINGDOM -> {
                Enemy e = new Enemy(120, 40, "Verilyx, King of Wings", 120);
                Quest verilyx = new Quest("Defeat Verilyx, ruler of the Cloud Kingdom", 1, 0, 75, 75, e);
                if (!player.hasQuest(verilyx) && player.getLevel() >= 35)
                    player.addQuest(verilyx);
                if (Music.isPlaying())
                    Music.stop();
                Music.play(musicPath.toAbsolutePath() + "\\cloud.wav");
            }
            case RAPULA_CASTLE -> {
                if (Music.isPlaying())
                    Music.stop();
                Music.play(musicPath.toAbsolutePath() + "\\rapula.wav");
            }
            case GREECE -> {
                Enemy e = new Enemy(2000, 500, "Gaia, the Earth-Mother", 2500);
                Quest killGaia = new Quest("Defeat Gaia", 1, 0, 1000, 1000, e);
                if (!player.hasQuest(killGaia) && player.getLevel() >= 55) {
                    ArrayList<Quest> q = player.getQuests();
                    q.add(killGaia);
                    player.setQuests(q);
                }
                if (Music.isPlaying())
                    Music.stop();
                Music.play(musicPath.toAbsolutePath() + "\\greece.wav");
            }
        }
        Music.loop();
        while (invalidInput) {
            try {
                //player's options
                System.out.print("\nSelect an option: 1 to battle, 2 to travel, 3 to check stats, 4 to check quests, 5 for inventory, and 0 to save/load. ");
                int choice = sc.nextInt();
                //making sure that the choice is within the boundaries
                if (choice > 5 || choice < 0) {
                    System.out.println("Invalid option.");
                    invalidInput = true;
                }
                else {
                    invalidInput = false;
                    switch (choice) {
                        case 0 -> {
                            boolean invInput = true;
                            while (invInput) {
                                System.out.println("Press 1 to save, 2 to load: ");
                                int data = sc.nextInt();
                                if (data > 2 || data < 0) {
                                    invInput = true;
                                }
                                else {
                                    invInput = false;
                                    switch (data) {
                                        case 1 -> save();
                                        case 2 -> load();
                                    }
                                }
                            }
                        }
                        case 1 -> {
                            switch (curLocation) {
                                case GRASSLANDS -> grasslands();
                                case DARK_CAVE -> darkCave();
                                case ANCIENT_JUNGLE -> ancientJungle();
                                case CLOUD_KINGDOM -> cloudKingdom();
                                case RAPULA_CASTLE -> rapulaCastle();
                                case GREECE -> Greece();
                            }
                        }
                        case 2 -> travel();
                        case 3 -> stats();
                        case 4 -> checkQuests();
                        case 5 -> {

                        }
                    }
                }
            }
            catch (InputMismatchException ex) {
                System.out.println("Invalid input. Try again.");
                sc.nextLine();
            }
        }
    }
    public static void grasslands() {
        Random rand = new Random();
        int enemyValue = rand.nextInt(4);
        Enemy enemy = new Enemy(1, 0, "placeholder", 0);
        //setting up random enemies and their stats
        switch (enemyValue) {
            case 0 -> {
                 enemy = new Enemy(5, 0, "dummy", 2);
                 enemy.setWeakness(player.getSpells()[0].getName());
            }
            case 1 -> enemy = new Enemy(7, 1, "chicken", 3);
            case 2 -> enemy = new Enemy(10, 4, "boar", 5);
            case 3 -> {
                enemy = new Enemy(20, 5, "goblin", 10);
                enemy.setWeakness(player.getSpells()[2].getName());
                enemy.addItem(new Item("goblin flesh", 2, 2, 50));
                enemy.addItem(new Weapon(10, "goblin knife", 50, 5, true));
            }
            default -> enemy = new Enemy(1, 0, "rare glitch", 0);
        }
        doBattle(enemy);
    }
    public static void darkCave() {
        Random rand = new Random();
        int enemyValue = rand.nextInt(4);
        Enemy enemy = new Enemy(1, 0, "placeholder", 0);
        switch (enemyValue) {
            case 0 -> {
                enemy = new Enemy(15, 5, "baby spider", 20);
                enemy.setWeakness(player.getSpells()[0].getName());
            }
            case 1 -> {
                enemy = new Enemy(20, 10, "adult spider", 25);
                enemy.setWeakness(player.getSpells()[0].getName());
            }
            case 2 -> {
                enemy = new Enemy(10, 10, "vampire bat", 22);
                enemy.setWeakness(player.getSpells()[2].getName());
                enemy.addItem(new Weapon(10, "vampire fang", 10, 33, false));
            }
            case 3 -> {
                enemy = new Enemy(40, 20, "Elder Tarantula", 30);
                enemy.addItem(new Item("Tarantula fur", 50, 1, 10));
            }
            default -> enemy = new Enemy(1, 0, "rare glitch", 0);
        }
        doBattle(enemy);
    }
    public static void ancientJungle() {
        Random rand = new Random();
        int enemyValue = rand.nextInt(4);
        Enemy enemy = new Enemy(1, 0, "placeholder", 0);
        switch (enemyValue) {
            case 0 -> {
                enemy = new Enemy(30, 10, "giant pihranha", 50);
                enemy.setWeakness(player.getSpells()[1].getName());
            }
            case 1 -> {
                enemy = new Enemy(10, 20, "sentient vines", 35);
                enemy.setWeakness(player.getSpells()[0].getName());
            }
            case 2 -> {
                enemy = new Enemy(100, 0, "\uD83E\uDEA8", 50);
                enemy.addItem(new Item("coal", 10, 7, 75));
                enemy.addItem(new Item("iron", 25, 4, 50));
                enemy.addItem(new Item("gold", 100, 2, 20));
                enemy.addItem(new Item("diamond", 1000, 1, 1));
            }
            case 3 -> {
                enemy = new Enemy(100, 30, "Nature's Heart", 100);
                enemy.setWeakness(player.getSpells()[0].getName());
                enemy.addItem(new Item("Nature's Heart's heart", 250, 1, 15));
                challenge = true;
            }
            default -> enemy = new Enemy(1, 0, "rare glitch", 0);
        }
        doBattle(enemy);
    }
    public static void cloudKingdom() {
        Random rand = new Random();
        int enemyValue = rand.nextInt(4);
        Enemy enemy = new Enemy(1, 0, "placeholder", 0);
        switch (enemyValue) {
            case 0 -> {
                enemy = new Enemy(70, 30, "kingdom defender", 75);
                enemy.setWeakness(player.getSpells()[1].getName());
            }
            case 1 -> {
                enemy = new Enemy(60, 60, "balance patrol", 70);
                enemy.setWeakness(player.getSpells()[1].getName());
            }
            case 2 -> enemy = new Enemy(85, 45, "Verilyx's guard", 90);
            case 3 -> {
                enemy = new Enemy(120, 40, "Verilyx, King of Wings", 120);
                enemy.addItem(new Item("Verilyx's feather", 100, 1, 99));
                enemy.addItem(new Item("Verilyx's feather", 100, 2, 75));
                enemy.addItem(new Item("Verilyx's feather", 100, 5, 50));
                enemy.addItem(new Item("Verilyx's feather", 100, 10, 25));
                enemy.addItem(new Item("Verilyx's feather", 100, 50, 10));
                enemy.addItem(new Item("Verilyx's feather", 100, 100, 1));
                challenge = true;
            }
            default -> enemy = new Enemy(1, 0, "rare glitch", 0);
        }
        doBattle(enemy);
    }
    public static void rapulaCastle() {
        Random rand = new Random();
        Enemy enemy = new Enemy(1, 0, "placeholder", 0);
        int enemyValue = rand.nextInt(2);
        challenge = true;
        switch (enemyValue) {
            case 0 -> {
                enemy = new Enemy(250, 69, "Rapula", 500);
                enemy.addItem(new Item("Rapula's mixtape", 1, 1, 100));
                System.out.println("Yo yo yo, it's Rapula in the house. My house. What are you doing in my house? Scram, kid, before I make a diss track on you!");
            }
            case 1 -> {
                enemy = new Enemy(200, 50, "Allenator, the Punk Rock Werewolf", 300);
                enemy.addItem(new Item("Allenator's Guitar", 1000, 1, 10));
            }
        }
        doBattle(enemy);
    }
    public static void Greece() {
        Random rand = new Random();
        Enemy enemy = new Enemy(1, 0, "placeholder", 0);
        int enemyValue = rand.nextInt(15);
        challenge = true;
        switch (enemyValue) {
            case 0 -> {
                enemy = new Enemy(500, 85, "Hestia", 750);
                enemy.addItem(new Item("Hestia's hearth", 1000, 1, 10));
            }
            case 1 -> {
                enemy = new Enemy(400, 100, "Dionysus", 750);
                enemy.addItem(new Item("Dionysus's wine", 1000, 1, 10));
            }
            case 2 -> {
                enemy = new Enemy(300, 150, "Hermes", 750);
                enemy.addItem(new Item("Talaria", 1500, 1, 10));
                enemy.setWeakness(player.getSpells()[1].getName());
            }
            case 3 -> {
                enemy = new Enemy(600, 50, "Aphrodite", 750);
                enemy.setWeakness(player.getSpells()[2].getName());
            }
            case 4 -> {
                enemy = new Enemy(600, 100, "Demeter", 800);
                enemy.setWeakness(player.getSpells()[0].getName());
            }
            case 5 -> {
                enemy = new Enemy(800, 125, "Hephaestus", 900);
                enemy.addItem(new Weapon(175, "Hephaestus's hammer", 2000, 1, true));
            }
            case 6 -> {
                enemy = new Enemy(400, 200, "Apollo", 900);
                enemy.addItem(new Item("Apollo's mini-sun", 2000, 1, 10));
            }
            case 7 -> {
                enemy = new Enemy(600, 150, "Artemis", 900);
                enemy.addItem(new Item("Artemis's bow", 2000, 1, 10));
            }
            case 8 -> {
                enemy = new Enemy(700, 200, "Athena", 1000);
                enemy.addItem(new Weapon(190, "Athena's spear", 1000, 1, true));
                enemy.addItem(new Item("Athena's Guide to a Perfect War", 10000, 1, 1));
            }
            case 9 -> {
                enemy = new Enemy(1000, 150, "Hera", 1100);
            }
            case 10 -> {
                enemy = new Enemy(900, 300, "Ares", 1000);
                enemy.addItem(new Item("Ares's Guide to a Perfect Battle", 10000, 1, 1));
            }
            case 11 -> {
                enemy = new Enemy(1000, 200, "Hades", 1200);
                enemy.addItem(new Weapon(200, "Hades's Scyth", 3500, 5, true));
            }
            case 12 -> {
                enemy = new Enemy(1250, 150, "Poseidon", 1200);
                enemy.addItem(new Weapon(250, "Poseidon's Trident", 4000, 3, true));
            }
            case 13 -> {
                enemy = new Enemy(1500, 300, "Zeus", 1500);
                enemy.addItem(new Wand(300, "Pure Lightning Rod", 5000, 1, true));
            }
            case 14 -> {
                enemy = new Enemy(2000, 500, "Gaia, the Earth-Mother", 2500);
            }
        }
        doBattle(enemy);
    }

    public static void doBattle(Enemy enemy){
        boolean invalidInput = true;
        Music.stop();
        //resetting stats before battle
        player.setHealth(player.getMaxHealth());
        player.setCurrentMana(player.getTotalMana());
        if (challenge) {
            Music.play(musicPath.toAbsolutePath() + "\\challenge.wav");
            Music.loop();
            System.out.println("You challenge " + enemy.getName() + ".");
            challenge = false;
        }
        else {
            Music.play(musicPath.toAbsolutePath() + "\\battle.wav");
            Music.loop();
            System.out.println("\nA wild " + enemy.getName() + " appeared!");
        }
        boolean battleOver = false;
        boolean playerWon = false;
        int attackChoice = 0;
        while (!battleOver) {
            //making sure that the health and mana doesn't go over max
            if (player.getCurrentMana() > player.getTotalMana()) {
                player.setCurrentMana(player.getTotalMana());
            }
            if (player.getHealth() > player.getMaxHealth()) {
                player.setHealth(player.getMaxHealth());
            }
            invalidInput = true;
            while (invalidInput) {
                try {
                    System.out.print("\nSelect an attack: 1 for melee, 2 for spells: ");
                    attackChoice = sc.nextInt();
                    if (attackChoice > 2 || attackChoice < 1) {
                        System.out.println("Invalid Input");
                    }
                    else {
                        invalidInput = false;
                    }
                }
                catch (InputMismatchException inputException) {
                    System.out.println("Invalid input.");
                    sc.nextLine();
                }
            }

            switch (attackChoice) {
                case 1 -> {
                    System.out.println(player.getName() + " used a melee attack.");
                    player.attack(enemy);
                }
                case 2 -> {
                    boolean invInput = true;
                    while (invInput) {
                        try {
                            System.out.print("Press 1 for Fire, 2 for Lightning, or 3 for Leech: ");
                            int spellChoice = sc.nextInt();
                            if (spellChoice > player.getSpells().length || spellChoice < 1) {
                                System.out.println("Invalid input.");
                            }
                            else {
                                System.out.println(player.getName() + " used " + player.getSpells()[spellChoice - 1].getName() + ".");
                                player.spellAttack(enemy, spellChoice);
                                if (spellChoice == 3)
                                    player.getSpells()[2].heal(player, player.getSpells()[2].getDamage() / 2);
                                invInput = false;
                            }
                        }
                        catch (InputMismatchException e) {
                            System.out.println("Invalid input.");
                            sc.nextLine();
                        }
                        catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Invalid input, please input a number from 1 to 3");
                            sc.nextLine();
                        }
                        if (!invInput) {
                            break;
                        }
                    }
                }
            }
            //looks nicer/more appropriate with this
            if (enemy.getHealth() <= 0) {
                enemy.setHealth(0);
            }
            if (challenge)
                System.out.println(enemy.getName() + " has " + enemy.getHealth() + "/" + enemy.getMaxHealth() + " health left.");
            else
                System.out.println("\nThe " + enemy.getName() + " has " + enemy.getHealth() + "/" + enemy.getMaxHealth() + " health left.");
            //win condition
            if (enemy.getHealth() <= 0) {
                playerWon = true;
                battleOver = true;
                break;
            }
            enemy.attack(player);
            if (challenge)
                System.out.println(enemy.getName() + " attacked!");
            else
                System.out.println("The " + enemy.getName() + " attacked!");
            //looks nicer/more appropriate with this
            if (player.getHealth() < 0) {
                player.setHealth(0);
            }
            System.out.println("\n" + player.getName() + " has " + player.getHealth() + "/" + player.getMaxHealth() + " health left.");
            //lose condition
            if (player.getHealth() <= 0) {
                playerWon = false;
                battleOver = true;
                break;
            }
            System.out.println("You have " + player.getCurrentMana() + "/" + player.getTotalMana() + " mana left.");
            //players should be able to keep using spells at some point
            System.out.println("You regenerated 1 mana.");
            player.setCurrentMana(player.getCurrentMana() + 1);
        }
        Music.stop();
        if (playerWon) {
            Music.play(musicPath.toAbsolutePath() + "\\win.wav");
            Music.loop();
            System.out.println("\nYou win!");
            enemy.drop(player);
            for (int c = 0; c < player.getQuests().size(); c++) {
                if (player.getQuests().get(c).checkSubject(enemy))
                    player.resolveQuest(c);
            }
            if (enemy.getName().equals("Rapula")) {
                System.out.println("Your journey is done... or is it?");
                rapulaDefeated = true;
            }
            player.setCoins(player.getCoins() + enemy.getExpAmount());
            player.setExp(player.getExp() + enemy.getExpAmount());
            //level up, stats get buffed
            while (player.getExp() >= player.getNeededExp()) {
                System.out.println("Level up!");
                player.setExp(player.getExp() - player.getNeededExp());
                player.setNeededExp(player.getNeededExp() + 10);
                player.setMaxHealth(player.getMaxHealth() + 1);
                player.setDamage(player.getDamage() + 1);
                player.setTotalMana(player.getTotalMana() + 1);
                for (int c = 0; c < player.getSpells().length; c++) {
                    player.getSpells()[c].setDamage(player.getSpells()[c].getDamage() + 1);
                }
                player.setLevel(player.getLevel() + 1);
            }
            System.out.print("Press enter to continue. ");
            sc.nextLine();
            String done = sc.nextLine();
        }
        else if (!playerWon) {
            Music.play(musicPath.toAbsolutePath() + "\\death.wav");
            System.out.println("\nYou lose...");
            System.out.print("Press enter to continue. ");
            sc.nextLine();
            String useless = sc.nextLine();
        }
    }
    public static void travel() {
        String cave = "", jungle = "", cloud = "", rapula = "", greece = "";
        int choice = 0;
        boolean invalidInput = true;
        while (invalidInput) {
            try {
                //everything gets unlocked at the right level
                if (player.getLevel() >= 8)
                    cave = ", \n3 for the dark cave";
                if (player.getLevel() >= 15)
                    jungle = ", 4 for the ancient jungle";
                if (player.getLevel() >= 30)
                    cloud = ", \n5 for the cloud kingdom,";
                if (player.getLevel() >= 50)
                    rapula = ", or 6 for Rapula's castle";
                if (rapulaDefeated || player.getLevel() >= 60)
                    greece = ", or... 7 to go to Greece";
                System.out.print("\nWhere would you like to travel? 1 for grasslands, 2 for the village shop" + cave + jungle + cloud + rapula + greece + ". ");
                choice = sc.nextInt();
                //couldn't find a shorter way to do this
                if (player.getLevel() < 8) {
                    //limits choices
                    if (choice > 2 || choice < 1) {
                        System.out.println("Invalid input, try again.");
                        invalidInput = true;
                    }
                    else {
                        invalidInput = false;
                    }
                }
                else if (rapulaDefeated || player.getLevel() >= 60) {
                    if (choice > 7 || choice < 1) {
                        System.out.println("Invalid input, try again.");
                        invalidInput = true;
                    }
                    else {
                        invalidInput = false;
                    }
                }
                else if (player.getLevel() >= 50) {
                    if (choice > 6 || choice < 1) {
                        System.out.println("Invalid input, try again.");
                        invalidInput = true;
                    }
                    else {
                        invalidInput = false;
                    }
                }
                else if (player.getLevel() >= 30) {
                    if (choice > 5 || choice < 1) {
                        System.out.println("Invalid input, try again.");
                        invalidInput = true;
                    }
                    else {
                        invalidInput = false;
                    }
                }
                else if (player.getLevel() >= 15) {
                    if (choice > 4 || choice < 1) {
                        System.out.println("Invalid input, try again.");
                        invalidInput = true;
                    }
                    else {
                        invalidInput = false;
                    }
                }

                else if (player.getLevel() >= 8) {
                    if (choice > 3 || choice < 1) {
                        System.out.println("Invalid input, try again.");
                        invalidInput = true;
                    }
                    else {
                        invalidInput = false;
                    }
                }
            }
            catch (InputMismatchException mismatch) {
                System.out.println("Invalid input, try again.");
                sc.nextLine();
            }
        }
        switch (choice) {
            //limited choices from earlier makes this the only switch statement for travelling to other places
            case 1 -> {
                curLocation = GRASSLANDS;
                while (true)
                    menu();
            }
            case 2 -> {
                while (true)
                    villageShop();
            }
            case 3 -> {
                curLocation = DARK_CAVE;
                while (true)
                    menu();
            }
            case 4 -> {
                curLocation = ANCIENT_JUNGLE;
                while (true)
                    menu();
            }
            case 5 -> {
                curLocation = CLOUD_KINGDOM;
                while (true)
                    menu();
            }
            case 6 ->{
                curLocation = RAPULA_CASTLE;
                while (true)
                    menu();
            }
            case 7 -> {
                curLocation = GREECE;
                while (true)
                    menu();
            }
        }

    }
    public static void villageShop() {
        Music.stop();
        Music.play(musicPath.toAbsolutePath() + "\\shop.wav");
        Music.loop();
        //appear once you get the last weapons
        Shop shop = new Shop();
        while (true) {
            //checks the amount of weapons the player bought, updates shop based on that
            switch (player.getWeaponsBought()) {
                case 0 -> shop.setWeapon(new Weapon(2, "wooden sword", 50, false));
                case 1 -> shop.setWeapon(new Weapon(5, "iron sword", 100, false));
                case 2 -> shop.setWeapon(new Weapon(10, "Buster Sword", 250, false));
                case 3 -> shop.setWeapon(new Weapon(20, "Demon Machete", 2500, false));
                case 4 -> shop.setWeapon(new Weapon(45, "Tchaikovsky's Cannon Sword", 10000, false));
                case 5 -> shop.setWeapon(new Weapon(145, "Satan's Lance", 20000, false));
                default -> {
                    String wpn = "Satan's Lance";
                    int dmg = 150;
                    int price = 20000;
                    for (int c = 0; c < player.getWeaponsBought() - 5; c++) {
                        wpn += "+";
                        dmg += 50;
                        price += 5000;
                    }
                    shop.setWeapon(new Weapon(dmg, wpn, price, false));
                }
            }
            switch (player.getArmorBought()) {
                case 0 -> shop.setArmor(new Armor(2, "leather tunic", 50, false));
                case 1 -> shop.setArmor(new Armor(5, "iron armor", 100, false));
                case 2 -> shop.setArmor(new Armor(10, "Dreadlord Suit", 250, false));
                case 3 -> shop.setArmor(new Armor(20, "Gundam", 2500, false));
                case 4 -> shop.setArmor(new Armor(45, "Mozart's Invincible Suit", 10000, false));
                case 5 -> shop.setArmor(new Armor(145, "God's Robes", 20000, false));
                default -> {
                    String amr = "God's Robes";
                    int def = 150;
                    int price = 20000;
                    for (int c = 0; c < player.getWeaponsBought() - 5; c++) {
                        amr += "+";
                        def += 50;
                        price += 5000;
                    }
                    shop.setWeapon(new Weapon(def, amr, price, false));
                }
            }
            switch (player.getWeaponsBought()) {
                case 0 -> shop.setWand(new Wand(2, "starter wand", 50, false));
                case 1 -> shop.setWand(new Wand(5, "apprentice wand", 100, false));
                case 2 -> shop.setWand(new Wand(10, "Starlight Wand", 250, false));
                case 3 -> shop.setWand(new Wand(25, "Phoenix Wand", 2500, false));
                case 4 -> shop.setWand(new Wand(50, "Beethoven's Baton", 10000, false));
                case 5 -> shop.setWand(new Wand(150, "Jesus's Blessings", 20000, false));
                default -> {
                    String wnd = "Jesus's Blessings";
                    int dmg = 150;
                    int price = 20000;
                    for (int c = 0; c < player.getWeaponsBought() - 5; c++) {
                        wnd += "+";
                        dmg += 50;
                        price += 5000;
                    }
                    shop.setWeapon(new Weapon(dmg, wnd, price, false));
                }
            }

            boolean invalidInput = true;
            while (invalidInput) {
                try {
                    System.out.print("\nSelect an option: 1 to shop, or 2 to exit the village. ");
                    int choice = sc.nextInt();
                    if (choice > 2 || choice < 1) {
                        System.out.println("Invalid input.");
                        invalidInput = true;
                    }
                    else {
                        invalidInput = false;
                        switch (choice) {
                            case 1 -> {
                                System.out.println("\nWeapon: " + shop.getWeapon() + "\tCost: " + shop.getWeaponPrice() + " (press 1 to buy)");
                                System.out.println("Armor: " + shop.getArmor() + "\tCost: " + shop.getArmorPrice() + " (press 2 to buy)");
                                System.out.println("Wand: " + shop.getWand() + "\tCost: " + shop.getWandPrice() + " (press 3 to buy)");
                                System.out.println("Press 4 to sell your things.");
                                System.out.println("Press 5 to exit.");
                                boolean invInput = true;
                                while (invInput) {
                                    try {
                                        int buy = sc.nextInt();
                                        if (buy > 5 || buy < 1) {
                                            System.out.println("Invalid input.");
                                            invInput = true;
                                        }
                                        else {
                                            invInput = false;
                                            switch (buy) {
                                                case 1 -> {
                                                    if (player.getCoins() >= shop.getWeaponPrice()) {
                                                        player.buyWeapon(shop);
                                                    }
                                                }
                                                case 2 -> {
                                                    if (player.getCoins() >= shop.getArmorPrice()) {
                                                        player.buyArmor(shop);
                                                    }
                                                }
                                                case 3 -> {
                                                    if (player.getCoins() >= shop.getWandPrice()) {
                                                        player.buyWand(shop);
                                                    }
                                                }
                                                case 4 -> {
                                                    boolean invalid = true;
                                                    while (invalid) {
                                                        try {
                                                            sc.nextLine();
                                                            System.out.println("Press 1 to sell items, 2 to sell weapons, 3 to sell armor, 4 to sell wands, or 5 to go back: ");
                                                            int option = sc.nextInt();
                                                            switch (option) {
                                                                case 1 -> {
                                                                    invalid = false;
                                                                    System.out.println("Here are your items: \n" + player.getItems());
                                                                    System.out.println("Type out what you want to sell: ");
                                                                    String sell = sc.nextLine().toLowerCase();
                                                                    Item i = player.findItemLower(sell);
                                                                    if (i.getName().equals("nothing"))
                                                                        System.out.println("You don't have that. Try again.");
                                                                    else {
                                                                        invalid = false;
                                                                        boolean exception = true;
                                                                        while (exception) {
                                                                            try {
                                                                                System.out.println("How many would you like to sell?");
                                                                                int amount = sc.nextInt();
                                                                                player.sellItem(i, amount);
                                                                                exception = false;
                                                                            }
                                                                            catch (InputMismatchException ae) {
                                                                                System.out.println("Invalid option. Try again.");
                                                                                sc.nextLine();
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                                case 2 -> {
                                                                    invalid = false;
                                                                    System.out.println("Here are your weapons: \n" + player.getWeapons());
                                                                    System.out.println("Type out what you want to sell: ");
                                                                    String sell = sc.nextLine().toLowerCase();
                                                                    Weapon w = player.findWeaponLower(sell);
                                                                    if (w.getName().equals("nothing"))
                                                                        System.out.println("You don't have that. Try again.");
                                                                    else {
                                                                        invalid = false;
                                                                        boolean exception = true;
                                                                        while (exception) {
                                                                            try {
                                                                                System.out.println("How many would you like to sell?");
                                                                                int amount = sc.nextInt();
                                                                                player.sellWeapon(w, amount);
                                                                                exception = false;
                                                                            }
                                                                            catch (InputMismatchException ae) {
                                                                                System.out.println("Invalid option. Try again.");
                                                                                sc.nextLine();
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                                case 3 -> {
                                                                    invalid = false;
                                                                    System.out.println("Here are your armors: \n" + player.getArmors());
                                                                    System.out.println("Type out what you want to sell: ");
                                                                    String sell = sc.nextLine().toLowerCase();
                                                                    Armor a = player.findArmorLower(sell);
                                                                    if (a.getName().equals("nothing"))
                                                                        System.out.println("You don't have that. Try again.");
                                                                    else {
                                                                        invalid = false;
                                                                        boolean exception = true;
                                                                        while (exception) {
                                                                            try {
                                                                                System.out.println("How many would you like to sell?");
                                                                                int amount = sc.nextInt();
                                                                                player.sellArmor(a, amount);
                                                                                exception = false;
                                                                            }
                                                                            catch (InputMismatchException ae) {
                                                                                System.out.println("Invalid option. Try again.");
                                                                                sc.nextLine();
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                                case 4 -> {
                                                                    invalid = false;
                                                                    System.out.println("Here are your items: \n" + player.getWands());
                                                                    System.out.println("Type out what you want to sell: ");
                                                                    String sell = sc.nextLine().toLowerCase();
                                                                    Wand w = player.findWandLower(sell);
                                                                    if (w.getName().equals("nothing"))
                                                                        System.out.println("You don't have that. Try again.");
                                                                    else {
                                                                        invalid = false;
                                                                        boolean exception = true;
                                                                        while (exception) {
                                                                            try {
                                                                                System.out.println("How many would you like to sell?");
                                                                                int amount = sc.nextInt();
                                                                                player.sellWand(w, amount);
                                                                                exception = false;
                                                                            }
                                                                            catch (InputMismatchException ae) {
                                                                                System.out.println("Invalid option. Try again.");
                                                                                sc.nextLine();
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                                case 5 -> invalid = false;
                                                            }
                                                        }
                                                        catch (InputMismatchException bruh) {
                                                            System.out.println("Invalid input. Try again.");
                                                        }
                                                    }
                                                }
                                                case 5 -> {
                                                    break;
                                                }
                                            }

                                        }

                                    }
                                    catch (InputMismatchException inputException) {
                                        System.out.println("Invalid input.");
                                        sc.nextLine();
                                    }
                                }
                            }
                            case 2 -> {
                                while(true) {
                                    travel();
                                }
                            }
                        }
                    }
                }
                catch (InputMismatchException e) {
                    System.out.println("Invalid input.");
                    sc.nextLine();
                }
            }
        }
    }
    public static void stats() {
        System.out.println("\nPlayer Name: " + player.getName());
        System.out.println("Level: " + player.getLevel());
        System.out.println("Health: " + player.getMaxHealth());
        System.out.println("Damage: " + player.getDamage());
        System.out.println("Mana: " + player.getTotalMana());
        System.out.println("Fire Damage: " + player.getSpells()[0].getDamage());
        System.out.println("Lightning Damage: " + player.getSpells()[1].getDamage());
        System.out.println("Leech Damage: " + player.getSpells()[2].getDamage());
        System.out.println("Experience: " + player.getExp() + "/" + player.getNeededExp());
        System.out.println("Coins: " + player.getCoins());
        System.out.println("Equipped Weapon: " + player.getCurrWeapon());
        System.out.println("Equipped Armor: " + player.getCurrArmor());
        System.out.println("Equipped Wand: " + player.getCurrWand());
    }

    public static void checkQuests() {
        System.out.println("Here are your current quests: ");
        System.out.println(player.getQuests());
        for (int c = 0; c < player.getQuests().size(); c++) {
            player.resolveQuest(c);
        }
        System.out.println("Here's your quests after resolving completed ones: ");
        System.out.println(player.getQuests());
    }

    public static void inventory() {
        boolean invalidInput = true;
        while (invalidInput) {
            try {
                System.out.println("Select 1 for items, 2 for weapons, 3 for armor, 4 for wands, or 5 to go back: ");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1 -> {
                        System.out.println(player.getItems());
                        invalidInput = false;
                    }
                    case 2 -> {
                        invalidInput = false;
                        boolean invInput = true;
                        while (invInput) {
                            try {
                                System.out.println(player.getWeapons());
                                System.out.println("Select 1 to equip by name, 2 to equip by index, or 3 to go back: ");
                                int option = sc.nextInt();
                                switch (option) {
                                    case 1 -> {
                                        invInput = false;
                                        boolean invInp = true;
                                        while (invInp) {
                                            try {
                                                System.out.println("Select the index to equip it, or press 0 to go back: ");
                                                int equip = sc.nextInt();
                                                if (equip - 1 >= 0 && equip - 1 < player.getWeapons().size()) {
                                                    player.equipWeapon(equip - 1);
                                                    System.out.println("Weapon equipped!");
                                                    invInp = false;
                                                }
                                                else if (equip == 0)
                                                    invInp = false;
                                                else {
                                                    System.out.println("Invalid input. Try again.");
                                                }
                                            } catch (InputMismatchException bru) {
                                                System.out.println("Invalid input. Try again.");
                                                sc.nextLine();
                                            }
                                        }
                                    }
                                    case 2 -> {
                                        invInput = false;
                                        boolean invInp = true;
                                        while (invInp) {
                                            System.out.println("Input the weapon name, or press 0 to exit: ");
                                            String name = sc.nextLine();
                                            if (player.findWeaponLower(name) != null) {
                                                player.equipWeapon(player.findWeaponLower(name));
                                                invInp = false;
                                            }
                                            else if (name == "0") {
                                                invInp = false;
                                            }
                                            else
                                                System.out.println("Invalid input. Try again.");
                                        }
                                    }
                                    case 3 -> invInput = false;
                                }
                            }
                            catch (InputMismatchException bruh) {
                                System.out.println("Invalid input. Try again.");
                                sc.nextLine();
                            }
                        }
                    }
                    case 3 -> {
                        invalidInput = false;
                        boolean invInput = true;
                        while (invInput) {
                            try {
                                System.out.println(player.getArmors());
                                System.out.println("Select 1 to equip by name, 2 to equip by index, or 3 to go back: ");
                                int option = sc.nextInt();
                                switch (option) {
                                    case 1 -> {
                                        invInput = false;
                                        boolean invInp = true;
                                        while (invInp) {
                                            try {
                                                System.out.println("Select the index to equip it, or press 0 to go back: ");
                                                int equip = sc.nextInt();
                                                if (equip - 1 >= 0 && equip - 1 < player.getArmors().size()) {
                                                    player.equipArmor(equip - 1);
                                                    System.out.println("Armor equipped!");
                                                    invInp = false;
                                                }
                                                else if (equip == 0)
                                                    invInp = false;
                                                else {
                                                    System.out.println("Invalid input. Try again.");
                                                }
                                            } catch (InputMismatchException bru) {
                                                System.out.println("Invalid input. Try again.");
                                                sc.nextLine();
                                            }
                                        }
                                    }
                                    case 2 -> {
                                        invInput = false;
                                        boolean invInp = true;
                                        while (invInp) {
                                            System.out.println("Input the armor name, or press 0 to exit: ");
                                            String name = sc.nextLine();
                                            if (player.findArmorLower(name) != null) {
                                                player.equipArmor(player.findArmorLower(name));
                                                invInp = false;
                                            }
                                            else if (name == "0") {
                                                invInp = false;
                                            }
                                            else
                                                System.out.println("Invalid input. Try again.");
                                        }
                                    }
                                    case 3 -> invInput = false;
                                }
                            }
                            catch (InputMismatchException bruh) {
                                System.out.println("Invalid input. Try again.");
                                sc.nextLine();
                            }
                        }
                    }
                    case 4 -> {
                        invalidInput = false;
                        boolean invInput = true;
                        while (invInput) {
                            try {
                                System.out.println(player.getWands());
                                System.out.println("Select 1 to equip by name, 2 to equip by index, or 3 to go back: ");
                                int option = sc.nextInt();
                                switch (option) {
                                    case 1 -> {
                                        invInput = false;
                                        boolean invInp = true;
                                        while (invInp) {
                                            try {
                                                System.out.println("Select the index to equip it, or press 0 to go back: ");
                                                int equip = sc.nextInt();
                                                if (equip - 1 >= 0 && equip - 1 < player.getWands().size()) {
                                                    player.equipWand(equip - 1);
                                                    System.out.println("Wand equipped!");
                                                    invInp = false;
                                                }
                                                else if (equip == 0)
                                                    invInp = false;
                                                else {
                                                    System.out.println("Invalid input. Try again.");
                                                }
                                            } catch (InputMismatchException bru) {
                                                System.out.println("Invalid input. Try again.");
                                                sc.nextLine();
                                            }
                                        }
                                    }
                                    case 2 -> {
                                        invInput = false;
                                        boolean invInp = true;
                                        while (invInp) {
                                            System.out.println("Input the wand name, or press 0 to exit: ");
                                            String name = sc.nextLine();
                                            if (player.findWandLower(name) != null) {
                                                player.equipWand(player.findWandLower(name));
                                                invInp = false;
                                            }
                                            else if (name == "0") {
                                                invInp = false;
                                            }
                                            else
                                                System.out.println("Invalid input. Try again.");
                                        }
                                    }
                                    case 3 -> invInput = false;
                                }
                            }
                            catch (InputMismatchException bruh) {
                                System.out.println("Invalid input. Try again.");
                                sc.nextLine();
                            }
                        }
                    }
                    case 5 -> invalidInput = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Try again.");
                sc.nextLine();
            }
        }
        menu();
    }

    //copied this from https://www.tutorialspoint.com/java/java_serialization.htm lol
    public static void save() {
        try {
            FileOutputStream fileOut = new FileOutputStream(savePath + "\\" + player.getName() + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(player);
            out.close();
            fileOut.close();
            System.out.println("Data saved.");
        }
        catch (IOException i) {
            i.printStackTrace();
            System.out.println("error");
        }
    }
    public static void load() {
        String name = new String(player.getName());
        player = null;
        try {
            FileInputStream fileIn = new FileInputStream(savePath + "\\" + name + ".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            player = (Player) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Successfully loaded.");
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Player class not found");
            c.printStackTrace();
            return;
        }
    }
}