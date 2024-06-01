package com.imashashop;

import java.util.ArrayList;
import java.util.*;
import java.io.*;
import java.util.Comparator;
public class WestminsterShoppingManager implements ShoppingManager, Serializable{
    public static ArrayList<Product> productList;

    // Constructor initializes productList
    public WestminsterShoppingManager() {
        this.productList = new ArrayList<>();
        loadProduct(); // Load products from file when the program starts
    }

    public static ArrayList getProductList() {

        return productList;
    }
    public static void main(String[] args) {
        WestminsterShoppingManager manager = new WestminsterShoppingManager();
        manager.runMenu();
    }

    public void runMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            // Print menu options
            System.out.println("Welcome to the Management Console");
            System.out.println("-------------------------------");
            System.out.println("1. Add Product");
            System.out.println("2. Delete Product");
            System.out.println("3. Print Products");
            System.out.println("4. Save Product List to file");
            System.out.println("5. Load product List from file");
            System.out.println("6. ShopGUI");
            System.out.println("7. Quit");

            // Get user's choice
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addProduct();
                    break;

                case 2:
                    removeProduct();
                    break;

                case 3:
                    printProduct();
                    break;

                case 4:
                    saveProduct();
                    break;

                case 5:
                    loadProduct();
                    break;

                case 6:
                    new ShopGUI();
                    break;
                case 7:
                    System.out.println("Exiting program...");
                    saveProduct(); // Save products to file when the program exits
                    System.exit(0); // Quit program

                default:
                    System.out.println("Invalid choice . Please try again.");
                    break;
            }
        }while (choice != 7);
    }


    @Override
    public void addProduct() {
        if(productList.size() >= 50) {
            System.out.println("The product limit has reached.");
            return;
        }

        // Input product information
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter product ID: ");
        String productId = scanner.nextLine();

        // Validate productId (non-null and non-empty)
        if (productId == null || productId.trim().isEmpty()) {
            System.out.println("Invalid product ID. It cannot be empty.");
            return;
        }

        // Check if the product with the same ID already exists
        for (Product product : productList) {
            if (product != null && product.getProductId().equals(productId)) {
                System.out.println("Product with ID " + productId + " already exists.");
                return;
            }
        }

        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();

        // Validate productName (non-null and non-empty)
        if (productName == null || productName.trim().isEmpty()) {
            System.out.println("Invalid product Name. It cannot be empty.");
            return;
        }

        System.out.print("Enter number available: ");
        int availableItems = scanner.nextInt();

        // Validate item availability (non-null and non-empty)
        if (availableItems <= 0) {
            System.out.println("Invalid item availability.  It should be greater than zero .");
            return;
        }

        System.out.print("Enter price: ");
        double price = scanner.nextDouble();

        // Validate price (non-null and non-empty)
        if (price <= 0) {
            System.out.println("Invalid price.  It should be greater than zero .");
            return;
        }

        // Create product object
        Product product = null;

        System.out.print("Is this an electronics or clothing product (E/C)? ");
        String type = scanner.next();

        if (!type.equalsIgnoreCase("E") && !type.equalsIgnoreCase("C")) {
            System.out.println("Invalid type. It should be 'E' for electronics or 'C' for clothing.");
            return;
        }

        if(type.equalsIgnoreCase("E")) {
            // Input electronics specific info
            System.out.print("Enter brand: ");
            String brand = scanner.next();

            System.out.print("Enter warranty period: ");
            int warrantyPeriod = scanner.nextInt();

            product = new Electronics(productId, productName, availableItems,
                    price, brand, warrantyPeriod);

        } else if (type.equalsIgnoreCase("C")) {
            // Input clothing specific info
            System.out.print("Enter size: ");
            String size = scanner.next();

            System.out.print("Enter color: ");
            String color = scanner.next();

            product = new Clothing(productId, productName, availableItems,
                    price, size, color);
        }

        // Add product to list
        productList.add(product);

        System.out.println("Product added successfully!");
    }

    @Override
    public void removeProduct() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter product ID to delete: ");
        String productIdToDelete = scanner.nextLine();

        // Validate productIdToDelete (non-null and non-empty)
        if (productIdToDelete == null || productIdToDelete.trim().isEmpty()) {
            System.out.println("Invalid product ID to delete. It cannot be null or empty.");
            return;
        }

        Product productToDelete = null;
        for (Product product : productList) {
            if (product != null && product.getProductId().equals(productIdToDelete)) {
                productToDelete = product;
                break;
            }
        }

        if (productToDelete != null) {
            productList.remove(productToDelete);
            System.out.println("Product deleted successfully!");

            // Display information about the deleted product
            System.out.println("Deleted Product Information:");
            System.out.println("Product ID: " + productToDelete.getProductId());
            System.out.println("Product Name: " + productToDelete.getProductName());

            // Determine if it's electronics or clothing and display relevant information
            if (productToDelete instanceof Electronics) {
                Electronics electronics = (Electronics) productToDelete;
                System.out.println("Type: Electronics");
                System.out.println("Brand: " + electronics.getBrand());
                System.out.println("Warranty Period: " + electronics.getWarrantyPeriod() + " months");
            } else if (productToDelete instanceof Clothing) {
                Clothing clothing = (Clothing) productToDelete;
                System.out.println("Type: Clothing");
                System.out.println("Size: " + clothing.getSize());
                System.out.println("Color: " + clothing.getColor());
            }

            System.out.println("Total number of products left: " + productList.size());
        } else {
            System.out.println("Product with ID " + productIdToDelete + " not found.");
        }
    }
    @Override
    public void printProduct() {
        if (productList.isEmpty()) {
            System.out.println("No products available.");
            return;
        }

        // Sort the product list alphabetically by product ID, handling nulls
        productList.sort(Comparator.comparing(p -> p == null ? "" : p.getProductId(), Comparator.nullsFirst(String::compareTo)));

        System.out.println("List of Products:");
        for (Product product : productList) {
            if (product == null) {
                // Handle null elements gracefully
                System.out.println("Null Product");
                System.out.println("------------");
                continue;
            }

            System.out.println("Product ID: " + product.getProductId());
            System.out.println("Product Name: " + product.getProductName());
            System.out.println("Available Items: " + product.getAvailableItems());
            System.out.println("Price: $" + product.getPrice());

            // Determine if it's electronics or clothing and display relevant information
            if (product instanceof Electronics) {
                Electronics electronics = (Electronics) product;
                System.out.println("Type: Electronics");
                System.out.println("Brand: " + electronics.getBrand());
                System.out.println("Warranty Period: " + electronics.getWarrantyPeriod() + " months");
            } else if (product instanceof Clothing) {
                Clothing clothing = (Clothing) product;
                System.out.println("Type: Clothing");
                System.out.println("Size: " + clothing.getSize());
                System.out.println("Color: " + clothing.getColor());
            }

            System.out.println("------------");
        }
    }

    @Override
    public void saveProduct() {
        try {
            FileOutputStream fos = new FileOutputStream("productList.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            // Write each product to file
            for (Product product : productList) {
                oos.writeObject(product);
            }
            oos.close();
            fos.close();
            System.out.println("Products saved to file successfully!");
        } catch (IOException e) {
            System.out.println("Error saving products to file!");
        }
    }

    @Override
    public void loadProduct() {
        try {
            FileInputStream fis = new FileInputStream("productList.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);

            // Clear the existing productList before loading from the file
            productList.clear();

            // Read objects back from file
            try {
                while (true) {
                    Product product = (Product) ois.readObject();
                    productList.add(product);
                }
            } catch (EOFException e) {
                // End of file reached, do nothing
            }

            ois.close();
            fis.close();

            System.out.println("Products loaded from file successfully!");

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading products from file!");
        }

    }
}
