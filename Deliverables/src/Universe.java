import java.util.Random;
import java.util.ArrayList;
import javax.swing.*;
import java.util.*;
import java.util.List;
import java.util.Collections;

public class Universe extends Game {
    private List<String> duplic;
    private ArrayList<TechLevel> tech;
    private ArrayList<Region> region;
    private static String[] name = {
            "Alpha-20", "Beta-43", "Charlie-28",
            "Delta-8", "EEEEE-E", "Falcon-69",
            "Gamma-Hamma", "Helix-Felix", "I-99", "Ben-10"
    };

    public Universe() {
        tech = new ArrayList<>(10);
        region = new ArrayList<>(10);

        duplic = Arrays.asList(name);
        Collections.shuffle(duplic);
        Set<Integer> setX = new HashSet<>();
        Set<Integer> setY = new HashSet<>();

        Random r = new Random();
        while(setX.size() < 10 || setY.size() < 10) {
            int x = r.nextInt(40) - 20;
            int y = r.nextInt(40) - 20;
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
            region.add(new Region((Integer)itr.next() * 10, (Integer) itr2.next() * 10, tech.get(g), name[g]));
            g++;
        }

        player.setRegion(region.get(0));

        super.region = region;
        super.regionList = new JComboBox<>(region.toArray());
    }

    public ArrayList<Region> getRegion() {
        return region;
    }
}
