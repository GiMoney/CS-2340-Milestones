
import java.util.LinkedList;
public class Region {
    private int x;
    private int y;
    private TechLevel techLevel;
    private String name;
    private LinkedList<String> items;

    public Region(int x, int y, TechLevel techlevel, String name) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.techLevel = techlevel;
    }

    public int priceCalculator(int price, Player user) {
        Region currRegion = user.getRegion1();
        double scaleFactor;
        double merchantFactor = (user.getMerchant() > 0) ? 1.0 / user.getMerchant() : 1;
        switch (currRegion.getTechLevel()) {
        case PREAG :
            scaleFactor = 0.1;
            break;

        case AGRICULTURE :
            scaleFactor = 0.2;
            break;

        case MEDIEVAL :
            scaleFactor = 0.3;
            break;

        case RENAISSANCE :
            scaleFactor = 0.4;
            break;

        case INDUSTRIAL :
            scaleFactor = 0.5;
            break;

        case MODERN :
            scaleFactor = 0.6;
            break;

        case FUTURISTIC :
            scaleFactor = 0.7;
            break;

        case DARK :
            scaleFactor = 0.8;
            break;

        case STEAMPUNK :
            scaleFactor = 0.9;
            break;

        default:
            scaleFactor = 1;
        }

        return (int) Math.ceil(price * scaleFactor * merchantFactor);
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

    public TechLevel getTechLevel() {
        return techLevel;
    }

    public void setTechLevel(TechLevel techLevel) {
        this.techLevel = techLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<String> getItems() {
        return items;
    }

    public void setItems(LinkedList<String> items) {
        this.items = items;
    }

    public String toString() {
        return this.name + " (" + this.x + ", " + this.y + ") " + this.techLevel;
    }

}

