import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Universe extends Game {

    public Universe(String[] name){
        ArrayList duplic = new ArrayList(10);
        ArrayList cord = new ArrayList(10);
        int i = 0;
        while(i < 10){
            String names = name[(int)(Math.random() * 10)];
            if(!duplic.contains(names)){
                System.out.println("ENTER");
                duplic.add(names);
                i++;
            }
          //  System.out.println(i);
        }
        //System.out.println(duplic.size());
        //for(int j =0; j < duplic.size();j++){
          //  System.out.println(duplic.get(j).toString());
       // }
       // System.out.println(duplic);
        player.setRegion(duplic.get(0).toString());
       // System.out.println(player.getRegion());
        //duplic.remove(0);


        //System.out.println((String) duplic.get(1));

//        HashMap<String, String> loc = new HashMap<String, String>();
//        while (i < 10) {
//            loc.put(duplic.get(i).toString(), "16");
//            i++;
//        }
        Object[] locat = duplic.toArray();
        super.RegionList = new JComboBox(locat);




    }
}
