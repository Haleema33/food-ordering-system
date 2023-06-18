public class MenuItem {
    private int itemId;
    private int restaurantId;
    private String itemName;
    private String itemDescription;
    private double itemPrice;
    private int itemPreparationTime;

    // Constructor
    public MenuItem(int itemId, int restaurantId, String itemName, String itemDescription, double itemPrice, int itemPreparationTime) {
        this.itemId = itemId;
        this.restaurantId = restaurantId;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.itemPreparationTime = itemPreparationTime;
    }

    // Getters
    public int getItemId() {
        return itemId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public int getItemPreparationTime() {
        return itemPreparationTime;
    }

    // Setters
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setItemPreparationTime(int itemPreparationTime) {
        this.itemPreparationTime = itemPreparationTime;
    }

    // Static method to parse menu item from string
    public static MenuItem parseMenuItem(String menuItemLine) {
        String[] menuItemInfo = menuItemLine.split("\\|");
        int itemId = Integer.parseInt(menuItemInfo[0]);
        int restaurantId = Integer.parseInt(menuItemInfo[1]);
        String itemName = menuItemInfo[2];
        String itemDescription = menuItemInfo[3];
        double itemPrice = Double.parseDouble(menuItemInfo[4]);
        int itemPreparationTime = Integer.parseInt(menuItemInfo[5]);

        return new MenuItem(itemId, restaurantId, itemName, itemDescription, itemPrice, itemPreparationTime);
    }
}