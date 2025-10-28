
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Function;

import storeUtils.*;

public class StoreManagement {
    private static ArrayList<Product> products = new ArrayList<>();
    private static ArrayList<Inventory> inventories = new ArrayList<>();
    private static ArrayList<Supplier> suppliers = new ArrayList<>();
    private static ArrayList<Employee> employees = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        int option = 0;
        Inventory inventory = new Inventory(1, "east");
        inventories.add(inventory);
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
            System.out.println("2. Search by ID");
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
                    System.out.println(products.size() + " product/s returned.");
                    System.out.print("Press 'Enter' to continue...");
                    scanner.nextLine();
                    break;
                case 2: // search Product object by name
                    System.out.println("\n------ SEARCH PRODUCT by ID -------");
                    if(products.size() < 1){
                        System.out.println("No product yet!");
                        System.out.print("Press 'Enter' to continue...");
                        scanner.nextLine();
                        scanner.nextLine();
                        break;
                    }
                    
                    Product productFound = findProductById();
                    scanner.nextLine();
                    if(productFound != null){
                        System.out.println("\n--------- PRODUCT FOUND ---------");
                        productFound.showInfo();
                    }
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
                        int productID = validId(
                            "Enter new product ID: ", 
                            "ID must be more than 1! Please try again!", 
                            "ID is number only! Please try again!", 
                            "Product ID already exist! Please enter a different one!", 
                            products, Product::getProductId);
                        
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
                            "Qty is a whole number only! Please try again!");
                        product.setQuantity(qty);
                        scanner.nextLine();

                        // set new product supplier
                        Supplier supplier = findSupplierById();
                        scanner.nextLine();
                        if(supplier != null){
                            product.setSupplier(supplier);
                        }

                        // set new product inventory
                        Inventory inventory = findInventoryById();
                        scanner.nextLine();
                        
                        // search if inventory exist
                        if(inventory != null){
                            product.setInventory(inventory);
                            inventory.updateCurrentCapacity(qty);
                        }
                        
                        products.add(product);
                        System.out.println("\n----------- PRODUCT INFO ----------");
                        product.showInfo();
                        System.out.print("Press 'Enter' to continue...");
                        scanner.nextLine();
                        System.out.println();                              
                    }
                    break;
                case 4: // modify product info
                    System.out.println("\n-------- MODIFY PRODUCT INFO ------");
                    System.out.println("1. Change Name");
                    System.out.println("2. Change Price");
                    System.out.println("3. Change Quantity");
                    System.out.println("4. Change Supplier");
                    System.out.println("5. Change Inventory");
                    System.out.println("6. Back");
                    option = validMenuOption(1, 6);

                    switch(option){
                        case 1: // change product name
                            System.out.println("\n-------- MODIFY PRODUCT INFO ------");
                            System.out.println("\n------------ Change Name ----------");
                            Product productName = findProductById();
                            scanner.nextLine();
                            if(productName != null){
                                productName.showInfo();
                                System.out.print("Enter new product name: ");
                                productName.changeProductName(scanner.nextLine());
                            }
                            break;
                        case 2: // change product price
                            System.out.println("\n-------- MODIFY PRODUCT INFO ------");
                            System.out.println("\n----------- Change Price ----------");
                            Product productPrice = findProductById();
                            scanner.nextLine();
                            if(productPrice != null){
                                productPrice.showInfo();
                                double newPrice = validDecimalNumberInput(
                                    "Enter new product price: $",
                                    "Price must be more than $1! Please try again!",
                                    "Price is number only! Please try again!"
                                );
                                productPrice.changeProductPrice(newPrice);
                            }
                                        
                            break;
                        case 3: // change product quantity
                            System.out.println("\n-------- MODIFY PRODUCT INFO ------");
                            System.out.println("\n---------- Change Quantity --------");
                            Product productQuantity = findProductById();
                            scanner.nextLine();
                            if(productQuantity != null){
                                productQuantity.showInfo();
                                int newQty = validNumberInput(
                                    "Enter new product quantity: ",
                                    "Quantity must be more than 1! Please try again!",
                                    "Quantity is number only! Please try again!"
                                );
                                productQuantity.changeProductQuantity(newQty);
                            }
                            break;
                        case 4: // change product supplier
                            System.out.println("\n-------- MODIFY PRODUCT INFO ------");
                            System.out.println("\n---------- Change Supplier --------");
                            Product productSupplier = findProductById();
                            scanner.nextLine();
                            if(productSupplier != null){
                                productSupplier.showInfo();
                                Supplier supplierFound = findSupplierById();
                                scanner.nextLine();
                                if(supplierFound != null){
                                    productSupplier.changeProductSupplier(supplierFound);
                                }
                            }
                            break;
                        case 5: // change product inventory
                            System.out.println("\n-------- MODIFY PRODUCT INFO ------");
                            System.out.println("\n--------- Change Inventory --------");
                            Product productInventory = findProductById();
                            scanner.nextLine();
                            if(productInventory != null){
                                productInventory.showInfo();
                                Inventory inventoryFound = findInventoryById();
                                scanner.nextLine();
                                if(inventoryFound != null){
                                    productInventory.changeProductInventory(inventoryFound);
                                }
                            }
                            break;
                        case 6:
                            break;
                    }
                    option = 0;
                    break;
                case 5: // remove product object
                    System.out.println("\n--------- REMOVE PRODUCT ---------");
                    Product product = findProductById();
                    scanner.nextLine();
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
            System.out.println("2. See All Inventory Products");
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
                    System.out.println(inventories.size() + " inventory/s returned.");
                    System.out.print("Press 'Enter' to continue...");
                    scanner.nextLine();
                    break;
                case 2:
                    System.out.println("\n----- ALL INVENTORY PRODUCTS ------");
                    if(inventories.size() < 1){ // if inventory array is empty
                        System.out.println("No inventory yet!");
                        System.out.print("Press 'Enter' to continue...");
                        scanner.nextLine();
                        break;
                    }

                    for(Inventory inventory : inventories){
                        System.out.println(inventory.toString());
                        inventory.showAllInventoryProduct();
                        System.out.println();
                    }
                    System.out.println(inventories.size() + " inventory returned.");
                    scanner.nextLine();
                    System.out.print("Press 'Enter' to continue...");
                    scanner.nextLine();
                    break;
                case 3:
                    System.out.println("\n----- SEARCH INVENTORY by ID ------");
                    if(inventories.size() < 1){ // if inventory array is empty
                        System.out.println("No inventory yet!");
                        System.out.print("Press 'Enter' to continue...");
                        scanner.nextLine();
                        break;
                    }

                    Inventory inventoryFound = findInventoryById();
                    scanner.nextLine();
                    if(inventoryFound != null){
                        System.out.println("\n-------- INVENTORY FOUND --------");
                        inventoryFound.showInfo();
                        System.out.print("Press 'Enter' to continue...");
                        scanner.nextLine();
                    }
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
                        int id = validId(
                            "Enter new inventory ID: ", 
                            "ID must be more than 1! Please try again!", 
                            "ID is number only! Please try again!", 
                            "Inventory ID already exist! Please enter a different one!",
                            inventories, Inventory::getInventoryId);
                        inventory.setInventoryId(id);
                        scanner.nextLine();

                        // set new inventory location
                        System.out.print("Enter new inventory location: ");
                        inventory.setInventoryLocation(scanner.nextLine());

                        inventories.add(inventory);
                        System.out.println("\n---------- INVENTORY INFO ---------");
                        inventory.showInfo();
                        System.out.print("Press 'Enter' to continue...");
                        scanner.nextLine();
                        System.out.println();                              
                    }
                    break;
                case 5:
                    System.out.println("\n------- MODIFY INVENTORY INFO -----");
                    System.out.println("1. Change Location");
                    System.out.println("2. Change Status");
                    System.out.println("3. Back");
                    option = validMenuOption(1, 3);

                    switch(option){
                        case 1:
                            System.out.println("\n---------- Change Location --------");
                            Inventory inventoryLocation = findInventoryById();
                            scanner.nextLine();
                            if(inventoryLocation != null){
                                System.out.print("Enter new location: ");
                                inventoryLocation.setInventoryLocation(scanner.nextLine());
                            }
                            break;
                        case 2:
                            System.out.println("\n----------- Change Status ---------");
                            Inventory inventoryStatus = findInventoryById();
                            scanner.nextLine();
                            System.out.println("Change inventory status to");
                            System.out.println("1. Normal");
                            System.out.println("2. Understocked");
                            System.out.println("3. Under Maintenance");
                            option = validMenuOption(1, 3);
                            inventoryStatus.setInventoryStatus(option);
                            break;
                        case 3:
                            break;
                    }
                    break;
                case 6:
                    System.out.println("\n--------- REMOVE INVENTORY --------");
                    Inventory inventory = findInventoryById();
                    scanner.nextLine();
                    if(inventory != null){
                        System.out.println("\n-------- INVENTORY FOUND --------");
                        inventory.showInfo();
                        System.out.print("Remove this inventory (y/n)? ");
                        String youSureYouWantToRemove = scanner.nextLine();
                        switch(youSureYouWantToRemove){
                            case "y":
                            case "Y":
                                inventories.remove(inventory);
                                System.out.println("Invetory successfully removed!");
                                break;
                            case "n":
                            case "N":
                                System.out.println("Inventory removal canceled.");
                                break;
                            default:
                                System.out.println("Invalid choice! Please enter 'y' or 'n'.");
                                break;
                        }
                    }
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
        do{
            System.out.println("\n=== WAREHOUSE MANAGEMENT SYSTEM ===");
            System.out.println("------ MANAGE SUPPLIER MENU -------");
            System.out.println("1. See All Supplier");
            System.out.println("2. Search by ID");
            System.out.println("3. Add Supplier");
            System.out.println("4. Modify Supplier");
            System.out.println("5. Remove Supplier");
            System.out.println("6. Return to Main Menu");
            option = validMenuOption(1, 6);

            switch(option){
                case 1:
                    System.out.println("\n--------- ALL SUPPLIERS -----------");
                    if(suppliers.size() < 1){ // if inventory array is empty
                        System.out.println("No supplier yet!");
                    }
                    // loop through each inventories element
                    for(Supplier supplier : suppliers){
                        supplier.showSupplierInfo();
                        System.out.println();
                    }
                    scanner.nextLine();
                    System.out.println(suppliers.size() + " supplier/s returned.");
                    System.out.print("Press 'Enter' to continue...");
                    scanner.nextLine();
                    break;
                case 2:
                    System.out.println("\n----- SEARCH SUPPLIERS by ID ------");
                    if(suppliers.size() < 1){ // if supplier array is empty
                        System.out.println("No supplier yet!");
                        System.out.print("Press 'Enter' to continue...");
                        scanner.nextLine();
                        break;
                    }

                    Supplier supplierFound = findSupplierById();
                    scanner.nextLine();
                    if(supplierFound != null){
                        System.out.println("\n-------- SUPPLIER FOUND ---------");
                        supplierFound.showSupplierInfo();
                        System.out.print("Press 'Enter' to continue...");
                        scanner.nextLine();
                    }
                    break;
                case 3:
                    System.out.println("\n-------- ADD NEW SUPPLIER ---------");
                    // enter amount of new inventory
                    int amount = validNumberInput(
                        "How many new suppliers? ", 
                        "New supplier must be at least 1! Please try again!", 
                        "Invalid input! Please try again!\n");

                    for(int i = 0; i < amount; i++){
                        Supplier newSupplier = new Supplier();
                        // set new supplier id
                        int id = validId(
                            "Enter new supplier ID: ", 
                            "ID must be more than 1! Please try again!", 
                            "ID is number only! Please try again!", 
                            "Inventory ID already exist! Please enter a different one!", 
                            suppliers, Supplier::getSupplierId);
                        newSupplier.setSupplierId(id);
                        scanner.nextLine();

                        // set new supplier name
                        System.out.print("Enter new supplier name: ");
                        newSupplier.setName(scanner.nextLine());

                        // set new supplier company
                        System.out.print("Enter new supplier company: ");
                        newSupplier.setCompany(scanner.nextLine());

                        // set new supplier phone number
                        System.out.print("Enter new supplier contact: ");
                        newSupplier.setPhoneNumber(scanner.nextLine());

                        boolean isValid;
                        do{
                            isValid = true;
                            System.out.print("Activate supplier (y/n)? ");
                            String yOrN = scanner.nextLine();
                            switch(yOrN){
                                case "y":
                                case "Y":
                                    newSupplier.setActive(true);
                                    break;
                                case "n":
                                case "N":
                                    newSupplier.setActive(false);
                                    break;
                                default:
                                    System.out.println("Invalid option! enter only 'y' or 'n'!");
                                    isValid = false;
                            }
                        }while(!isValid);

                        suppliers.add(newSupplier);
                        System.out.println("\n---------- SUPPLIER INFO ----------");
                        newSupplier.showSupplierInfo();
                        System.out.print("Press 'Enter' to continue...");
                        scanner.nextLine();
                        System.out.println();
                    }
                    break;
                case 4:
                    System.out.println("\n------- MODIFY SUPPLIER INFO ------");
                    System.out.println("1. Change Name");
                    System.out.println("2. Change Company");
                    System.out.println("3. Change Contact");
                    System.out.println("4. Change Status");
                    System.out.println("5. Back");
                    option = validMenuOption(1, 5);

                    switch(option){
                        case 1:
                            System.out.println("\n------------ Change Name ----------");
                            Supplier supplierName = findSupplierById();
                            scanner.nextLine();

                            if(supplierName != null){
                                System.out.print("Enter new supplier name: ");
                                supplierName.setName(scanner.nextLine());
                            }
                            break;
                        case 2:
                            System.out.println("\n----------- Change Company --------");
                            Supplier supplierCompany = findSupplierById();
                            scanner.nextLine();

                            if(supplierCompany != null){
                                System.out.print("Enter new supplier company: ");
                                supplierCompany.setCompany(scanner.nextLine());
                            }
                            break;
                        case 3:
                            System.out.println("\n----------- Change Contact --------");
                            Supplier supplierContact = findSupplierById();
                            scanner.nextLine();

                            if(supplierContact != null){
                                System.out.print("Enter new supplier contact: ");
                                supplierContact.setPhoneNumber(scanner.nextLine());
                            }
                            break;
                        case 4:
                            System.out.println("\n----------- Change Status ---------");
                            Supplier supplierStatus = findSupplierById();
                            scanner.nextLine();

                            if(supplierStatus != null){
                                boolean isValid;
                                do{
                                    isValid = true;
                                    System.out.print("Change supplier status to (activate = 1; deactivate = 0): ");
                                    String setStatus = scanner.nextLine();
                                    
                                    switch(setStatus){
                                        case "1":
                                            supplierStatus.activateSupplier();
                                            System.out.print("Press 'Enter' to continue...");
                                            scanner.nextLine();
                                            break;
                                        case "0":
                                            supplierStatus.deactivateSupplier();
                                            System.out.print("Press 'Enter' to continue...");
                                            scanner.nextLine();
                                            break;
                                        default:
                                            System.out.println("Invalid option! Enter 1 or 0!");
                                            isValid = false;
                                            break;
                                    }
                                }while(!isValid);
                            }
                            break;
                        case 5:
                            break;
                    }
                    break;
                case 5:
                    System.out.println("\n--------- REMOVE SUPPLIER ---------");
                    Supplier supplier = findSupplierById();
                    scanner.nextLine();
                    if(supplier != null){
                        System.out.println("\n-------- SUPPLIER FOUND ---------");
                        supplier.showSupplierInfo();
                        System.out.print("Remove this supplier (y/n)? ");
                        String youSureYouWantToRemove = scanner.nextLine();
                        switch(youSureYouWantToRemove){
                            case "y":
                            case "Y":
                                suppliers.remove(supplier);
                                System.out.println("Supplier successfully removed!");
                                break;
                            case "n":
                            case "N":
                                System.out.println("Supplier removal canceled.");
                                break;
                            default:
                                System.out.println("Invalid choice! Please enter 'y' or 'n'.");
                                break;
                        }
                    }
                    break;
                case 6:
                    break;
            }
        }while(option != 6);
    }

    /*
     * Manage Employee Menu
     */
    private static void manageEmployee(){
        int option = 0;
        do{
            System.out.println("\n=== WAREHOUSE MANAGEMENT SYSTEM ===");
            System.out.println("------ MANAGE EMPLOYEE MENU -------");
            System.out.println("1. See All Employee");
            System.out.println("2. Search by ID");
            System.out.println("3. Add Employee");
            System.out.println("4. Modify Employee");
            System.out.println("5. Remove Employee");
            System.out.println("6. Return to Main Menu");
            option = validMenuOption(1, 6);

            switch(option){
                case 1:
                    System.out.println("\n--------- ALL EMPLOYEES -----------");
                    if(employees.size() < 1){ // if employees array is empty
                        System.out.println("No employees yet!");
                    }
                    // loop through each inventories element
                    for(Supplier supplier : suppliers){
                        supplier.showSupplierInfo();
                        System.out.println();
                    }
                    scanner.nextLine();
                    System.out.println(suppliers.size() + " supplier/s returned.");
                    System.out.print("Press 'Enter' to continue...");
                    scanner.nextLine();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
            }
        }while(option != 6);
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
    
    /*private static int validProductId(String question, String error1, String error2, String isExistResponse){
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
    }*/

    /*private static int validInventoryId(String question, String error1, String error2, String isExistResponse){
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
    }*/
    /*private static int validSupplierId(String question, String error1, String error2, String isExistResponse){
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

                for(Supplier existingSupplier : suppliers){
                    if(existingSupplier.getSupplierId() == num){
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
    }*/
    /*private static int validInventoryId(String question, String error1, String error2, String isExistResponse){
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
    }*/

    private static double validDecimalNumberInput(String question, String error1, String error2){
        double num = 0;
        boolean isValid;
        do{
            isValid = true;
            try{
                System.out.print(question);
                num = scanner.nextDouble();
                if(num <= 0){
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
        int id;
        try {
            id = validNumberInput(
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
            if (existingProduct.getProductId() == id) {
                return existingProduct;
            }
        }

        System.out.println("Product ID not found! Please enter a valid one!");
        System.out.print("Press 'Enter' to continue...");
        scanner.nextLine();
        return null;
    }

    private static Inventory findInventoryById() {
        int id;
        try {
            id = validNumberInput(
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
            if (existingInventory.getInventoryId() == id) {
                return existingInventory;
            }
        }

        System.out.println("Inventory ID not found! Please enter a valid one!");
        System.out.print("Press 'Enter' to continue...");
        scanner.nextLine();
        return null;
    }

    private static Supplier findSupplierById() {
        int id;
        try {
            id = validNumberInput(
                "Enter supplier ID: ",
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

        for(Supplier existingSupplier : suppliers){
            if(existingSupplier.getSupplierId() == id){
                return existingSupplier;
            }
        }

        System.out.println("Supplier ID not found! Please enter a valid one!");
        System.out.print("Press 'Enter' to continue...");
        scanner.nextLine();
        return null;
    }

    private static Employee findEmployeeById(){
        int id;
        try {
            id = validNumberInput(
                "Enter employee ID: ",
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

        for(Employee existingEmployee : employees){
            if(existingEmployee.getId() == id){
                return existingEmployee;
            }
        }

        System.out.println("Employee ID not found! Please enter a valid one!");
        System.out.print("Press 'Enter' to continue...");
        scanner.nextLine();
        return null;
    }

    public static <T> int validId(
        String question,
        String error1,
        String error2,
        String isExistResponse,
        ArrayList<T> list,
        Function<T, Integer> idGetter
    ){
        int num = 0;
        boolean isValid;

        do {
            isValid = true;
            try {
                System.out.print(question);
                num = scanner.nextInt();
                if (num < 1) {
                    scanner.nextLine();
                    System.out.println(error1);
                    isValid = false;
                    continue;
                }
                for (T item : list) {
                    if (idGetter.apply(item) == num) {
                        System.out.println(isExistResponse);
                        isValid = false;
                        break;
                    }
                }
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println(error2);
                isValid = false;
            }
        } while (!isValid);
        return num;
    }
}
