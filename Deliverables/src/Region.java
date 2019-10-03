
public class Region {
    private int x;
    private int y;
    private TechLevel techLevel;
    private String name;

    public void Region(int x, int y , TechLevel techlevel, String name){
        this.name = name;
        this.x = x;
        this.y = y;
        this.techLevel = techlevel;
    }

}