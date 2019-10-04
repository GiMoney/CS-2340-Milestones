import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.util.Collections;
import java.awt.Rectangle;


public class Universe extends Game {

    public Universe(String[] name){
        List<String> duplic = new ArrayList<>(10);
        ArrayList<TechLevel> tech = new ArrayList(10);
        ArrayList region = new ArrayList(10);

        /*while(i < 10){
            String names = name[(int)(Math.random() * 10)];
            if(!duplic.contains(names)){
                duplic.add(names);
                i++;
            }
          //  System.out.println(i);
        }

        */
        duplic = Arrays.asList(name);
        Collections.shuffle(duplic);
        Set<Integer> setX = new HashSet<Integer>();
        Set<Integer> setY = new HashSet<Integer>();

        Random r = new Random();
        while(setX.size() < 10 || setY.size() < 10) {
            int x = r.nextInt((20) + 1) - 0;
            int y = r.nextInt((20) + 1) - 0;
            setX.add(x);
            setY.add(y);
            System.out.print(setX);
            System.out.print(setY);
            System.out.println("SIZEX:" + setX.size());
            System.out.println("SIZEY:" + setY.size());
        }

        Iterator itr = setX.iterator();
        Iterator itr2 = setY.iterator();
        int g = 0;
        int p = 0;

        while(p < 10) {
            Random random = new Random();
            TechLevel randomX = TechLevel.values()[random.nextInt(TechLevel.values().length)];
            if (!tech.contains(randomX)) {
                tech.add(randomX);
                p++;
            }
        }
        while(itr.hasNext() && itr2.hasNext()) {
            region.add(new Region((Integer)itr.next() * 10, (Integer) itr2.next() * 10, tech.get(g), name[g]));
            //System.out.print(region.get(g).getX()); BRIAN ASKF OR HELP HERREEEEEEEEE
            g++;
        }


        //System.out.println(duplic.size());
        //for(int j =0; j < duplic.size();j++){
          //  System.out.println(duplic.get(j).toString());
       // }
       // System.out.println(duplic);
        player.setRegion(name[0].toString());
       // System.out.println(player.getRegion());
        //duplic.remove(0);


        //System.out.println((String) duplic.get(1));

//        HashMap<String, String> loc = new HashMap<String, String>();
//        while (i < 10) {
//            loc.put(duplic.get(i).toString(), "16");
//            i++;
//        }
        //Object[] locat = name;
        super.RegionList = new JComboBox(region.toArray());




    }

}
