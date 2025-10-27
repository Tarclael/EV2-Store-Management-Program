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
                            case 1:
                                System.out.println("\n---------- ALL PRODUCT ------------");
                                if(products.size() < 1){
                                    System.out.println("No products yet!");
                                }
                                for(Product product : products){
                                    product.showInfo();
                                    System.out.println();
                                }
                                scanner.nextLine();
                                System.out.println(products.size() + " product returned.");
                                System.out.print("Press 'Enter' to continue...");
                                scanner.nextLine();
                                break;
                            case 2:
                                System.out.println("\n----- SEARCH PRODUCT by NAME ------");
                                if(products.size() < 1){
                                    System.out.println("No products yet!");
                                }else{
                                    System.out.print("Enter product name: ");
                                    String id = scanner.nextLine();
                                    Product productFound = null;
    
                                    for(Product product : products){
                                        if(product.getProductName().equalsIgnoreCase(id)){
                                            productFound = product;
                                            break;
                                        }
                                    }
    
                                    if (productFound == null) {
                                        System.out.println("Product with name " + id + " not found.");
                                        System.out.print("Press 'Enter' to continue...");
                                        scanner.nextLine();
                                    } else {
                                        System.out.println("********* Reservation Found *********");
                                        productFound.showInfo();
                                    }
                                }
                                scanner.nextLine();
                                System.out.print("Press 'Enter' to continue...");
                                scanner.nextLine();
                                break;
                            case 3:
                                System.out.println("\n--------- ADD NEW PRODUCT ---------");
                                // enter amount of new product
                                int productAmount = validNumberInput("How many new products? ", "New product must be at least 1! Please try again!", "Invalid input! Please try again!\n");

                                for(int i = 0; i < productAmount; i++){
                                    Product product = new Product();
                                    
                                    // set new product ID
                                    int productID = validNumberInput("Enter new product ID: ", "ID must be more than 1! Please try again!", "ID is number only! Please try again!", "Product ID already exist! Please enter a different one!");
                                    product.setProductId(productID);
                                    scanner.nextLine();

                                    // set new product name
                                    System.out.print("Enter new product name: ");
                                    product.setProductName(scanner.nextLine());

                                    // set new product price
                                    double price = validDecimalNumberInput("Enter new product price: $", "Price must be at least $1! Please enter a different one!", "Price is a number only! Please try again!");
                                    product.setPrice(price);

                                    // set new product qty
                                    int qty = validNumberInput("Enter new product quantity: ", "Product quantity must be at least 1!", "Qty is a number only! Please try again!");
                                    product.setQuantity(qty);
                                    scanner.nextLine();

                                    // set new product supplier
                                    System.out.print("Enter valid supplier name: ");
                                    String supplier = scanner.nextLine();
                                    /*
                                    for(Supplier existingSupplier : suppliers){
                                        if(existingSupplier.getSupplierName().equalsIgnoreCase(supplier)){
                                            product.setSupplierName(existingSupplier);
                                            break;
                                        }else{
                                            System.out.print("Supplier name not found! Please change supplier name in 'Modify Product' menu!");
                                            break;
                                        }
                                    }
                                     */

                                    // set new product inventory
                                    System.out.print("Enter valid inventory location: ");
                                    String inventory = scanner.nextLine();

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
                            case 4:
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
                                    case 1:
                                        System.out.println("\n-------- MODIFY PRODUCT INFO ------");
                                        System.out.println("\n------------- Change Id -----------");
                                        Product productId = findProductById();
                                        if(productId != null){
                                            int newId = validNumberInput(
                                                "Enter new product ID: ",
                                                "ID must be more than 1! Please try again!",
                                                "ID is number only! Please try again!",
                                                "Product ID already exist! Please enter a different one!"
                                            );
                                            productId.changeProductID(newId);
                                        }
                                        break;
                                    case 2:
                                        System.out.println("\n-------- MODIFY PRODUCT INFO ------");
                                        System.out.println("\n------------ Change Name ----------");
                                        Product productName = findProductById();
                                        if(productName != null){
                                            System.out.print("Enter new product name: ");
                                            productName.changeProductName(scanner.nextLine());
                                        }
                                        break;
                                    case 3:
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
                                    case 4:
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
                                    case 5:
                                        System.out.println("\n-------- MODIFY PRODUCT INFO ------");
                                        System.out.println("\n---------- Change Supplier --------");
                                        Product productSupplier = findProductById();
                                        if(productSupplier != null){
                                            System.out.print("Enter valid product supplier name: ");
                                            String supplier = scanner.nextLine();
                                            // TODO: add logic to verify supplier
                                        }
                                        break;
                                    case 6:
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
                                break;
                            case 5:
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
                    option = 0;
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
            }
        }while(option != 5);
        scanner.close();
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
    
    private static int validNumberInput(String question, String error1, String error2, String isExistResponse){
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

}
