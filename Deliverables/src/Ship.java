import java.util.*;

public class Ship {
    private List<String> shipType;
    private int cargoSpace;
    private int fuelCapacity;
    private int health;

    public Ship() {
        shipType = new ArrayList<>();
        shipType.add("Fighter Jet");
        cargoSpace = 17;
        fuelCapacity = 100;
        health = 100;
    }

    public String getShipType() {
        return shipType.get(0);
    }

    public int getCargoSpace() {
        return cargoSpace;
    }

    public void setCargoSpace(int cargoSpace) {
        this.cargoSpace = cargoSpace;
    }

    public int getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(int x) {
        this.fuelCapacity = x;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}