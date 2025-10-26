package storeUtils;

public class Product {
    private int productID, quantity;
    private String productName;
    private double price;
    private Supplier supplier;
    private Inventory inventory;

    /*
     * constructors
     */
    // no parameter
    public Product(){this(0, "", 0, 0, null, null);}
    // all parameter
    public Product(int productID, String productName, int quantity, double price, Supplier supplier, Inventory inventory){
        this.productID = productID;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.supplier = supplier;
        this.inventory = inventory;
    }

    /*
     * setter & getter
     */
    // product id
    public void setProductId(int productID){this.productID = productID;}
    public int getProductId(){return productID;}

    // product name
    public void setProductName(String productName){this.productName = productName;}
    public String getProductName(){return productName;}

    // product quantity
    public void setQuantity(int quantity){this.quantity = quantity;}
    public int getQuantity(){return quantity;}

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
    
}
