public class Region {
    private int x;
    private int y;
    private TechLevel techLevel;
    private String name;

    public Region(int x, int y , TechLevel techlevel, String name){
        this.name = name;
        this.x = x;
        this.y = y;
        this.techLevel = techlevel;
    }
    public int getX(){
        return x;
     }
    public int getY(){
        return y;
    }
    public void setX(int x){
       this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public TechLevel getTechLevel(){
        return techLevel;
    }
    public void setTechLevel(){
        this.techLevel = techLevel;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String toString() {
        return this.name + "(" + this.x + "," + this.y + ")" + this.techLevel ;
    }

}