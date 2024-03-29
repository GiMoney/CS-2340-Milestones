public class Player {
    private String name;
    private int fighter;
    private int pilot;
    private int merchant;
    private int engineer;
    private String region;
    private Region region1;
    private Region regionPrev;
    private int money;
    private int x;
    private int y;
    private int karma;
    private int banditChance;
    private int policeChance;
    private int traderChance;
    private double fleeChance;
    private double fightChance;
    private double robChance;
    private double negotiateChance;
    private int repairDiscount;
    private String difficulty;
    private boolean successfulTravel;
    private boolean dialogOpen;
    
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
            banditChance = 15 - karma; //change back to 15 when not testing
            policeChance = 15 - karma;
        } else if (difficulty.equals("Medium")) {
            money = 500;
            banditChance = 25 - karma;
            policeChance = 25 - karma;
        } else {
            money = 100;
            banditChance = 40 - karma;
            policeChance = 40 - karma;
        }
        x = 0;
        y = 0;
        traderChance = 15 + karma;
        fleeChance = (5.625 * pilot) + 5;
        fightChance = (5.625 * fighter) + 5;
        robChance = fightChance;
        negotiateChance = (5.625 * merchant) + 5;
        if (engineer == 0) {
            repairDiscount = 0;
        } else {
            repairDiscount = (5 * engineer) + 10;
        }

    }

    public String getRegion() {
        return region;
    }

    public Region getRegion1() {
        return region1;
    }

    public Region getRegionPrev() {
        return regionPrev;
    }

    public void setRegionPrev(Region regionPrev) {
        this.regionPrev = regionPrev;
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

    public boolean getSuccessfulTravel() {
        return successfulTravel;
    }

    public void setSuccessfulTravel(boolean successfulTravel) {
        this.successfulTravel = successfulTravel;
    }

    public boolean getDialogOpen() {
        return dialogOpen;
    }

    public void setDialogOpen(boolean dialogOpen) {
        this.dialogOpen = dialogOpen;
    }

    public double getFightChance() {
        return fightChance;
    }

    public double getFleeChance() {
        return fleeChance;
    }

    public double getNegotiateChance() {
        return negotiateChance;
    }

    public double getRobChance() {
        return robChance;
    }

    public int getKarma() {
        return karma;
    }

    public void setKarma(int karma) {
        this.karma = karma;
    }

    public int getRepairDiscount() {
        return repairDiscount;
    }
}
