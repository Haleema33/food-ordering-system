import java.io.*;
import java.time.ZonedDateTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.time.temporal.ChronoUnit;
import java.time.ZoneId;
import java.time.Duration;
import java.util.Arrays;


public class FoodDeliverySystem {
    private static Scanner scanner = new Scanner(System.in);
    private static List<MenuItem> menuItems = new ArrayList<>();
    private static List<String> ordersList = new ArrayList<>();
    private static final int PREPARE_TIME_MINUTES = 10;
    private static final int DELIVER_TIME_MINUTES = 20;
    private static final String ORDERS_FILE = "orders.txt";
    public static void main(String[] args) {
        loadData();
        mainMenu();
    }

    private static void loadData() {
        // Loading data from files
        List<String> customers = loadFromFile("customers.txt");
        List<String> menuItems = loadFromFile("menu_items.txt");
        ordersList = loadFromFile("orders.txt");
        List<String> pendingRequests = loadFromFile("pending_requests.txt");
        List<String> restaurants = loadFromFile("restaurants.txt");
        List<String> appOwner = loadFromFile("appowner.txt");
    }
    
    private static List<String> getMenuItemsByRestaurantId(int restaurantId) {
        List<String> menuItems = new ArrayList<>();

        List<String> lines = readLinesFromFile("menu_items.txt");
        for (String line : lines) {
            String[] parts = line.split("\\|");
            if (Integer.parseInt(parts[0]) == restaurantId) {
                menuItems.add(line);
            }
        }

        return menuItems;
    }


    private static List<String> getMenuItemsByRestaurantName(String restaurantName) {
        List<String> menuItems = new ArrayList<>();

        List<String> lines = readLinesFromFile("menu_items.txt");
        for (String line : lines) {
            String[] parts = line.split("\\|");
            if (parts[1].equals(restaurantName)) {
                menuItems.add(line);
            }
        }

        return menuItems;
    }


    private static void updateMenuItem(String menuItemId, String updatedMenuItem) {
        List<String> lines = readLinesFromFile("menu_items.txt");
        List<String> updatedLines = new ArrayList<>();

        for (String line : lines) {
        	String[] parts = line.split("\\|");
            if (parts[0].equals(menuItemId)) {
                updatedLines.add(updatedMenuItem);
            } else {
                updatedLines.add(line);
            }
        }

        writeLinesToFile("menu_items.txt", updatedLines);
    }

    
    private static List<String> loadFromFile(String filename) {
        List<String> data = new ArrayList<>();
        try {
            Path path = Paths.get(filename);
            if (!Files.exists(path)) {
                System.out.println("Oops, it seems like you are missing the " + filename + " file.");
                return data;
            }
            data = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private static void mainMenu() {
        while (true) {
            System.out.println("Welcome to the QuickBite!");
            System.out.println("Please choose your role:");
            System.out.println("1. Customer");
            System.out.println("2. Restaurant Owner");
            System.out.println("3. App Owner");
            System.out.println("4. Exit");
            System.out.print("Enter the number of your choice: ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    customerMenu();
                    break;
                case 2:
                    restaurantOwnerMenu();
                    break;
                case 3:
                    appOwnerMenu();
                    break;
                case 4:
                    System.out.println("Exiting the Food Delivery System. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }


    private static void customerMenu() {
        while (true) {
            System.out.println("Customer Menu");
            System.out.println("1. Log In");
            System.out.println("2. Sign Up");
            System.out.println("3. Press Space and Enter to go back to Main Menu");
            System.out.print("Enter the number of your choice: ");

            String choice;
            try {
                choice = scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
                continue;
            }

            if (choice.equals(" ")) {
                mainMenu();
                break;
            }

            int numericChoice;
            try {
                numericChoice = Integer.parseInt(choice);
            } catch (NumberFormatException e) {
                System.out.println("Invalid option.");
                continue;
            }

            switch (numericChoice) {
                case 1:
                    customerLogin();
                    break;
                case 2:
                    customerSignUp();
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }


    private static void restaurantOwnerMenu() {
        while (true) {
            System.out.println("Restaurant Owner Menu");
            System.out.println("1. Log In");
            System.out.println("2. Sign Up");
            System.out.println("3. Press Space and Enter to go back to Main Menu");
            System.out.print("Enter the number of your choice: ");

            String choice;
            try {
                choice = scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
                continue;
            }

            if (choice.equals(" ")) {
                mainMenu();
                break;
            }

            int numericChoice;
            try {
                numericChoice = Integer.parseInt(choice);
            } catch (NumberFormatException e) {
                System.out.println("Invalid option.");
                continue;
            }

            switch (numericChoice) {
                case 1:
                    restaurantOwnerLogin();
                    break;
                case 2:
                    restaurantOwnerSignUp();
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }


    private static void appOwnerMenu() {
        while (true) {
            System.out.println("App Owner Menu");
            System.out.println("1. Log In");
            System.out.println("2. Press Space and Enter to go back to Main Menu");
            System.out.print("Enter the number of your choice: ");

            String choice;
            try {
                choice = scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
                continue;
            }

            if (choice.equals(" ")) {
                mainMenu();
                break;
            }

            int numericChoice;
            try {
                numericChoice = Integer.parseInt(choice);
            } catch (NumberFormatException e) {
                System.out.println("Invalid option.");
                continue;
            }

            switch (numericChoice) {
                case 1:
                    appOwnerLogin();
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }



    private static int customerLogin() {
        System.out.println("Customer Login");

        int customerId = -1;

        while (customerId == -1) {
            try {
                System.out.print("Enter your email: ");
                String email = scanner.nextLine();
                System.out.print("Enter your password: ");
                String password = scanner.nextLine();

                customerId = getCustomerId("customers.txt", email, password);
                if (customerId != -1) {
                    System.out.println("Login successful! Welcome to the delivery app.");
                    customerDeliveryApp(customerId);
                } else {
                    System.out.println("Incorrect email or password.");
                    System.out.println("Press R to retry or any other key to return to the main menu:");
                    String retryChoice = scanner.nextLine().toUpperCase();
                    if (!retryChoice.equals("R")) {
                        mainMenu();
                        break;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again.");
                scanner.nextLine();
            }
        }

        return customerId;
    }



    private static int getCustomerId(String fileName, String email, String password) {
        int customerId = -1;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] customerInfo = line.split(",");
                if (customerInfo[2].equals(email) && customerInfo[3].equals(password)) {
                    customerId = Integer.parseInt(customerInfo[0]);
                    break;
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading customers file.");
        }
        return customerId;
    }

    
    
    private static int customerSignUp() {
        System.out.println("Customer Sign Up");

        String name = "";
        String email = "";
        String password = "";

        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print("Enter your name: ");
                name = scanner.nextLine();
                System.out.print("Enter your email: ");
                email = scanner.nextLine();
                System.out.print("Enter your password: ");
                password = scanner.nextLine();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again.");
                scanner.nextLine();
            }
        }

        int customerId = generateNewCustomerId();
        String newCustomer = String.format("%d,%s,%s,%s", customerId, name, email, password);
        appendLineToFile("customers.txt", newCustomer);

        System.out.println("Customer sign up successful.");
        return customerId;
    }

    
    private static int generateNewCustomerId() {
        int highestCustomerId = 0;
        List<String> customerLines = readLinesFromFile("customers.txt");
        for (String line : customerLines) {
            if (!line.trim().isEmpty()) { // Add this condition to check if the line is empty
                String[] customerInfo = line.split(",");
                int customerId = Integer.parseInt(customerInfo[0]);
                if (customerId > highestCustomerId) {
                    highestCustomerId = customerId;
                }
            }
        }
        return highestCustomerId + 1;
    }

    private static int getRestaurantId(String fileName, String email, String password) {
        int restaurantId = -1;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] restaurantInfo = line.split(",");
                if (restaurantInfo.length >= 6) { // Check if the line is valid
                    if (restaurantInfo[4].equals(email) && restaurantInfo[5].equals(password)) {
                        restaurantId = Integer.parseInt(restaurantInfo[0]);
                        break;
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading restaurants file.");
        }
        return restaurantId;
    }

    private static boolean checkLoginCredentials(String fileName, String email, String password) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userInfo = line.split(",");
                if (userInfo[1].equals(email) && userInfo[2].equals(password)) {
                    reader.close();
                    return true;
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public static int restaurantOwnerLogin() {
        System.out.println("Restaurant Owner Login");

        int restaurantId = -1;

        while (restaurantId == -1) {
            try {
                System.out.print("Enter your email: ");
                String email = scanner.nextLine();
                System.out.print("Enter your password: ");
                String password = scanner.nextLine();

                restaurantId = getRestaurantId("restaurants.txt", email, password);

                if (restaurantId != -1) {
                    System.out.println("Login successful! Welcome to the restaurant management app.");
                    restaurantOwnerApp(restaurantId);
                } else {
                    System.out.println("Incorrect email or password.");
                    System.out.println("Press R to retry or any other key to return to the main menu:");
                    String retryChoice = scanner.nextLine().toUpperCase();
                    if (!retryChoice.equals("R")) {
                        mainMenu();
                        break;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again.");
                scanner.nextLine();
            }
        }

        return restaurantId;
    }


    private static void restaurantOwnerSignUp() {
        System.out.println("Restaurant Owner Sign Up");

        boolean validName = false;
        String restaurantName = "";
        while (!validName) {
            System.out.print("Enter your restaurant name: ");
            restaurantName = scanner.nextLine().toLowerCase();
            if (restaurantName.matches("^[a-zA-Z\\s]+$")) {
                List<String> pendingRequests = readLinesFromFile("pending_requests.txt");
                List<String> acceptedRequests = readLinesFromFile("restaurants.txt");
                boolean nameExists = false;
                for (String request : pendingRequests) {
                    String[] requestInfo = request.split(",");
                    if (requestInfo[0].equalsIgnoreCase(restaurantName)) {
                        nameExists = true;
                        System.out.println("A previous request for this restaurant name is still pending approval.");
                        break;
                    }
                }
                if (!nameExists) {
                    for (String request : acceptedRequests) {
                        String[] requestInfo = request.split(",");
                        if (requestInfo[1].equalsIgnoreCase(restaurantName)) {
                            nameExists = true;
                            System.out.println("A restaurant with this name already exists.");
                            break;
                        }
                    }
                }
                if (!nameExists) {
                    validName = true;
                }
            } else {
                System.out.println("Invalid name. Please enter alphabets and spaces only.");
            }
        }

        boolean validLocation = false;
        String location = "";
        while (!validLocation) {
            System.out.print("Enter your restaurant city in Pakistan: ");
            location = scanner.nextLine().toLowerCase();
            if (Arrays.asList("karachi", "lahore", "islamabad", "quetta", "peshawar").contains(location)) {
                validLocation = true;
            } else {
                System.out.println("Invalid city. Please choose from Karachi, Lahore, Islamabad, Quetta, Peshawar.");
            }
        }

        System.out.print("Enter your restaurant cuisine: ");
        String cuisine = scanner.nextLine();

        boolean validEmail = false;
        String email = "";
        while (!validEmail) {
            System.out.print("Enter your email: ");
            email = scanner.nextLine().toLowerCase();
            if (email.matches("^\\S+@\\S+\\.\\S+$")) {
                validEmail = true;
            } else {
                System.out.println("Invalid email address. Please try again.");
            }
        }

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        List<String> pendingRequests = readLinesFromFile("pending_requests.txt");
        for (String request : pendingRequests) {
            String[] requestInfo = request.split(",");
            if (requestInfo[0].equalsIgnoreCase(restaurantName) && requestInfo[1].equalsIgnoreCase(location) && requestInfo[3].equalsIgnoreCase(email)) {
                String status = requestInfo[5];
                if (status.equalsIgnoreCase("pending")) {
                    System.out.println("Your previous request for this restaurant name and location with this email address is still pending approval.");
                } else if (status.equalsIgnoreCase("accepted")) {
                    System.out.println("Your previous request for this restaurant name and location with this email address has been accepted.");
                } else if (status.equalsIgnoreCase("denied")) {
                    System.out.println("Your previous request for this restaurant name and location with this email address has been denied.");
                }
                return;
            }
        }

        List<String> acceptedRequests = readLinesFromFile("restaurants.txt");
        for (String request : acceptedRequests) {
        	String[] requestInfo = request.split(",");
        	if (requestInfo[1].equalsIgnoreCase(restaurantName) && requestInfo[2].equalsIgnoreCase(location) && requestInfo[4].equalsIgnoreCase(email)) {
        	System.out.println("A restaurant with this name already exists in this location with the same email address.");
        	return;
        	}
        	}
        String pendingRequest = String.format("%s,%s,%s,%s,%s,pending", restaurantName, location, cuisine, email, password);
        appendLineToFile("pending_requests.txt", pendingRequest);

        System.out.println("Restaurant owner sign up successful. Your request is pending approval.");
    }


    
    private static void appOwnerLogin() {
        System.out.println("App Owner Login");

        boolean loginSuccess = false;

        while (!loginSuccess) {
            try {
                System.out.print("Enter your email: ");
                String email = scanner.nextLine();
                System.out.print("Enter your password: ");
                String password = scanner.nextLine();

                loginSuccess = checkLoginCredentials("appowner.txt", email, password);

                if (loginSuccess) {
                    System.out.println("Login successful! Welcome to the app owner dashboard.");
                    appOwnerDashboard();
                } else {
                    System.out.println("Incorrect email or password.");
                    System.out.println("Press R to retry or any other key to return to the main menu:");
                    String retryChoice = scanner.nextLine().toUpperCase();
                    if (!retryChoice.equals("R")) {
                        mainMenu();
                        break;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again.");
                scanner.nextLine();
            }
        }
    }



    private static void appendLineToFile(String fileName, String line) {
        try (FileWriter fileWriter = new FileWriter(fileName, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
             PrintWriter printWriter = new PrintWriter(bufferedWriter)) {

            printWriter.println(); // Add a newline character before appending the content
            printWriter.print(line);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + fileName);
            e.printStackTrace();
        }
    }




    
    private static void customerDeliveryApp(int customerID) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Customer Menu Dashboard");
            System.out.println("1. Browse menu");
            System.out.println("2. Place an order");
            System.out.println("3. Track order");
            System.out.println("4. Logout");
            System.out.print("Enter the number of your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    browseMenu(customerID);
                    break;
                case 2:
                    Duration allowedDuration = Duration.ofMinutes(20);
                    if (hasRecentOrder(customerID, ordersList, allowedDuration)) {
                        System.out.println("You have placed an order recently. Please wait till order has been delivered before placing another order.");
                    } else {
                        placeOrder(customerID);
                    }
                    break;
                case 3:
                    trackOrder(customerID);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        }
    }

    private static boolean hasRecentOrder(int customerId, List<String> orders, Duration allowedDuration) {
        List<String> customerOrders = getCustomerOrders(customerId, orders);
        if (customerOrders.isEmpty()) {
            return false;
        }

        String latestOrder = findLatestOrder(customerOrders);
        String[] orderDetails = latestOrder.split(",");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String orderDateTimeString = orderDetails[7].substring(orderDetails[7].indexOf(':') + 2).trim();
        LocalDateTime orderLocalDateTime = LocalDateTime.parse(orderDateTimeString, formatter);
        ZonedDateTime orderDateTime = orderLocalDateTime.atZone(ZoneId.of("Asia/Karachi"));

        long minutesElapsed = ChronoUnit.MINUTES.between(orderDateTime, ZonedDateTime.now(ZoneId.of("Asia/Karachi")));

        return minutesElapsed < allowedDuration.toMinutes();
    }


    private static void browseMenu(int customerID) {
        System.out.println("Menu Items:");

        List<String> restaurantNames = readAllRestaurants();
        for (String restaurantName : restaurantNames) {
            List<String> menuItems = readMenuItems(restaurantName);
            if (menuItems.isEmpty()) {
                System.out.println(restaurantName + ":\n\tNo menu items available right now\n");
            } else {
                System.out.println(restaurantName + ":");
                menuItems.forEach(System.out::println);
                System.out.println();
            }
        }


        System.out.println("Double press Enter to go back to the main menu.");
        scanner.nextLine(); // Consume the newline character
        scanner.nextLine(); // Wait for the user to press Enter

        customerDeliveryApp(customerID); // Call the customerDeliveryApp method with customerId
    }

    private static int getIntInput(String prompt, int min, int max) {
        int choice = -1;
        while (choice < min || choice > max) {
            System.out.print(prompt);
            try {
                choice = scanner.nextInt();
                if (choice < min || choice > max) {
                    System.out.println("Invalid input. Please enter a number between " + min + " and " + max + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }
        scanner.nextLine(); // Consume the newline character
        return choice;
    }

    private static int getIntInput(String prompt) {
        int choice = -1;
        while (choice == -1) {
            System.out.print(prompt);
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }
        scanner.nextLine(); // Consume the newline character
        return choice;
    }

    private static List<String> getDeliverableCities() {
        List<String> deliverableCities = new ArrayList<>();
        List<String> allRestaurants = readLinesFromFile("restaurants.txt");
        for (String restaurant : allRestaurants) {
            String[] restaurantDetails = restaurant.split(",");
            String city = restaurantDetails[2].trim();
            if (!deliverableCities.contains(city)) {
                deliverableCities.add(city);
            }
        }
        return deliverableCities;
    }


    
    private static void placeOrder(int customerId) {
        String city;
        List<String> restaurants;

        while (true) {
            // Ask for the desired city
            System.out.println("Enter the city for delivery:");
            city = scanner.nextLine();

            // Show the list of restaurants in the specified city
            restaurants = readRestaurantsInCity(city);

            if (restaurants.isEmpty()) {
                List<String> deliverableCities = getDeliverableCities();
                System.out.println("We do not deliver to " + city + ".");
                System.out.println("We deliver to the following cities: " + String.join(", ", deliverableCities));
                System.out.println("Enter '1' to try another city, or '2' to exit the program.");
                int choice = getIntInput("Enter the number of your choice: ", 1, 2);
                if (choice == 2) {
                    return;
                }
            } else {
                break;
            }
        }

        // Show the list of restaurants in the specified city
        System.out.println("Select a restaurant:");
        for (int i = 0; i < restaurants.size(); i++) {
            System.out.println((i + 1) + ". " + restaurants.get(i));
        }

        int selectedRestaurantIndex = getIntInput("Enter the number of the restaurant: ", 1, restaurants.size());
        String selectedRestaurant = restaurants.get(selectedRestaurantIndex - 1);
        int selectedRestaurantId = getRestaurantId(selectedRestaurant); // New line: Get the selected restaurant ID

        // Display menu items for the chosen restaurant
        List<String> menuItems = readMenuItems(selectedRestaurant);
        System.out.println("Menu items for " + selectedRestaurant + ":");
        for (String menuItem : menuItems) {
            System.out.println(menuItem);
        }

        // Get order details from the customer
        int itemId = getIntInput("Enter menu item ID to order: ", 1, Integer.MAX_VALUE);

        // Check if the entered menu item ID belongs to the displayed restaurant menu
        boolean validItemId = false;
        for (String menuItem : menuItems) {
            int displayedItemId = Integer.parseInt(menuItem.split("\\.")[0].trim());
            if (displayedItemId == itemId) {
                validItemId = true;
                break;
            }
        }

        if (!validItemId) {
            System.out.println("Invalid menu item ID. Please enter a valid ID from the displayed menu.");
            return;
        }
        int quantity = getIntInput("Enter quantity: ");
        int deliveryOption = getIntInput("Enter delivery (1) or pickup (2): ", 1, 2);
        System.out.println("Enter delivery/pickup location:");
        String location = scanner.nextLine();
        
        // Generate the current timestamp
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = now.format(formatter);

        System.out.println("Choose payment method:");
        System.out.println("1. Credit Card");
        System.out.println("2. Debit Card");
        System.out.println("3. Jazz Cash");
        System.out.println("4. Easypaisa");
        int paymentOption = getIntInput("Enter the number of your choice: ", 1, 4);

        System.out.println("Enter payment details:");
        String paymentDetails = scanner.nextLine();

        // Input validation for payment details
        boolean validPaymentDetails = false;
        while (!validPaymentDetails) {
            if (paymentOption == 1 || paymentOption == 2) {
                if (paymentDetails.matches("\\d{4}-\\d{4}-\\d{4}-\\d{4}")) {
                    validPaymentDetails = true;
                } else {
                    System.out.println("Please enter card number in the format: 1111-2222-3333-4444");
                }
            } else if (paymentOption == 3 || paymentOption == 4) {
                if (paymentDetails.matches("03\\d{2}-\\d{7}")) {
                    validPaymentDetails = true;
                } else {
                    System.out.println("Please enter mobile number in the format: 03XX-XXXXXXX");
                }
            }

            if (!validPaymentDetails) {
                System.out.println("Enter payment details:");
                paymentDetails = scanner.nextLine();
            }
        }

        String orderType = deliveryOption == 1 ? "delivery" : "pickup";
        String paymentMethod = "";
        switch (paymentOption) {
            case 1:
                paymentMethod = "Credit Card";
                break;
            case 2:
                paymentMethod = "Debit Card";
                break;
            case 3:
                paymentMethod = "Jazz Cash";
                break;
            case 4:
                paymentMethod = "Easypaisa";
                break;
            default:
                System.out.println("Invalid payment option.");
                return;
        }

        // Generate a new order ID (assuming the order ID is an incremental integer)
        int newOrderId = generateNewOrderId();

        // Save the new order to the "orders.txt" file
        String newOrder = String.format("OrderID: %d, RestaurantID: %d, CustomerID: %d, ItemID: %d, Quantity: %d, OrderType: %s, Location: %s, Time: %s, PaymentMethod: %s, PaymentDetails: %s, Timestamp: %s", newOrderId, selectedRestaurantId, customerId, itemId, quantity, orderType, location, timestamp, paymentMethod, paymentDetails, timestamp);
        appendLineToFile("orders.txt", newOrder);

        // Update inventory in the "menu_items.txt" file
        List<String> menuItemsLines = readLinesFromFile("menu_items.txt");
        List<String> updatedMenuItemsLines = new ArrayList<>();
        for (String menuItemLine : menuItemsLines) {
            String[] menuItemInfo = menuItemLine.split("\\|");
            if (Integer.parseInt(menuItemInfo[0].trim()) == itemId) {
                int currentQuantity = Integer.parseInt(menuItemInfo[5].trim());
                int updatedQuantity = Math.max(0, currentQuantity - quantity); // Ensure the quantity does not go below 0
                menuItemInfo[5] = String.valueOf(updatedQuantity);
                String updatedMenuItemLine = String.join("|", menuItemInfo);
                updatedMenuItemsLines.add(updatedMenuItemLine);
            } else {
                updatedMenuItemsLines.add(menuItemLine);
            }
        }
        writeLinesToFile("menu_items.txt", updatedMenuItemsLines);

        System.out.println("Order placed successfully!");
        System.out.println("Your food is being prepared");
    }


    private static List<String> readMenuItems(int restaurantId) {
        List<String> menuItems = new ArrayList<>();
        List<String> menuItemLines = readLinesFromFile("menu_items.txt");

        for (String line : menuItemLines) {
            String[] menuItemInfo = line.split("\\|");
            int itemRestaurantId = Integer.parseInt(menuItemInfo[0].trim());
            if (itemRestaurantId == restaurantId) {
                menuItems.add(line);
            }
        }

        return menuItems;
    }


    

    public static int getRestaurantId(String restaurantName) {
        int id = -1;
        try {
            File file = new File("restaurants.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] temp = line.split(",", 3); // Change the limit to 3
                if (temp.length > 1 && temp[1].trim().equalsIgnoreCase(restaurantName)) {
                    id = Integer.parseInt(temp[0]);
                    break;
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return id;
    }


    private static void trackOrder(int customerID) {
        List<String> customerOrders = getCustomerOrders(customerID, ordersList);

        if (customerOrders.isEmpty()) {
            System.out.println("No orders found for this customer.");
            return;
        }

        String latestOrder = findLatestOrder(customerOrders);

        String[] orderDetails = latestOrder.split(",");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String orderDateTimeString = orderDetails[7].substring(orderDetails[7].indexOf(':') + 2).trim();
        LocalDateTime orderLocalDateTime = LocalDateTime.parse(orderDateTimeString, formatter);
        ZonedDateTime orderDateTime = orderLocalDateTime.atZone(ZoneId.of("Asia/Karachi"));

        ZonedDateTime currentDateTime = ZonedDateTime.now(ZoneId.of("Asia/Karachi"));
        long minutesElapsed = ChronoUnit.MINUTES.between(orderDateTime, currentDateTime);

        if (minutesElapsed < 10) {
            long remainingMinutes = 10 - minutesElapsed;
            System.out.println("Your food is getting prepared. " + remainingMinutes + " minutes left.");
        } else {
            long remainingMinutes = 20 - minutesElapsed;
            if (remainingMinutes > 0) {
                System.out.println("Your rider is on the way. ETA: " + remainingMinutes + " minutes.");
            } else {
                System.out.println("Your order should have arrived. If not, please contact the restaurant. Email us at customerService@quickbite.com");
            }
        }
    }




    private static String findLatestOrder(List<String> customerOrders) {
        LocalDateTime latestDateTime = null;
        String latestOrderLine = null;
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        for (String orderLine : customerOrders) {
            String[] orderDetails = orderLine.split(",");
            String orderDateTimeString = orderDetails[7].substring(orderDetails[7].indexOf(':') + 2).trim();
            LocalDateTime orderDateTime = LocalDateTime.parse(orderDateTimeString, formatter);
            
            if (latestDateTime == null || orderDateTime.isAfter(latestDateTime)) {
                latestDateTime = orderDateTime;
                latestOrderLine = orderLine;
            }
        }
        
        return latestOrderLine;
    }





    private static List<String> getCustomerOrders(int customerId, List<String> ordersList) {
        List<String> customerOrders = new ArrayList<>();

        // Loop through all orders
        for (String orderLine : ordersList) {
            String[] orderDetails = orderLine.split(",");

            // Get the customer ID from the order details
            int orderCustomerId = Integer.parseInt(orderDetails[2].substring(orderDetails[2].indexOf(':') + 2).trim());

            // If the customer ID from the order matches the input customer ID, add the order to the customerOrders list
            if (orderCustomerId == customerId) {
                customerOrders.add(orderLine);
            }
        }

        return customerOrders;
    }
    

    private static int generateNewOrderId() {
        int highestOrderId = 0;
        List<String> orderLines = readLinesFromFile("orders.txt");
        for (String line : orderLines) {
            String[] orderInfo = line.split(",");
            String orderIdString = orderInfo[0].trim().substring("OrderID: ".length());
            int orderId = Integer.parseInt(orderIdString);
            if (orderId > highestOrderId) {
                highestOrderId = orderId;
            }
        }
        return highestOrderId + 1;
    }



    private static List<String> readRestaurantsInCity(String city) {
        List<String> restaurants = new ArrayList<>();
        List<String> restaurantLines = readLinesFromFile("restaurants.txt");

        for (String line : restaurantLines) {
            String[] restaurantInfo = line.split(",");
            if (restaurantInfo.length >= 3 && restaurantInfo[2].trim().equalsIgnoreCase(city)) {
                // Check if the restaurant has any menu items
                int restaurantId = Integer.parseInt(restaurantInfo[0].trim());
                List<String> menuItems = readMenuItems(restaurantId); // Pass restaurantId instead of restaurant name
                if (!menuItems.isEmpty()) {
                    // Only add the restaurant if it has menu items
                    restaurants.add(restaurantInfo[1]);
                }
            }
        }

        return restaurants;
    }

    private static List<String> readAllRestaurants() {
        List<String> restaurants = new ArrayList<>();
        List<String> lines = readLinesFromFile("restaurants.txt");

        for (String line : lines) {
            String[] fields = line.split(",");
            String restaurantName = fields[1].trim();
            restaurants.add(restaurantName);
        }

        return restaurants;
    }


    
    private static List<String> readMenuItems(String restaurantName) {
        List<String> menuItems = new ArrayList<>();
        List<String> allMenuItems = readLinesFromFile("menu_items.txt");

        for (String line : allMenuItems) {
            String[] parts = line.split("\\|", -1);

            if (parts.length < 6) {
                System.out.println("Invalid line in menu_items.txt: " + line);
                continue;
            }

            String menuItemRestaurant = parts[1].trim();
            if (menuItemRestaurant.equals(restaurantName)) {
                int itemId = Integer.parseInt(parts[0].trim());
                String itemName = parts[2].trim();
                String itemDescription = parts[3].trim();
                double itemPrice = Double.parseDouble(parts[4].trim());
                int itemInventory = Integer.parseInt(parts[5].trim());

                String menuItem = String.format("%d. %s - %s - Price: %.2f - Inventory: %d", itemId, itemName, itemDescription, itemPrice, itemInventory);
                menuItems.add(menuItem);
            }
        }

        return menuItems;
    }




    
    public class MenuItem {
        private int id;
        private String restaurantName;
        private String name;
        private double price;
        private String description;

        public MenuItem(int id, String restaurantName, String name, double price, String description) {
            this.id = id;
            this.restaurantName = restaurantName;
            this.name = name;
            this.price = price;
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public String getRestaurantName() {
            return restaurantName;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return String.format("%d - %s - $%.2f - %s", id, name, price, description);
        }
    }
    
    private static void restaurantOwnerApp(int restaurantId) {
        boolean keepRunning = true;

        while (keepRunning) {
            System.out.println("Restaurant Owner Dashboard:");
            System.out.println("1. Manage Menu");
            System.out.println("2. Manage Inventory");
            System.out.println("3. Track Item Sales");
            System.out.println("4. View Orders");
            System.out.println("5. Logout");

            int choice = getIntInput("Enter the number of your choice: ", 1, 5);

            switch (choice) {
                case 1:
                    manageMenu(restaurantId);
                    break;
                case 2:
                    manageInventory(restaurantId);
                    break;
                case 3:
                    trackItemSales(restaurantId);
                    break;
                case 4:
                    displayRestaurantOrders(restaurantId);
                    break;
                case 5:
                    keepRunning = false;
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }



    private static void displayRestaurantOrders(int restaurantId) {
        List<String> orderLines = readLinesFromFile("orders.txt");
        List<String> restaurantOrders = new ArrayList<>();

        for (String line : orderLines) {
            String[] orderInfo = line.split(", ");
            int currentRestaurantId = Integer.parseInt(orderInfo[1].split(": ")[1].trim());
            if (currentRestaurantId == restaurantId) {
                restaurantOrders.add(line);
            }
        }

        if (restaurantOrders.isEmpty()) {
            System.out.println("No orders found for this restaurant.");
        } else {
            System.out.println("Orders for the restaurant (ID: " + restaurantId + "):");
            for (String order : restaurantOrders) {
                String[] orderInfo = order.split(", ");
                String orderType = orderInfo[5].split(": ")[1].trim();
                LocalDateTime orderTime = LocalDateTime.parse(orderInfo[7].split(": ")[1].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                LocalDateTime now = LocalDateTime.now();
                long minutesSinceOrder = ChronoUnit.MINUTES.between(orderTime, now);

                String status = "";
                if (orderType.equals("delivery")) {
                    if (minutesSinceOrder < 20) {
                        status = "being delivered";
                    } else {
                        status = "delivered";
                    }
                }

                String orderId = orderInfo[0].split(": ")[1].trim();
                String customerId = orderInfo[2].split(": ")[1].trim();
                String item = orderInfo[3].split(": ")[1].trim();
                String quantity = orderInfo[4].split(": ")[1].trim();
                String location = orderInfo[6].split(": ")[1].trim();
                String paymentMethod = orderInfo[8].split(": ")[1].trim();
                String paymentDetails = orderInfo[9].split(": ")[1].trim();

                String formattedOrder = String.format("Order #%s for customer #%s: %s x %s - %s at %s (%s paid with %s)", orderId, customerId, quantity, item, orderType, location, paymentMethod, paymentDetails);

                if (!status.equals("")) {
                    formattedOrder += " - " + status;
                }

                System.out.println(formattedOrder);
            }
        }
    }

    
    private static String getRestaurantNameById(int restaurantId) {
        List<String> restaurantLines = readLinesFromFile("restaurants.txt");
        for (String line : restaurantLines) {
            String[] restaurantInfo = line.split(",");
            int currentRestaurantId = Integer.parseInt(restaurantInfo[0].trim());
            if (currentRestaurantId == restaurantId) {
                return restaurantInfo[1].trim();
            }
        }
        return null;
    }

    private static int generateNewMenuItemId() {
        int highestMenuItemId = 0;
        List<String> menuItemsLines = readLinesFromFile("menu_items.txt");
        for (String line : menuItemsLines) {
            String[] menuItemInfo = line.split("\\|");
            int menuItemId = Integer.parseInt(menuItemInfo[0]);
            if (menuItemId > highestMenuItemId) {
                highestMenuItemId = menuItemId;
            }
        }
        return highestMenuItemId + 1;
    }


    private static void manageMenu(int restaurantId) {
        String restaurantName = getRestaurantNameById(restaurantId);
        if (restaurantName == null) {
            System.out.println("Invalid restaurant ID.");
            return;
        }

        List<String> menuItems = readMenuItems(restaurantName);
        System.out.println("Menu items for " + restaurantName + ":");
        for (String menuItem : menuItems) {
            System.out.println(menuItem.replace("|", "\t")); // replaced pipe with tab here
        }

        System.out.println("Manage Menu:");
        System.out.println("1. Add Item");
        System.out.println("2. Edit Item");
        System.out.println("3. Remove Item");
        System.out.println("4. Go back");

        int choice = getIntInput("Enter the number of your choice: ", 1, 4);

        switch (choice) {
            case 1:
                addItem(restaurantId);
                break;
            case 2:
                editItem(restaurantId);
                break;
            case 3:
                removeItem(restaurantId);
                break;
            case 4:
                break;
            default:
                System.out.println("Invalid option.");
                break;
        }
    }

    
    private static void addItem(int restaurantId) {
        String restaurantName = getRestaurantNameById(restaurantId);
        if (restaurantName == null) {
            System.out.println("Invalid restaurant ID.");
            return;
        }

        boolean continueAdding = true;

        while (continueAdding) {
            System.out.println("Enter the name of the new item (or type '-1' to return to the previous menu):");
            String itemName = scanner.nextLine();

            if (itemName.equals("-1")) {
                return;
            }

            System.out.println("Enter the price of the new item (or type '-1' to return to the previous menu):");
            double itemPrice = scanner.nextDouble();
            scanner.nextLine();

            if (itemPrice == -1) {
                return;
            }

            System.out.println("Enter the description of the new item (or type '-1' to return to the previous menu):");
            String itemDescription = scanner.nextLine();

            if (itemDescription.equals("-1")) {
                return;
            }

            System.out.println("Enter the inventory of the new item (or type '-1' to return to the previous menu):");
            int itemInventory = scanner.nextInt();
            scanner.nextLine();

            if (itemInventory == -1) {
                return;
            }

            int newItemId = generateNewMenuItemId();
            String newMenuItem = String.format("%d|%s|%s|%s|%.2f|%d", newItemId, restaurantName, itemName, itemDescription, itemPrice, itemInventory);
            appendLineToFile("menu_items.txt", newMenuItem);

            System.out.println("New item added successfully!");
            System.out.println("Do you want to add another item? (Y/N)");

            String continueChoice = scanner.nextLine();
            continueAdding = continueChoice.equalsIgnoreCase("Y");
        }
    }


    private static void editItem(int restaurantId) {
        System.out.println("Enter the item ID you want to edit:");
        int itemIdToEdit = scanner.nextInt();
        scanner.nextLine();

        List<String> menuItemsLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("menu_items.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                menuItemsLines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int itemIndexToEdit = -1;

        for (int i = 0; i < menuItemsLines.size(); i++) {
            String[] menuItemInfo = menuItemsLines.get(i).split("\\|");
            int menuItemId = Integer.parseInt(menuItemInfo[0]);
            int menuItemRestaurantId = Integer.parseInt(menuItemInfo[1]);

            if (menuItemId == itemIdToEdit && menuItemRestaurantId == restaurantId) {
                itemIndexToEdit = i;
                break;
            }
        }

        if (itemIndexToEdit == -1) {
            System.out.println("Item not found.");
            return;
        }

        // Ask the user for the new item details and update the item
        System.out.println("Enter new item name:");
        String newItemName = scanner.nextLine();
        System.out.println("Enter new item price:");
        double newItemPrice = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter new item description:");
        String newItemDescription = scanner.nextLine();
        System.out.println("Enter new item inventory:");
        int newItemInventory = scanner.nextInt();
        scanner.nextLine();

        String[] oldItemInfo = menuItemsLines.get(itemIndexToEdit).split("\\|");
        int menuItemId = Integer.parseInt(oldItemInfo[0]);
        String updatedItem = String.format("%d|%d|%s|%s|%.2f|%d", menuItemId, restaurantId, newItemName, newItemDescription, newItemPrice, newItemInventory);

        menuItemsLines.set(itemIndexToEdit, updatedItem);
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("menu_items.txt"))) {
            for (String menuItemLine : menuItemsLines) {
                writer.write(menuItemLine);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Item updated successfully.");
    }

    private static void removeItem(int restaurantId) {
        System.out.println("Enter the item ID you want to remove or enter -1 to go back:");
        int itemId = scanner.nextInt();
        scanner.nextLine();

        if (itemId == -1) {
            return;
        }

        List<String> menuItemsLines = readLinesFromFile("menu_items.txt");
        boolean itemFound = false;

        for (int i = 0; i < menuItemsLines.size(); i++) {
            String[] menuItemInfo = menuItemsLines.get(i).split("\\|");
            int menuItemId = Integer.parseInt(menuItemInfo[0]);
            int menuItemRestaurantId = Integer.parseInt(menuItemInfo[1]);

            if (menuItemId == itemId && menuItemRestaurantId == restaurantId) {
                menuItemsLines.remove(i);
                writeLinesToFile("menu_items.txt", menuItemsLines);
                System.out.println("Item removed successfully.");
                itemFound = true;
                break;
            }
        }

        if (!itemFound) {
            System.out.println("Item not found.");
        }
    }

    
    private static List<String> readLinesFromFile(String filename) {
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line); // removed .replace("|", "\t") from here
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }


    private static void writeLinesToFile(String filename, List<String> lines) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        return input;
    }

    
    private static void manageInventory(int restaurantId) {
        String restaurantName = getRestaurantNameById(restaurantId);
        if (restaurantName == null) {
            System.out.println("Invalid restaurant ID.");
            return;
        }

        System.out.println("Managing inventory for restaurant ID: " + restaurantId);

        while (true) {
            System.out.println("1. Update inventory item");
            System.out.println("2. List inventory items");
            System.out.println("3. Go back");

            int choice = getIntInput("Enter your choice: ");

            List<String> menuItems = getMenuItemsByRestaurantName(restaurantName);

            switch (choice) {
                case 1:
                    String updateItemName = getStringInput("Enter item name to update: ");
                    int updateItemQuantity = getIntInput("Enter new item quantity: ");

                    boolean itemFound = false;
                    for (String menuItem : menuItems) {
                        String[] menuItemInfo = menuItem.split("\\|");
                        if (menuItemInfo[2].equalsIgnoreCase(updateItemName)) { // Change this line
                            menuItemInfo[5] = String.valueOf(updateItemQuantity);
                            String updatedMenuItem = String.join("|", menuItemInfo);
                            updateMenuItem(menuItemInfo[0], updatedMenuItem);
                            itemFound = true;
                            System.out.println("Inventory item updated.");
                            break;
                        }
                    }

                    if (!itemFound) {
                        System.out.println("Item not found in the menu.");
                    }

                    break;
                case 2:
                    System.out.println("Listing inventory items:");
                    for (String menuItem : menuItems) {
                        String[] menuItemInfo = menuItem.split("\\|");
                        System.out.println("Item: " + menuItemInfo[2] + ", Quantity: " + menuItemInfo[5]);
                    }
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }


    private static void trackItemSales(int restaurantId) {
        System.out.println("Tracking item sales for restaurant ID: " + restaurantId);

        List<String> orders = readLinesFromFile("orders.txt");
        System.out.println("Orders: " + orders); // Print out list of orders
        Map<Integer, Integer> itemSales = calculateItemSales(restaurantId);

        List<String> menuItems = getMenuItemsByRestaurantId(restaurantId);
        System.out.println("Item sales:");
        for (String menuItem : menuItems) {
            String[] menuItemInfo = menuItem.split("\\|");
            int itemId = Integer.parseInt(menuItemInfo[0]);
            String itemName = menuItemInfo[2];
            int sales = itemSales.getOrDefault(itemId, 0);
            System.out.println("Item: " + itemName + ", Sales: " + sales);
        }
    }

    private static Map<Integer, Integer> calculateItemSales(int restaurantId) {
        List<String> orders = readLinesFromFile("orders.txt");
        Map<Integer, Integer> itemSales = new HashMap<>();

        for (String order : orders) {
            String[] orderInfo = order.split(",(?=\\s\\w+:)");
            int orderRestaurantId = Integer.parseInt(orderInfo[1].substring("RestaurantID: ".length()).trim());

            if (orderRestaurantId == restaurantId) {
                System.out.println(order); // Print out order information
                int itemId = Integer.parseInt(orderInfo[3].substring("ItemID: ".length()).trim());
                int quantity = Integer.parseInt(orderInfo[4].substring("Quantity: ".length()).trim());
                itemSales.put(itemId, itemSales.getOrDefault(itemId, 0) + quantity);
            }
        }

        return itemSales;
    }




    private static void appOwnerDashboard() {
        while (true) {
            System.out.println("App Owner Dashboard:");
            System.out.println("1. View pending restaurant requests");
            System.out.println("2. View sales");
            System.out.println("3. View all restaurant inventory");
            System.out.println("4. Add restaurant");
            System.out.println("5. Remove restaurant");
            System.out.println("6. Logout");

            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    processPendingRequests();
                    break;
                case 2:
                    viewSales();
                    break;
                case 3:
                    viewAllRestaurantInventory();
                    break;
                case 4:
                    addRestaurant();
                    break;
                case 5:
                    removeRestaurant();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }


    private static void processPendingRequests() {
        List<String> pendingRequests = readLinesFromFile("pending_requests.txt");
        System.out.println("Pending Requests:");
        System.out.println("Name, City, Type, Email, Password");

        boolean validInput = false;
        String restaurantName = "";
        while (!validInput) {
            for (String request : pendingRequests) {
                String[] requestInfo = request.split(",");
                if (requestInfo[5].equalsIgnoreCase("pending")) {
                    System.out.printf("%s, %s, %s, %s, %s%n", requestInfo[0], requestInfo[1], requestInfo[2], requestInfo[3], requestInfo[4]);
                }
            }

            System.out.println("Enter the name of the restaurant you want to process, or type 'exit' to go back:");
            restaurantName = scanner.nextLine();

            if (restaurantName.equalsIgnoreCase("exit")) {
                return;
            }

            boolean nameFound = false;
            for (String request : pendingRequests) {
                String[] requestInfo = request.split(",");
                if (requestInfo[0].equalsIgnoreCase(restaurantName) && requestInfo[5].equalsIgnoreCase("pending")) {
                    nameFound = true;
                    break;
                }
            }

            if (nameFound) {
                validInput = true;
            } else {
                System.out.println("Invalid restaurant name. Please try again.");
            }
        }

        List<String> updatedRequests = new ArrayList<>();
        boolean requestProcessed = false;

        for (String request : pendingRequests) {
            String[] requestInfo = request.split(",");
            if (requestInfo[0].equalsIgnoreCase(restaurantName) && requestInfo[5].equalsIgnoreCase("pending")) {
                requestProcessed = true;
                while (true) {
                    System.out.println("Do you want to accept (A) or deny (D) the request?");
                    String decision = getStringInput("").toUpperCase();

                    if (decision.equals("A")) {
                        int newRestaurantId = generateNewRestaurantId();
                        String newRestaurant = String.format("%d,%s,%s,%s,%s,%s", newRestaurantId, requestInfo[0], requestInfo[1], requestInfo[2], requestInfo[3], requestInfo[4]);
                        appendLineToFile("restaurants.txt", newRestaurant);
                        updatedRequests.add(String.format("%s,%s,%s,%s,%s,accepted", requestInfo[0], requestInfo[1], requestInfo[2], requestInfo[3], requestInfo[4]));
                        break;
                    } else if (decision.equals("D")) {
                        updatedRequests.add(String.format("%s,%s,%s,%s,%s,denied", requestInfo[0], requestInfo[1], requestInfo[2], requestInfo[3], requestInfo[4]));
                        break;
                    } else {
                        System.out.println("Invalid input. Please try again.");
                    }
                }
            } else {
                updatedRequests.add(request);
            }
        }

        if (requestProcessed) {
            writeLinesToFile("pending_requests.txt", updatedRequests);
            System.out.println("Request processed successfully.");
        } else {
            System.out.println("Request not found.");
        }
    }




    private static int generateNewRestaurantId() {
        int highestRestaurantId = 0;
        List<String> restaurantLines = readLinesFromFile("restaurants.txt");
        for (String line : restaurantLines) {
            String[] restaurantInfo = line.split(",");
            int restaurantId = Integer.parseInt(restaurantInfo[0]);
            if (restaurantId > highestRestaurantId) {
                highestRestaurantId = restaurantId;
            }
        }
        return highestRestaurantId + 1;
    }

    private static void viewSales() {
        List<String> orders = readLinesFromFile("orders.txt");
        Map<String, Double> salesPerRestaurant = new LinkedHashMap<>();
        double totalSales = 0;

        for (String order : orders) {
            String[] orderInfo = order.split(",(?=\\s\\w+:)");
            int orderId = Integer.parseInt(orderInfo[0].substring("OrderID: ".length()).trim());
            int restaurantId = getRestaurantIdFromOrderId(String.valueOf(orderId));
            String restaurantName = getRestaurantNameById(restaurantId);

            double orderTotal = 0;
            for (int i = 2; i < orderInfo.length; i++) {
                if (orderInfo[i].startsWith(" ItemID: ")) {
                    int itemId = Integer.parseInt(orderInfo[i].substring(" ItemID: ".length()).trim());
                    int quantity = 0;
                    if (i + 1 < orderInfo.length && orderInfo[i + 1].startsWith(" Quantity: ")) {
                        quantity = Integer.parseInt(orderInfo[i + 1].substring(" Quantity: ".length()).trim());
                    }
                    double itemPrice = getItemPriceById(itemId);
                    orderTotal += itemPrice * quantity;
                }
            }

            totalSales += orderTotal;
            salesPerRestaurant.put(restaurantName, salesPerRestaurant.getOrDefault(restaurantName, 0.0) + orderTotal);
        }

        for (Map.Entry<String, Double> entry : salesPerRestaurant.entrySet()) {
            System.out.printf("%s: Rs. %.2f\n", entry.getKey(), entry.getValue());
        }

        System.out.printf("\nTotal sales: Rs. %.2f\n", totalSales);
    }




    private static double getItemPriceById(int itemId) {
        List<String> menuItems = readLinesFromFile("menu_items.txt");

        for (String menuItem : menuItems) {
            String[] menuItemInfo = menuItem.split("\\|");
            int id = Integer.parseInt(menuItemInfo[0]);
            if (id == itemId) {
                return Double.parseDouble(menuItemInfo[4]);
            }
        }
        return -1;
    }




    private static int getRestaurantIdFromOrderId(String orderId) {
        List<String> orders = readLinesFromFile("orders.txt");
        for (String order : orders) {
            String[] orderInfo = order.split(",");
            if (orderInfo[0].substring("OrderID: ".length()).trim().equals(orderId)) {
                String restaurantIdString = orderInfo[1].substring("RestaurantID: ".length()).trim();
                if (!restaurantIdString.isEmpty()) {
                    return Integer.parseInt(restaurantIdString);
                } else {
                    System.out.println("Error: RestaurantID is missing for OrderID: " + orderId);
                    return -1;
                }
            }
        }
        return -1;
    }

    private static void viewAllRestaurantInventory() {
        List<String> menuItems = readLinesFromFile("menu_items.txt");
        Map<String, String> restaurantNames = new LinkedHashMap<>();

        for (String menuItem : menuItems) {
            String[] menuItemInfo = menuItem.split("\\|");
            String restaurantName = menuItemInfo[1];
            String itemName = menuItemInfo[2];
            int inventory = Integer.parseInt(menuItemInfo[5]);

            if (!restaurantNames.containsKey(restaurantName)) {
                restaurantNames.put(restaurantName, "");
                System.out.println("\n" + restaurantName + ":");
            }

            System.out.printf("%s: Inventory = %d\n", itemName, inventory);
        }
    }



    private static void addRestaurant() {
        int newRestaurantId = generateNewRestaurantId();
        
        System.out.println("Enter restaurant name:");
        String name = getStringInput("");
        System.out.println("Enter restaurant location:");
        String location = getStringInput("");
        System.out.println("Enter restaurant type:");
        String type = getStringInput("");
        System.out.println("Enter restaurant email:");
        String email = getStringInput("");
        System.out.println("Enter restaurant password:");
        String password = getStringInput("");

        String newRestaurant = String.format("%d,%s,%s,%s,%s,%s", newRestaurantId, name, location, type, email, password);
        appendLineToFile("restaurants.txt", newRestaurant);
        System.out.println("Restaurant added successfully.");
    }


    private static void removeRestaurant() {
        System.out.println("Enter the name of the restaurant you want to remove:");
        String restaurantName = getStringInput("");

        List<String> restaurants = readLinesFromFile("restaurants.txt");
        List<String> updatedRestaurants = new ArrayList<>();
        boolean restaurantRemoved = false;
        String removedRestaurantName = null;

        for (String restaurant : restaurants) {
            String[] restaurantInfo = restaurant.split(",");
            if (restaurantInfo.length >= 2 && restaurantInfo[1].equalsIgnoreCase(restaurantName)) {
                restaurantRemoved = true;
                removedRestaurantName = restaurantInfo[1];
            } else {
                updatedRestaurants.add(restaurant);
            }
        }

        if (restaurantRemoved) {
            String content = String.join("\n", updatedRestaurants);
            try (FileWriter fileWriter = new FileWriter("restaurants.txt")) {
                fileWriter.write(content);
            } catch (IOException e) {
                System.err.println("Error while writing to file: " + e.getMessage());
            }
            System.out.println("Restaurant removed successfully.");

            // Remove menu items associated with the removed restaurant
            List<String> menuItems = readLinesFromFile("menu_items.txt");
            List<String> updatedMenuItems = new ArrayList<>();

            for (String menuItem : menuItems) {
                String[] menuItemInfo = menuItem.split("\\|");
                if (menuItemInfo.length >= 2 && !menuItemInfo[1].equalsIgnoreCase(removedRestaurantName)) {
                    updatedMenuItems.add(menuItem);
                }
            }

            content = String.join("\n", updatedMenuItems);
            try (FileWriter fileWriter = new FileWriter("menu_items.txt")) {
                fileWriter.write(content);
            } catch (IOException e) {
                System.err.println("Error while writing to file: " + e.getMessage());
            }
            System.out.println("Menu items for the removed restaurant have also been removed.");
        } else {
            System.out.println("Restaurant not found.");
        }
    }




    
}
