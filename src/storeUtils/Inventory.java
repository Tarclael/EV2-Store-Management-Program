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

    public enum Status{
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
    public void showInfo(){
        System.out.println("ID" + getInventoryId());
        System.out.println("location" + getInventoryLocation());
        System.out.println("Current capacity" + currentCapacity + " of " + maxCapacity);
        System.out.println("Total value" + getTotalValue());
    }
    
    public void showAllInventoryProduct(){
        for(Product product : products){
            product.showInfo();
            System.out.println();
        }
        System.out.println("Status" + getInventoryStatus());
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
                System.out.print("Press 'Enter' to continue...");
                scanner.nextLine();
                break;
            }
        }
    }
    
    public void addProduct(Product product){
        products.add(product);
        updateTotalValue();
        System.out.println("Product added to inventory " + getInventoryId());
        System.out.print("Press 'Enter' to continue...");
        scanner.nextLine();
    }
    
    public void removeProduct(Product product){
        products.remove(product);
        updateTotalValue();
        System.out.println("Product removed from inventory " + getInventoryId());
        System.out.print("Press 'Enter' to continue...");
        scanner.nextLine();
    }

    @Override
    public String toString(){
        return "Inventory[" + inventoryId + ", " +  location + ", max=" + maxCapacity + ", current=" + currentCapacity + ", $" + totalValue + "]";
    }
}
