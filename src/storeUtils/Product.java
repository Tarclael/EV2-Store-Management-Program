package storeUtils;

import java.util.Scanner;

public class Product {
    private int productId, qty;
    private String productName;
    private double price;
    private Supplier supplier;
    private Inventory inventory;

    private Scanner scanner = new Scanner(System.in);

    /*
     * constructors
     */
    // no parameter
    public Product(){this(0, null, 0, 0, null, null);}
    // all parameter
    public Product(int productId, String productName, int quantity, double price, Supplier supplier, Inventory inventory){
        this.productId = productId;
        this.productName = productName;
        this.qty = quantity;
        this.price = price;
        this.supplier = supplier;
        this.inventory = inventory;
    }

    public Product(String name){this.productName = name;}

    /*
     * setter & getter
     */
    // product id
    public void setProductId(int productId){this.productId = productId;}
    public int getProductId(){return productId;}

    // product name
    public void setProductName(String productName){this.productName = productName;}
    public String getProductName(){return productName;}

    // product quantity
    public void setQuantity(int quantity){this.qty = quantity;}
    public int getQuantity(){return qty;}

    // product price
    public void setPrice(double price){this.price = price;}
    public double getPrice(){return price;}

    // product supplier
    public void setSupplier(Supplier supplier){this.supplier = supplier;}
    public Supplier getSupplier(){return supplier;}

    // product inventory location
    public void setInventory(Inventory inventory){this.inventory = inventory;}
    public Inventory getInventory(){return inventory;}

    /*
     * methods
     */
    public void showInfo() {
        System.out.println("ID                 : " + getProductId());
        System.out.println("Name               : " + getProductName());
        System.out.println("Quantity           : " + getQuantity());
        System.out.println("Price              : $" + getPrice());

        if(inventory != null){
            System.out.println("Inventory location : " + inventory.getInventoryLocation());
        }else{
            System.out.println("Inventory location : Not exist/set yet!");
        }

        if(supplier != null){
            System.out.println("Supplier           : " + supplier.getName());
        }else{
            System.out.println("Supplier           : Not exist/set yet!");
        }
    }
    
    public void changeProductName(String newName){
        System.out.println("Product name has been changed to " + newName);
        this.productName = newName;
        System.out.print("Press 'Enter' to continue...");
        scanner.nextLine();
    }
    
    public void changeProductQuantity(int newQty){
        System.out.println("Product quantity has been changed to " + newQty);
        this.qty = newQty;
        System.out.print("Press 'Enter' to continue...");
        scanner.nextLine();
    }
    
    public void changeProductPrice(double newPrice){
        System.out.println("Product price has been changed to $" + newPrice);
        this.price = newPrice;
        System.out.print("Press 'Enter' to continue...");
        scanner.nextLine();
    }
    
    public void changeProductInventory(Inventory newInventory){
        System.out.println("Product inventory location has been changed to ID " + newInventory.getInventoryId());
        this.inventory = newInventory;
    }

    public void changeProductSupplier(Supplier newSupplier){
        System.out.println("Product supplier has been changed to ID " + newSupplier.getSupplierId());
        this.supplier = newSupplier;
        System.out.print("Press 'Enter' to continue...");
        scanner.nextLine();
    }

    @Override
    public String toString(){
        return "Product[" + productId + ", " +  productName + ", $" + price + ", " + ", quantity = " + qty + ", supplier = " + supplier.getName() + "inventory = " + inventory.getInventoryId() + "]";
    }
}