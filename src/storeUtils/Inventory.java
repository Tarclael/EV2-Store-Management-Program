package storeUtils;

import java.util.Scanner;
public class Inventory {
    private int inventoryId, capacity;
    private String location;
    private double totalValue;
    private String status;

    private Scanner scanner = new Scanner(System.in);

    private enum Status{
        Understocked, Normal, Full;
    }

    /*
     * constructors
     */
    // no parameter
    public Inventory(){this(0, null, 0, 0, null);}

    // all parameters
    public Inventory(int inventoryId, String location, int capacity, double totalValue, Status status){
        this.inventoryId = inventoryId;
        this.location = location;
        this.capacity = capacity;
        this.totalValue = totalValue;
        this.status = status.name();
    }

    /*
     * setter & getter
     */
    public void setInventoryId(int inventoryId)
    /*
     * methods
     */
    public void showInventoryInfo(){

    }
}
