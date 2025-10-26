package storeUtils;

import java.util.ArrayList;
import java.util.Scanner;
public class Inventory {
    private int inventoryId, maxCapacity, currentCapacity;
    private String location;
    private double totalValue;
    private String status;

    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Product> products;

    private enum Status{
        Understocked, Normal, Full;
    }

    /*
     * constructors
     */
    // no parameter
    public Inventory(){this(0, null, 0, null);}

    // all parameters
    public Inventory(int inventoryId, String location, int maxCapacity, Status status){
        this.inventoryId = inventoryId;
        this.location = location;
        this.maxCapacity = maxCapacity;
        this.status = status.name();
        this.products = new ArrayList<>();
    }

    /*
     * setter & getter
     */
    // inventory id
    public void setInventoryId(int inventoryId){this.inventoryId = inventoryId;}
    public int getInventoryId(){return inventoryId;}

    // inventory location
    public void setInventoryLocation(String location){this.location = location;}
    public String getInventoryLocation(){return location;}

    // inventory capacity
    public void setInventoryCapacity(int capacity){this.maxCapacity = capacity;}
    public int getInventoryCapacity(){return maxCapacity;}

    // inventory status
    public void setInventoryStatus(Status status){this.status = status.name();}
    public String getInventoryStatus(){return status;}    

    public double getTotalValue(){
        updateTotalValue();
        return totalValue;
    }

    /*
     * methods
     */
    public void showInventoryInfo(){
        System.out.println();
    }

    public void updateTotalValue(){
        currentCapacity = 0;
        totalValue = 0;

        for(Product product : products){
            if(currentCapacity + product.getQuantity() <= maxCapacity){
                currentCapacity += product.getQuantity();
                totalValue += product.getQuantity() * product.getPrice();
            }else{
                setInventoryStatus(Status.Full);
                System.out.println("Inventory " + getInventoryId() + " is full.");
                break;
            }
        }
    }

    public void addProduct(Product product){
        products.add(product);
        updateTotalValue();
    }

    public void removeProduct(Product product){
        products.add(product);
        updateTotalValue();
    }
}
