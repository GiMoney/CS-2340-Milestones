public class Player {
    private String name;
    private int fighter;
    private int pilot;
    private int merchant;
    private int engineer;
    private String region;
    private int money;
    private int x;
    private int y;
    
    public Player() {
        name = null;
        fighter = 0;
        pilot = 0;
        merchant = 0;
        engineer = 0;
        money = 0;
        region = null;
        x = 0;
        y = 0;
    }
    
    public Player(String[] args) {
        name = args[0];
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
        x = 0;
        y = 0;
    }

    public String getRegion() {
        return region;
    }
    
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name; 
    }
    
    public void setRegion(String region) {
        this.region = region; 
    }

    public void setMoney(int money) {
        this.money = money; 
    }
    
    public int getMoney() {
        return money; 
    }

    public void setFighter(int fighter) {
        this.fighter = fighter; 
    }
    
    public int getFighter() {
        return fighter; 
    }

    public void setPilot(int pilot) {
        this.pilot = pilot; 
    }
    
    public int getPilot() {
        return pilot; 
    }

    public void setEngineer(int engineer) {
        this.engineer = engineer; 
    }
    
    public int getEngineer() {
        return engineer; 
    }

    public void setMerchant(int merchant) {
        this.merchant = merchant; 
    }
    
    public int getMerchant() {
        return merchant; 
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

}
