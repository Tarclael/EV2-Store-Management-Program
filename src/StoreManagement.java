import java.io.ObjectInputFilter.Status;
import java.util.ArrayList;
import java.util.Scanner;
import storeUtils.*;

public class StoreManagement {
    private static ArrayList<Product> products = new ArrayList<>();
    private static ArrayList<Inventory> inventories = new ArrayList<>();
    private static ArrayList<Supplier> suppliers = new ArrayList<>();
    private static ArrayList<Employee> employees = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        int option = 0;

        do{
            /*
             * Main Menu
             */
            option = 0;
            System.out.println("\n=== WAREHOUSE MANAGEMENT SYSTEM ===");
            System.out.println("1. Manage Product");
            System.out.println("2. Manage Inventory");
            System.out.println("3. Manage Supplier");
            System.out.println("4. Manage Employee");
            System.out.println("5. Exit");
            option = validMenuOption(1,5);

            /*
            * Submenu
            */
            switch(option){
                case 1:
                    manageProduct();
                    break;
                case 2:
                    manageInventory();
                    break;
                case 3:
                    manageSupplier();
                    break;
                case 4:
                    manageEmployee();
                    break;
                case 5:
                    System.out.println("Program Closed.");
                    break;
            }
        }while(option != 5);
        scanner.close();
    }

    /*
     * Manage Product Menu
     */
    private static void manageProduct(){
        int option = 0;
        do{
            System.out.println("\n=== WAREHOUSE MANAGEMENT SYSTEM ===");
            System.out.println("------ MANAGE PRODUCT MENU --------");
            System.out.println("1. See All Products");
            System.out.println("2. Search by Name");
            System.out.println("3. Add Product");
            System.out.println("4. Modify Product");
            System.out.println("5. Remove Product");
            System.out.println("6. Return to Main Menu");
            option = validMenuOption(1, 6);

            /*
             * Manage product menu
             */
            switch(option){
                case 1: // display all Product objects
                    System.out.println("\n---------- ALL PRODUCT ------------");
                    if(products.size() < 1){ // if Product array is empty
                        System.out.println("No products yet!");
                    }
                    // loop through each products element
                    for(Product product : products){
                        product.showInfo();
                        System.out.println();
                    }
                    scanner.nextLine();
                    System.out.println(products.size() + " product returned.");
                    System.out.print("Press 'Enter' to continue...");
                    scanner.nextLine();
                    break;
                case 2: // search Product object by name
                    System.out.println("\n----- SEARCH PRODUCT by NAME ------");
                    if(products.size() < 1){ // if Product array is empty
                        System.out.println("No products yet!");
                    }else{
                        System.out.print("Enter product name: ");
                        String name = scanner.nextLine();
                        Product productFound = null; // store found Product object
                        // loop through each products element
                        for(Product product : products){
                            // if product name equals entered name
                            if(product.getProductName().equalsIgnoreCase(name)){
                                productFound = product;
                                break;
                            }
                        }
    
                        if(productFound == null){ // if product object not found
                            System.out.println("Product with name " + name + " not found.");
                            System.out.print("Press 'Enter' to continue...");
                            scanner.nextLine();
                        }else{ // if found
                            System.out.println("\n--------- PRODUCT FOUND ---------");
                            productFound.showInfo();
                        }
                    }
                    scanner.nextLine();
                    System.out.print("Press 'Enter' to continue...");
                    scanner.nextLine();
                    break;
                case 3: // add new product object
                    System.out.println("\n--------- ADD NEW PRODUCT ---------");
                    // enter amount of new product
                    int productAmount = validNumberInput(
                        "How many new products? ", 
                        "New product must be at least 1! Please try again!", 
                        "Invalid input! Please try again!\n");

                    for(int i = 0; i < productAmount; i++){
                        Product product = new Product();
                        // set new product ID
                        int productID = validProductId(
                            "Enter new product ID: ", 
                            "ID must be more than 1! Please try again!", 
                            "ID is number only! Please try again!", 
                            "Product ID already exist! Please enter a different one!");
                        product.setProductId(productID);
                        scanner.nextLine();

                        // set new product name
                        System.out.print("Enter new product name: ");
                        product.setProductName(scanner.nextLine());

                        // set new product price
                        double price = validDecimalNumberInput(
                            "Enter new product price: $", 
                            "Price must be at least $1! Please enter a different one!", 
                            "Price is a number only! Please try again!");
                        product.setPrice(price);

                        // set new product qty
                        int qty = validNumberInput(
                            "Enter new product quantity: ", 
                            "Product quantity must be at least 1!", 
                            "Qty is a number only! Please try again!");
                        product.setQuantity(qty);
                        scanner.nextLine();

                        // set new product supplier
                        System.out.print("Enter valid supplier name: ");
                        String supplier = scanner.nextLine();
                        for(Supplier existingSupplier : suppliers){
                            if(existingSupplier.getName().equalsIgnoreCase(supplier)){
                                product.setSupplier(existingSupplier);
                                break;
                            }else{
                                System.out.print("Supplier name not found! Please change supplier name in 'Modify Product' menu!");
                                break;
                            }
                        }

                        // set new product inventory
                        System.out.print("Enter valid inventory location: ");
                        String inventory = scanner.nextLine();

                        // search if inventory exist
                        for(Inventory existingInventory : inventories){
                            if(existingInventory.getInventoryLocation().equalsIgnoreCase(inventory)){
                                product.setInventory(existingInventory);
                                break;
                            }
                        }

                        System.out.println("\n----------- PRODUCT INFO ----------");
                        product.showInfo();
                        products.add(product);
                        System.out.print("Press 'Enter' to continue...");
                        scanner.nextLine();
                        System.out.println();                              
                    }
                    break;
                case 4: // modify product info
                    System.out.println("\n-------- MODIFY PRODUCT INFO ------");
                    System.out.println("1. Change ID");
                    System.out.println("2. Change Name");
                    System.out.println("3. Change Price");
                    System.out.println("4. Change Quantity");
                    System.out.println("5. Change Supplier");
                    System.out.println("6. Change Inventory");
                    System.out.println("7. Back");
                    option = validMenuOption(1, 7);

                    switch(option){
                        case 1: // change product id
                            System.out.println("\n-------- MODIFY PRODUCT INFO ------");
                            System.out.println("\n------------- Change Id -----------");
                            Product productId = findProductById(); // search product by id

                            if(productId != null){ // if product found
                                int newId = validProductId(
                                    "Enter new product ID: ",
                                    "ID must be more than 1! Please try again!",
                                    "ID is number only! Please try again!",
                                    "Product ID already exist! Please enter a different one!"
                                );
                                productId.changeProductID(newId);
                            }
                            break;
                        case 2: // change product name
                            System.out.println("\n-------- MODIFY PRODUCT INFO ------");
                            System.out.println("\n------------ Change Name ----------");
                            Product productName = findProductById();

                            if(productName != null){
                                System.out.print("Enter new product name: ");
                                productName.changeProductName(scanner.nextLine());
                            }
                            break;
                        case 3: // change product price
                            System.out.println("\n-------- MODIFY PRODUCT INFO ------");
                            System.out.println("\n----------- Change Price ----------");
                            Product productPrice = findProductById();

                            if(productPrice != null){
                                double newPrice = validDecimalNumberInput(
                                    "Enter new product price: ",
                                    "Price must be more than $1! Please try again!",
                                    "Price is number only! Please try again!"
                                );
                                productPrice.changeProductPrice(newPrice);
                            }
                                        
                            break;
                        case 4: // change product quantity
                            System.out.println("\n-------- MODIFY PRODUCT INFO ------");
                            System.out.println("\n---------- Change Quantity --------");
                            Product productQuantity = findProductById();

                            if(productQuantity != null){
                                int newQty = validNumberInput(
                                    "Enter new product quantity: ",
                                    "Quantity must be more than 1! Please try again!",
                                    "Quantity is number only! Please try again!"
                                );
                                productQuantity.changeProductQuantity(newQty);
                            }
                            break;
                        case 5: // change product supplier
                            System.out.println("\n-------- MODIFY PRODUCT INFO ------");
                            System.out.println("\n---------- Change Supplier --------");
                            Product productSupplier = findProductById();

                            if(productSupplier != null){
                                System.out.print("Enter valid product supplier name: ");
                                String supplier = scanner.nextLine();
                                            
                                Supplier matchedSupplier = null;
                                for(Supplier existingSupplier : suppliers){
                                    if(existingSupplier.getName().equalsIgnoreCase(supplier)){
                                        matchedSupplier = existingSupplier;
                                        break;
                                    }
                                }

                                if(matchedSupplier != null){
                                    productSupplier.changeProductSupplier(matchedSupplier);
                                    System.out.println("Product supplier successfully updated!");
                                }else{
                                    System.out.println("Supplier not found! Please try again later!");
                                    System.out.print("Press 'Enter' to continue...");
                                    scanner.nextLine();
                                }
                            }
                            break;
                        case 6: // change product inventory
                            System.out.println("\n-------- MODIFY PRODUCT INFO ------");
                            System.out.println("\n--------- Change Inventory --------");
                            Product productInventory = findProductById();

                            if(productInventory != null){
                                System.out.print("Enter valid inventory location: ");
                                String inventoryName = scanner.nextLine();

                                Inventory matchedInventory = null;
                                for(Inventory existingInventory : inventories){
                                    if(existingInventory.getInventoryLocation().equalsIgnoreCase(inventoryName)){
                                        matchedInventory = existingInventory;
                                        break;
                                    }
                                }

                                if(matchedInventory != null){
                                    productInventory.changeProductInventory(matchedInventory);
                                    System.out.println("Product inventory successfully updated!");
                                }else{
                                    System.out.println("Inventory not found! Please try again later!");
                                    System.out.print("Press 'Enter' to continue...");
                                    scanner.nextLine();
                                }
                            }
                            break;
                        case 7:
                            break;
                    }
                    option = 0;
                    break;
                case 5: // remove product object
                    System.out.println("\n--------- REMOVE PRODUCT ---------");
                    Product product = findProductById();

                    if(product != null){
                        System.out.println("\n--------- PRODUCT FOUND ---------");
                        product.showInfo();
                        System.out.print("Remove this product (y/n)? ");
                        String youSureYouWantToRemove = scanner.nextLine();
                        switch(youSureYouWantToRemove){
                            case "y":
                            case "Y":
                                products.remove(product);
                                System.out.println("Product successfully removed!");
                                break;
                            case "n":
                            case "N":
                                System.out.println("Product removal canceled.");
                                break;
                            default:
                                System.out.println("Invalid choice! Please enter 'y' or 'n'.");
                                break;
                        }
                    }
                break;
            }
        }while(option != 6);
    }

    /*
     * Manage Inventory Menu
     */
    private static void manageInventory(){
        int option = 0;
        do{
            System.out.println("\n=== WAREHOUSE MANAGEMENT SYSTEM ===");
            System.out.println("------ MANAGE INVENTORY MENU ------");
            System.out.println("1. See All Inventories");
            System.out.println("2. See All Inventory products");
            System.out.println("3. Search by ID");
            System.out.println("4. Add Inventory");
            System.out.println("5. Modify Inventory");
            System.out.println("6. Remove Inventory");
            System.out.println("7. Return to Main Menu");
            option = validMenuOption(1, 7);

            switch(option){
                case 1:
                    System.out.println("\n--------- ALL INVENTORY -----------");
                    if(inventories.size() < 1){ // if inventory array is empty
                        System.out.println("No inventory yet!");
                    }
                    // loop through each inventories element
                    for(Inventory inventory : inventories){
                        inventory.showInfo();
                        System.out.println();
                    }
                    scanner.nextLine();
                    System.out.println(inventories.size() + " inventory returned.");
                    System.out.print("Press 'Enter' to continue...");
                    scanner.nextLine();
                    break;
                case 2:
                    System.out.println("\n----- ALL INVENTORY PRODUCTS ------");
                    if(inventories.size() < 1){ // if inventory array is empty
                        System.out.println("No inventory yet!");
                    }

                    for(Inventory inventory : inventories){
                        System.out.println(inventory.toString());
                        inventory.showAllInventoryProduct();
                        System.out.println();
                    }
                    System.out.println(inventories.size() + " inventory returned.");
                    System.out.print("Press 'Enter' to continue...");
                    scanner.nextLine();
                    break;
                case 3:
                    System.out.println("\n----- SEARCH INVENTORY by ID ------");
                    if(inventories.size() < 1){ // if Inventory array is empty
                        System.out.println("No products yet!");
                    }else{
                        Inventory inventoryFound = findInventoryById();
    
                        if(inventoryFound == null){ // if product object not found
                            System.out.println("Inventory not found!");
                            System.out.print("Press 'Enter' to continue...");
                            scanner.nextLine();
                        }else{ // if found
                            System.out.println("\n-------- INVENTORY FOUND --------");
                            inventoryFound.showInfo();
                        }
                    }
                    scanner.nextLine();
                    System.out.print("Press 'Enter' to continue...");
                    scanner.nextLine();
                    break;
                case 4:
                    System.out.println("\n-------- ADD NEW INVENTORY --------");
                    // enter amount of new inventory
                    int amount = validNumberInput(
                        "How many new inventories? ", 
                        "New inventory must be at least 1! Please try again!", 
                        "Invalid input! Please try again!\n");

                    for(int i = 0; i < amount; i++){
                        Inventory inventory = new Inventory();
                        // set new inventory ID
                        int id = validInventoryId(
                            "Enter new inventory ID: ", 
                            "ID must be more than 1! Please try again!", 
                            "ID is number only! Please try again!", 
                            "Inventory ID already exist! Please enter a different one!");
                        inventory.setInventoryId(id);
                        scanner.nextLine();

                        // set new inventory location
                        System.out.print("Enter new inventory location: ");
                        inventory.setInventoryLocation(scanner.nextLine());

                        // set new inventory capacity
                        int capacity = validNumberInput(
                            "Enter new inventory capacity: ", 
                            "Capacity must be at least 1! Please enter a different one!", 
                            "Capacity is a number only! Please try again!");
                        inventory.setInventoryCapacity(capacity);

                        // set new inventory status
                        System.out.print("Set new inventory status: ");
                        String status = scanner.nextLine();
                        switch(status){
                            case "Understocked":
                            case "understocked":
                                inventory.setInventoryStatus(storeUtils.Inventory.Status.Understocked);
                                break;
                            case "Normal":
                            case "normal":
                                inventory.setInventoryStatus(storeUtils.Inventory.Status.Normal);
                                break;
                            case "Full":
                            case "full":
                                inventory.setInventoryStatus(storeUtils.Inventory.Status.Full);
                                break;
                            default:    
                                System.out.println("Invalid status!\nPlease change it in 'Modify Inventory'!");
                                scanner.nextLine();
                                break;
                        }

                        System.out.println("\n---------- INVENTORY INFO ---------");
                        inventory.showInfo();
                        inventories.add(inventory);
                        System.out.print("Press 'Enter' to continue...");
                        scanner.nextLine();
                        System.out.println();                              
                    }
                    break;
                case 5:
                    System.out.println("\n------- MODIFY INVENTORY INFO -----");
                    System.out.println("1. Change ID");
                    System.out.println("2. Change Location");
                    System.out.println("3. Change Capacity");
                    System.out.println("4. Change Status");
                    System.out.println("5. Back");
                    option = validMenuOption(1, 5);

                    switch(option){
                        case 1:
                            System.out.println("\n------------- Change Id -----------");
                            Inventory inventoryId = findInventoryById(); // search inventory by id

                            if(inventoryId != null){ // if inventory found
                                int newId = validInventoryId(
                                    "Enter new inventory ID: ",
                                    "ID must be more than 1! Please try again!",
                                    "ID is number only! Please try again!",
                                    "Inventory ID already exist! Please enter a different one!"
                                );
                                inventoryId.setInventoryId(newId);
                            }
                            break;
                        case 2:
                            System.out.println("\n---------- Change Location --------");
                            Inventory inventoryLocation = findInventoryById();

                            if(inventoryLocation != null){
                                System.out.print("Enter new location: ");
                                inventoryLocation.setInventoryLocation(scanner.nextLine());
                            }
                            break;
                        case 3:
                            System.out.println("\n---------- Change Capacity --------");
                            Inventory inventoryCapacity = findInventoryById();

                            if(inventoryCapacity != null){
                                int capacity = validNumberInput(
                                    "Enter new inventory capacity: ", 
                                    "Capacity must be at least 1! Please enter a different one!", 
                                    "Capacity is a number only! Please try again!");
                                inventoryCapacity.setInventoryCapacity(capacity);
                            }
                            break;
                        case 4:
                            System.out.println("\n----------- Change Status ---------");
                            Inventory inventoryStatus = findInventoryById();

                            System.out.print("Change inventory status: ");
                            String status = scanner.nextLine();
                            switch(status){
                                case "Understocked":
                                case "understocked":
                                    inventoryStatus.setInventoryStatus(storeUtils.Inventory.Status.Understocked);
                                    break;
                                case "Normal":
                                case "normal":
                                    inventoryStatus.setInventoryStatus(storeUtils.Inventory.Status.Normal);
                                    break;
                                case "Full":
                                case "full":
                                    inventoryStatus.setInventoryStatus(storeUtils.Inventory.Status.Full);
                                    break;
                                default:    
                                    System.out.println("Invalid status!\nPlease try again later!");
                                    scanner.nextLine();
                                    break;
                            }
                            break;
                        case 5:
                            break;
                    }
                    break;
                case 6:
                    break;
                case 7:
                    break;
            }
        }while(option != 7);
    }

    /*
     * Manage Supplier Menu
     */
    private static void manageSupplier(){
        int option = 0;
    }

    /*
     * Manage Employee Menu
     */
    private static void manageEmployee(){
        int option = 0;
    }

    private static int validMenuOption(int lowestRange, int highestRange){
        int option = 0;
        boolean isValid;
        do{
            isValid = true;
            try{
                System.out.print("Choose an option: ");
                option = scanner.nextInt();
                if(option < lowestRange || option > highestRange){
                    scanner.nextLine();
                    System.out.println("Option out of range. Please try again!\n");
                    isValid = false;
                }
            }catch(Exception e){
                scanner.nextLine();
                System.out.println("Invalid input! Please try again!\n");
                isValid = false;
            }
        }while(!isValid);
        return option;
    }

    private static int validNumberInput(String question, String error1, String error2){
        int num = 0;
        boolean isValid;
        do{
            isValid = true;
            try{
                System.out.print(question);
                num = scanner.nextInt();
                if(num < 1){
                    scanner.nextLine();
                    System.out.println(error1);
                    isValid = false;
                }
            }catch(Exception e){
                scanner.nextLine();
                System.out.println(error2);
                isValid = false;
            }
        }while(!isValid);
        return num;
    }
    
    private static int validProductId(String question, String error1, String error2, String isExistResponse){
        int num = 0;
        boolean isValid;
        do{
            isValid = true;
            try{
                System.out.print(question);
                num = scanner.nextInt();
                if(num < 1){
                    scanner.nextLine();
                    System.out.println(error1);
                    isValid = false;
                }

                for(Product existingProduct : products){
                    if(existingProduct.getProductId() == num){
                        System.out.println(isExistResponse);
                        isValid = false;
                        break;
                    }
                }
            }catch(Exception e){
                scanner.nextLine();
                System.out.println(error2);
                isValid = false;
            }
        }while(!isValid);
        return num;
    }

    private static int validInventoryId(String question, String error1, String error2, String isExistResponse){
        int num = 0;
        boolean isValid;
        do{
            isValid = true;
            try{
                System.out.print(question);
                num = scanner.nextInt();
                if(num < 1){
                    scanner.nextLine();
                    System.out.println(error1);
                    isValid = false;
                }

                for(Inventory existingInventory : inventories){
                    if(existingInventory.getInventoryId() == num){
                        System.out.println(isExistResponse);
                        isValid = false;
                        break;
                    }
                }
            }catch(Exception e){
                scanner.nextLine();
                System.out.println(error2);
                isValid = false;
            }
        }while(!isValid);
        return num;
    }

    private static double validDecimalNumberInput(String question, String error1, String error2){
        double num = 0;
        boolean isValid;
        do{
            isValid = true;
            try{
                System.out.print(question);
                num = scanner.nextInt();
                if(num < 1){
                    scanner.nextLine();
                    System.out.println(error1);
                    isValid = false;
                }
            }catch(Exception e){
                scanner.nextLine();
                System.out.println(error2);
                isValid = false;
            }
        }while(!isValid);
        return num;
    }

    private static Product findProductById() {
        int productID;
        try {
            productID = validNumberInput(
                "Enter product ID: ",
                "ID must be more than 1! Please try again!",
                "ID is number only! Please try again!"
            );
        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("Invalid input! Please try again later!\n");
            System.out.print("Press 'Enter' to continue...");
            scanner.nextLine();
            return null;
        }

        for (Product existingProduct : products) {
            if (existingProduct.getProductId() == productID) {
                return existingProduct;
            }
        }

        System.out.println("Product not found!");
        System.out.print("Press 'Enter' to continue...");
        scanner.nextLine();
        return null;
    }

    private static Inventory findInventoryById() {
        int inventoryID;
        try {
            inventoryID = validNumberInput(
                "Enter inventory ID: ",
                "ID must be more than 1! Please try again!",
                "ID is number only! Please try again!"
            );
        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("Invalid input! Please try again later!\n");
            System.out.print("Press 'Enter' to continue...");
            scanner.nextLine();
            return null;
        }

        for (Inventory existingInventory : inventories) {
            if (existingInventory.getInventoryId() == inventoryID) {
                return existingInventory;
            }
        }

        System.out.println("Inventory not found not found!");
        System.out.print("Press 'Enter' to continue...");
        scanner.nextLine();
        return null;
    }

}
