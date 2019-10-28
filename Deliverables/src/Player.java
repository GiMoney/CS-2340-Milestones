public class Player {
    private String name;
    private int fighter;
    private int pilot;
    private int merchant;
    private int engineer;
    private String region;
    private Region region1;
    private int money;
    private int x;
    private int y;
    private int banditChance;
    private int policeChance;
    private int traderChance;
    private String difficulty;
    
    public Player(String[] args) {
        name = args[0];
        fighter =  Integer.parseInt(args[3]);
        pilot = Integer.parseInt(args[2]);
        merchant = Integer.parseInt(args[4]);
        engineer = Integer.parseInt(args[5]);
        region = getRegion();
        region1 = null;
        difficulty = args[1];
        if (difficulty.equals("Easy")) {
            money = 1000;
            banditChance = 100; //change back to 10 when not testing
            policeChance = 10;
        } else if (difficulty.equals("Medium")) {
            money = 500;
            banditChance = 20;
            policeChance = 20;
        } else {
            money = 100;
            banditChance = 30;
            policeChance = 30;
        }
        x = 0;
        y = 0;
        traderChance = 20;

    }

    public String getRegion() {
        return region;
    }

    public Region getRegion1() {
        return region1;
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

    public void setRegion1(Region region1) {
        this.region1 = region1;
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

    public int getBanditChance() {
        return banditChance;
    }

    public int getPoliceChance() {
        return policeChance;
    }

    public int getTraderChance() {
        return traderChance;
    }

    public String getDifficulty() {
        return difficulty;
    }
}
