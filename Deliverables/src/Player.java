import java.util.ArrayList;

public class Player {
    private String name; // object of T
    private int fighter; // next of Node<T>
    private int pilot;
    private int merchant;
    private int engineer;
    private String region;
    private int money;
    /**
     * This is my Node construtor
     */
    public Player(){
         name = null;
         fighter = 0;
         pilot = 0;
         merchant = 0;
         engineer = 0;
         money = 0;
         region = null;
    }
    /**
     *Node constructor that take the Type T paramater
     *@param p type of T
     */
    public Player(String[] args){
        name = args[0]; // object becomes p
        fighter =  Integer.parseInt(args[3]);
        pilot = Integer.parseInt(args[2]);
        merchant = Integer.parseInt(args[4]);
        engineer = Integer.parseInt(args[5]);
        region = getRegion();
        if (args[1].equals("Easy")) {
            money = 1000;
        } else if (args[1].equals("Medium")) {
            money = 500;
        } else {
            money = 100;
        }
    }

    /**
     *Gets the next in the node
     *@return {@code Node<T>} the next node
     */
    public String getRegion(){
        return region;  // returns the next node
    }

    /**
     *Gets the Object at that Node
     *@return T type of T
     */
    public String getName(){
        return this.name; // returns the object
    }

    /**
     *Sets the object to o at that node
     *@param o type of T
     */
    public void setName(String name){
        this.name = name; // sets the object to o
    }

    /**
     *Sets the Next node to that object
     *@param o type of {@code Node<T>}
     */
    public void setRegion(String region){
        this.region = region; // sets next to Node<T> o
    }


    public void setMoney(int Money){
        this.money = money; // sets the object to o
    }

    /**
     *Sets the Next node to that object
     *@param o type of {@code Node<T>}
     */
    public int getMoney(){
        return money; // sets next to Node<T> o
    }

    public void setFighter(int fighter){
        this.fighter = fighter; // sets the object to o
    }

    /**
     *Sets the Next node to that object
     *@param o type of {@code Node<T>}
     */
    public int getFighter(){
        return fighter; // sets next to Node<T> o
    }

    public void setPilot(int pilot){
        this.pilot = pilot; // sets the object to o
    }

    /**
     *Sets the Next node to that object
     *@param o type of {@code Node<T>}
     */
    public int getPilot(){
        return pilot; // sets next to Node<T> o
    }


    public void setEngineer(int engineer){
        this.engineer = engineer; // sets the object to o
    }

    /**
     *Sets the Next node to that object
     *@param o type of {@code Node<T>}
     */
    public int getEngineer(){
        return engineer; // sets next to Node<T> o
    }

    public void setMerchant(int merchant){
        this.merchant = merchant; // sets the object to o
    }

    /**
     *Sets the Next node to that object
     *@param o type of {@code Node<T>}
     */
    public int getMerchant(){
        return merchant; // sets next to Node<T> o
    }

}
