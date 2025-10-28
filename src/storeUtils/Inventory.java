package storeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Inventory {
    private int inventoryId, currentCapacity;
    private String location;
    private double totalValue;
    private String status;

    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Product> products;

    public enum Status{
        Understocked, Normal, UnderMaintenance;
    }

    /*
     * constructors
     */
    // no parameter
    public Inventory(){this(0, null);}

    // all parameters
    public Inventory(int inventoryId, String location){
        this.inventoryId = inventoryId;
        this.location = location;
        this.products = new ArrayList<>();
        setInventoryStatus();
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


    // inventory status
    public void setInventoryStatus(){
        if(currentCapacity <= 10){
            this.status = Status.Understocked.toString();
        }else{
            this.status = Status.Normal.toString();
        }
    }
    
    public void setInventoryStatus(int status){
        switch(status){
            case 1:
                this.status = Status.Normal.name();
                System.out.println("Status changed to normal.");
                break;
            case 2:
                this.status = Status.Understocked.name();
                System.out.println("Status changed to understocked.");
                break;
            case 3:
                this.status = Status.UnderMaintenance.name();
                System.out.println("Status changed to under maintenance.");
                break;
        }
        System.out.print("Press 'Enter' to continue...");
        scanner.nextLine();
    }

    public void setInventoryStatus(Status status){this.status = status.name();}
    public String getInventoryStatus(){return status.toString();}    

    public void updateCurrentCapacity(int addedCapacity){
        currentCapacity += addedCapacity;
    }
    public double getTotalValue(){
        return totalValue;
    }
    public int getCurrentCapacity(){
        return currentCapacity;
    }

    /*
     * methods
     */
    public void showInfo(){
        System.out.println("ID               : " + getInventoryId());
        System.out.println("Location         : " + getInventoryLocation());
        System.out.println("Current capacity : " + getCurrentCapacity());
        System.out.println("Total value      : $" + getTotalValue());
        System.out.println("Status           : " + getInventoryStatus());
    }
    
    public void showAllInventoryProduct(){
        if(products.size() >= 1){
            for(Product product : products){
                product.showInfo();
                System.out.println();
            }
        }else{
            System.out.println("No product in this inventory!");
        }
        try {
            System.out.println("Status = " + getInventoryStatus());
        } catch (Exception e) {
            System.out.println("Inventory status not set yet!");
            System.out.print("Press 'Enter' to continue...");
            scanner.nextLine();
        }
    }
    
    public void updateTotalValue(){
        currentCapacity = 0;
        totalValue = 0;

        for(Product product : products){
            currentCapacity += product.getQuantity();
            totalValue += (double)product.getQuantity() * product.getPrice();
            
        }
        setInventoryStatus();
    }
    
    public void addProduct(Product product){
        products.add(product);
        updateTotalValue();
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
        return "Inventory[" + getInventoryId() + ", " +  getInventoryLocation() + ", current capacity = " + getCurrentCapacity() + ", total value = $" + getTotalValue() + "]";
    }
}
