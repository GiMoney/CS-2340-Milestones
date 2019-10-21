import java.util.Random;
import java.util.ArrayList;
import java.util.*;
import java.util.List;
import java.util.Collections;

public class Universe extends Game {
    protected ArrayList<Region> region = new ArrayList<>(10);
    private LinkedList<String> preAgItems;
    private LinkedList<String> agItems;
    private LinkedList<String> medItems;
    private LinkedList<String> renItems;
    private LinkedList<String> indItems;
    private LinkedList<String> modItems;
    private LinkedList<String> futItems;
    private LinkedList<String> darkItems;
    private LinkedList<String> steamItems;
    private LinkedList<String> cybItems;

    public Universe(String[] name) {
        populateLists();
        ArrayList<TechLevel> tech = new ArrayList<>(10);
        List<String> duplic = Arrays.asList(name);
        Collections.shuffle(duplic);
        Set<Integer> setX = new HashSet<>();
        Set<Integer> setY = new HashSet<>();

        Random r = new Random();
        while (setX.size() < 10 || setY.size() < 10) {
            int x = r.nextInt(20 + 20) - 20;
            int y = r.nextInt(20 + 20) - 20;
            setX.add(x);
            setY.add(y);
        }

        Iterator itr = setX.iterator();
        Iterator itr2 = setY.iterator();
        int g = 0;
        int p = 0;

        while (p < 10) {
            Random random = new Random();
            TechLevel randomX = TechLevel.values()[random.nextInt(TechLevel.values().length)];
            if (!tech.contains(randomX)) {
                tech.add(randomX);
                p++;
            }
        }
        while (itr.hasNext() && itr2.hasNext()) {
            region.add(new Region((Integer) itr.next() * 10,
                    (Integer) itr2.next() * 10, tech.get(g), name[g]));
            g++;
        }

        player.setRegion(name[0]);
        player.setRegion1(region.get(0));
        player.setX(region.get(0).getX());
        player.setY(region.get(0).getY());

        for (int i = 0; i < region.size(); i++) {
            switch (region.get(i).getTechLevel()) {
            case PREAG :
                region.get(i).setItems(preAgItems);
                break; // optional

            case AGRICULTURE :
                region.get(i).setItems(agItems);
                break; // optional

            case MEDIEVAL :
                region.get(i).setItems(medItems);
                break;

            case RENAISSANCE :
                region.get(i).setItems(renItems);
                break;

            case INDUSTRIAL :
                region.get(i).setItems(indItems);
                break;

            case MODERN :
                region.get(i).setItems(modItems);
                break;

            case FUTURISTIC :
                region.get(i).setItems(futItems);
                break;

            case DARK :
                region.get(i).setItems(darkItems);
                break;

            case STEAMPUNK :
                region.get(i).setItems(steamItems);
                break;

            // You can have any number of case statements.
            default : // Optional
                region.get(i).setItems(cybItems);
            }
            region.get(i).setTechLevel(tech.get(i));
        }
        Game.region = region;
    }

    private void populateLists() {
        preAgItems = new LinkedList<>(); // NEED to add quantity
        preAgItems.add("Fuel + 10"); // Important need to update fuel afterwards
        preAgItems.add("Stone");
        preAgItems.add("Dirt");
        preAgItems.add("Wood");
        preAgItems.add("Leather");
        preAgItems.add("Fish");
        preAgItems.add("Deer Meat");
        preAgItems.add("Dinosaur Meat");
        preAgItems.add("Water");
        preAgItems.add("Gold");

        agItems = new LinkedList<>(); // NEED to add quantity
        agItems.add("Fuel + 10"); // Important need to update fuel afterwards
        agItems.add("Seeds");
        agItems.add("Dirt");
        agItems.add("Rice");
        agItems.add("Corn");
        agItems.add("Hatchet");
        agItems.add("Hoe");
        agItems.add("Stone");
        agItems.add("Water");
        agItems.add("Gold");

        medItems = new LinkedList<>(); // NEED to add quantity
        medItems.add("Fuel + 10"); // Important need to update fuel afterwards
        medItems.add("Steel Sword");
        medItems.add("Steel Armor");
        medItems.add("Rations");
        medItems.add("Flintlock Pistol");
        medItems.add("Clout");
        medItems.add("Cannon");
        medItems.add("Horses");
        medItems.add("Water");
        medItems.add("Gold");

        renItems = new LinkedList<>(); // NEED to add quantity
        renItems.add("Fuel + 10"); // Important need to update fuel afterwards
        renItems.add("Artwork");
        renItems.add("Silver Jewelry");
        renItems.add("Baked pastry");
        renItems.add("Musket");
        renItems.add("Clout");
        renItems.add("Marble Statue");
        renItems.add("Silk clothing");
        renItems.add("Water");
        renItems.add("Gold");

        indItems = new LinkedList<>(); // NEED to add quantity
        indItems.add("Fuel + 10"); // Important need to update fuel afterwards
        indItems.add("Coal");
        indItems.add("Revolver");
        indItems.add("Bread");
        indItems.add("Steam engine");
        indItems.add("Car v1");
        indItems.add("Bike");
        indItems.add("Stopwatch");
        indItems.add("Water");
        indItems.add("Gold");


        modItems = new LinkedList<>(); // NEED to add quantity
        modItems.add("Fuel + 10"); // Important need to update fuel afterwards
        modItems.add("Car v2 (autonomous)");
        modItems.add("Pizza");
        modItems.add("Bitcoin");
        modItems.add("SmartPhone v1");
        modItems.add("Airpods");
        modItems.add("Computer");
        modItems.add("AR-15 assault rifle");
        modItems.add("Water");
        modItems.add("Gold");


        futItems = new LinkedList<>(); // NEED to add quantity
        futItems.add("Fuel + 10"); // Important need to update fuel afterwards
        futItems.add("car version v3 (flying)");
        futItems.add("Laser pistol");
        futItems.add("3d-printed food");
        futItems.add("Robotic arm");
        futItems.add("Microchip implant");
        futItems.add("SmartPhone v2");
        futItems.add("Teleportation device v1");
        futItems.add("Water");
        futItems.add("Gold");


        darkItems = new LinkedList<>(); // NEED to add quantity
        darkItems.add("Fuel + 10"); // Important need to update fuel afterwards
        darkItems.add("Cocaine");
        darkItems.add("Dirt");
        darkItems.add("Bread");
        darkItems.add("Knife");
        darkItems.add("Opium");
        darkItems.add("Weed");
        darkItems.add("Beer");
        darkItems.add("Water");
        darkItems.add("Gold");

        steamItems = new LinkedList<>(); // NEED to add quantity
        steamItems.add("Fuel + 10"); // Important need to update fuel afterwards
        steamItems.add("Coal");
        steamItems.add("Binoculars");
        steamItems.add("Revolver");
        steamItems.add("Steam Machina suit");
        steamItems.add("Top hat");
        steamItems.add("Steam core");
        steamItems.add("Animatronic pet");
        steamItems.add("Water");
        steamItems.add("Gold");

        cybItems = new LinkedList<>(); // NEED to add quantity
        cybItems.add("Fuel + 10"); // Important need to update fuel afterwards
        cybItems.add("Cybernetic nav chip");
        cybItems.add("Plasma cannon pistol");
        cybItems.add("Plasma engine");
        cybItems.add("Teleportation device v2");
        cybItems.add("SmartPhone v3");
        cybItems.add("Cybernet VPN");
        cybItems.add("Cybernetic body");
        cybItems.add("Water");
        cybItems.add("Gold");

    }
}
