import java.util.Random;
import java.util.ArrayList;
import javax.swing.*;
import java.util.*;
import java.util.List;
import java.util.Collections;

public class Universe extends Game {
    protected ArrayList<Region> region = new ArrayList<>(10);

    public Universe(String[] name) {
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
        Game.region = region;
        Game.regionList = new JComboBox<>(region.toArray());


    }
}
