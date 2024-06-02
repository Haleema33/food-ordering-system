
## Food Ordering System CLI

### Project Description
The Food Ordering System CLI is a command-line interface application written in Java. It is designed to streamline the process of ordering food from a restaurant. This project provides an intuitive and efficient way for users to browse menus, place orders, and manage their account details directly from the terminal.

### Features
- **User Authentication:**
  - User registration and login.
  - Password protection and encryption.
  - User session management.

- **Menu Browsing:**
  - Display a categorized list of available dishes.
  - Search for dishes by name or category.
  - View detailed information about each dish, including ingredients and price.

- **Order Management:**
  - Add items to the cart.
  - Remove items from the cart.
  - View current cart contents and total price.
  - Place an order with confirmation and summary.

- **Order History:**
  - View past orders and order details.
  - Reorder from previous orders.

- **Account Management:**
  - Update user information such as name, address, and payment details.
  - Change password.

- **Admin Features:**
  - Manage menu items (add, update, delete).
  - View all user accounts and their order histories.
  - Generate sales reports.

### Technologies Used
- **Programming Language:** Java
- **Data Storage:** SQLite for database management.
- **Libraries and Tools:**
  - JDBC for database connectivity.
  - JUnit for testing.
  - Apache Commons for utility functions.
  - BCrypt for password hashing.

### Installation and Setup
1. **Clone the Repository:**
   ```bash
   git clone https://github.com/yourusername/food-ordering-system-cli.git
   cd food-ordering-system-cli
   ```

2. **Install Dependencies:**
   Ensure you have Java and SQLite installed on your system.

3. **Compile the Code:**
   Use the following command to compile the project:
   ```bash
   javac -cp ".:path/to/sqlite-jdbc.jar" com/yourcompany/foodorderingsystem/*.java
   ```

4. **Initialize the Database:**
   Run the setup script to initialize the database:
   ```bash
   java -cp ".:path/to/sqlite-jdbc.jar" com.yourcompany.foodorderingsystem.Setup
   ```

5. **Run the Application:**
   Start the application using the following command:
   ```bash
   java -cp ".:path/to/sqlite-jdbc.jar" com.yourcompany.foodorderingsystem.Main
   ```

### Usage
- **User Registration and Login:**
  - Run the application and follow the prompts to register or log in.

- **Browsing and Ordering:**
  - Use the provided commands to browse the menu, add items to your cart, and place orders.

- **Admin Panel:**
  - Admin users can access management features after logging in with admin credentials.

### Contributing
Contributions are welcome! Please fork the repository and create a pull request with your changes. Ensure your code follows the project's style guidelines and includes appropriate tests.

### License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

### Contact
For questions, suggestions, or issues, please open an issue on GitHub or contact at haleemamalik589@gmail.com.
