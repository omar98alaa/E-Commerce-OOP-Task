package org.example;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // Customers
        Customer customer1 = new Customer("John Doe", 1100.0);
        Customer customer2 = new Customer("Jane Doe", 100.0);

        Date expiryDate = new Date(System.currentTimeMillis() + 30L * 24 * 60 * 60 * 1000); // 30 days from now
        Date expiredDate = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1100); // expired

        // Sample products
        IProduct scratchCard = new Product("Scratch Card", 2.0, 10, null); // Non-shippable, Non-expiring
        IProduct tv = new ShippableProduct("Smart TV", 1000.0, 1, null, 5.0); // Shippable, Non-expiring
        IProduct cheese = new ShippableProduct("Cheese", 5.0, 50, expiryDate, 1.5); // shippable, expiring
        IProduct milk = new ShippableProduct("Milk", 10.0, 50, expiredDate, 1.1); // shippable, expired

        // Scenario 1: Normal Checkout with multiple products
        System.out.println("\n**\tScenario 1: Normal Checkout with multiple products\t**\n");

        customer1.getCart().addItem(scratchCard, 5);
        customer1.getCart().addItem(tv, 1);
        customer1.getCart().addItem(cheese, 2);

        System.out.println("Customer 1 Balance before checkout: " + customer1.getBalance() + "$");
        CheckoutService.checkout(customer1);

        // Scenario 2: Expired Product in cart
        System.out.println("\n**\tScenario 2: Expired Product in cart\t**\n");

        customer2.getCart().addItem(scratchCard, 2);
        customer2.getCart().addItem(tv, 1);
        customer2.getCart().addItem(milk, 3); // expired product

        System.out.println("\nCustomer 2 Balance before checkout: " + customer2.getBalance() + "$");
        CheckoutService.checkout(customer2);
        customer2.getCart().clear();

        // Scenario 3: Product in cart became out of stock
        System.out.println("\n**\tScenario 3: Product in cart became out of stock\t**\n");

        System.out.println("Scratch Card current stock: " + scratchCard.getQuantity());

        System.out.println("Customer1:");
        customer1.getCart().addItem(scratchCard, 3);

        System.out.println("Customer2:");
        customer2.getCart().addItem(scratchCard, 5);

        CheckoutService.checkout(customer2); // should succeed

        CheckoutService.checkout(customer1); // should fail due to out of stock
        customer1.getCart().clear();

        // Scenario 4: Insufficient balance
        System.out.println("\n**\tScenario 4: Insufficient balance\t**\n");

        customer1.getCart().addItem(cheese, 20);

        CheckoutService.checkout(customer1);
        customer1.getCart().clear();
    }
}
