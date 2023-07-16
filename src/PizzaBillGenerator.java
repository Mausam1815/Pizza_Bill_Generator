import java.util.*;

public class PizzaBillGenerator {
    public static void main(String[] args) {
        // Creating instances of Pizza with different names and prices
        Pizza corns = new Pizza("Crazy Corns", 79);
        Pizza margherita = new Pizza("Margherita", 99);
        Pizza farmHouse = new Pizza("Farm House", 149);
        Pizza paneer = new Pizza("Paneer Pizza", 149);
        Pizza special = new Pizza("Special Pizza", 299);

        Order order = new Order(); // Creating an Order object to track the pizzas ordered

        System.out.println("Welcome to Sam's Pizza Shop!");
        System.out.println("What would you like to order?");
        System.out.println("Here are the available pizzas:");
        printMenu(corns, margherita, farmHouse, paneer, special); // Printing the menu options

        Scanner sc = new Scanner(System.in);
        int pizzaChoice;
        int pizzaQuantity;
        do {
            System.out.println("Enter the S.No of the pizza you want to order (enter 0 to finish): ");
            pizzaChoice = sc.nextInt();
            if (pizzaChoice != 0) {
                System.out.println("Enter the quantity: ");
                pizzaQuantity = sc.nextInt();
                if (pizzaQuantity > 0) {
                    // Adding the selected pizza to the order based on the choice
                    switch (pizzaChoice) {
                        case 1 -> order.addPizza(corns, pizzaQuantity);
                        case 2 -> order.addPizza(margherita, pizzaQuantity);
                        case 3 -> order.addPizza(farmHouse, pizzaQuantity);
                        case 4 -> order.addPizza(paneer, pizzaQuantity);
                        case 5 -> order.addPizza(special, pizzaQuantity);
                        default -> System.out.println("Invalid choice. Please enter a valid option.");
                    }
                } else {
                    System.out.println("Invalid quantity. Please enter a positive value.");
                }
            }
        } while (pizzaChoice != 0);

        double totalBill = order.calculateBill(); // Calculating the total bill
        System.out.println("Pizza Bill");
        System.out.println("============================");
        printPizzaPrices(order.getPizza()); // Printing the ordered pizzas and their prices

        System.out.println("Total: Rs. " + totalBill);
        System.out.println("============================");
        System.out.println("Do you want to remove any pizza or remove quantity?");
        System.out.println("Enter y/n: ");
        String wantToRemove = sc.next();
        if (wantToRemove.equals("y") || wantToRemove.equals("yes") || wantToRemove.equals("Yes")) {
            do {
                System.out.println("Enter the no. of pizza you want to remove (enter 0 to skip):");
                printMenu(corns, margherita, farmHouse, paneer, special); // Printing the menu options
                pizzaChoice = sc.nextInt();
                if (pizzaChoice != 0) {
                    System.out.println("Enter the quantity to remove: ");
                    pizzaQuantity = sc.nextInt();
                    // Removing the selected pizza from the order based on the choice
                    switch (pizzaChoice) {
                        case 1 -> order.removePizza(corns, pizzaQuantity);
                        case 2 -> order.removePizza(margherita, pizzaQuantity);
                        case 3 -> order.removePizza(farmHouse, pizzaQuantity);
                        case 4 -> order.removePizza(paneer, pizzaQuantity);
                        case 5 -> order.removePizza(special, pizzaQuantity);
                        default -> {
                            System.out.println("Invalid choice. Skipping removal process.");
                            System.out.println("Thank you! Enjoy your pizza.. :D");
                        }
                    }
                } else {
                    break;
                }
            } while (wantToRemove.equals("y") || wantToRemove.equals("yes") || wantToRemove.equals("Yes"));
        } else {
            System.out.println("Thank you! Enjoy your pizza.. :D");
        }

        totalBill = order.calculateBill(); // Calculating the final bill
        System.out.println("Final pizza bill");
        System.out.println("============================");
        printPizzaPrices(order.getPizza()); // Printing the final ordered pizzas and their prices
        System.out.println("Total: Rs. " + totalBill);
        sc.close();
    }

    private static void printPizzaPrices(List<Pizza> pizzas) {
        for (Pizza pizza : pizzas) {
            System.out.println(pizza.getName() + " - Rs. " + pizza.getPrice());
        }
    }

    public static void printMenu(Pizza corns, Pizza margherita, Pizza farmHouse, Pizza paneer, Pizza special) {
        // Printing the menu options with pizza names and prices
        System.out.println("1. Crazy Corns - Rs. " + corns.getPrice());
        System.out.println("2. Margherita - Rs. " + margherita.getPrice());
        System.out.println("3. Farm House - Rs. " + farmHouse.getPrice());
        System.out.println("4. Paneer - Rs. " + paneer.getPrice());
        System.out.println("5. Special - Rs. " + special.getPrice());
    }
}

class Pizza {
    private String name;
    private double price;

    public Pizza(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Method to get the name of the pizza
    public String getName() {
        return name;
    }

    // Method to get the price of the pizza
    public double getPrice() {
        return price;
    }
}

class Order {
    private Map<Pizza, Integer> pizzaQuantities;

    public Order() {
        this.pizzaQuantities = new HashMap<>();
    }

    public void addPizza(Pizza pizza, int quantity) {
        // Adding the pizza and its quantity to the order
        pizzaQuantities.put(pizza, pizzaQuantities.getOrDefault(pizza, 0) + quantity);
    }

    public void removePizza(Pizza pizza, int quantity) {
        int currQuantity = pizzaQuantities.getOrDefault(pizza, 0);
        if (currQuantity > 0) {
            int newQuantity = currQuantity - quantity;
            if (newQuantity <= 0) {
                pizzaQuantities.remove(pizza);
            } else {
                pizzaQuantities.put(pizza, newQuantity);
            }
        }
    }

    public double calculateBill() {
        double totalAmount = 0;
        for (Map.Entry<Pizza, Integer> entry : pizzaQuantities.entrySet()) {
            Pizza pizza = entry.getKey();
            int quantity = entry.getValue();
            totalAmount += pizza.getPrice() * quantity;
        }
        return totalAmount;
    }

    public List<Pizza> getPizza() {
        List<Pizza> pizzas = new ArrayList<>();
        for (Map.Entry<Pizza, Integer> entry : pizzaQuantities.entrySet()) {
            Pizza pizza = entry.getKey();
            int quantity = entry.getValue();
            for (int i = 0; i < quantity; i++) {
                pizzas.add(pizza);
            }
        }
        return pizzas;
    }
}
