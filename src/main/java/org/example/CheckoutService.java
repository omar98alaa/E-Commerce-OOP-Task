package org.example;

import java.util.HashMap;

public final class CheckoutService {

  private CheckoutService() {
    // Private constructor to prevent instantiation
  }

  public static boolean checkout(Customer customer) {
    if(customer == null){
      return false;
    }

    System.out.println("\n**\t\tCheckout Customer" + customer.getId() + "\t\t**");

    Cart cart = customer.getCart();

    // Check for expired products and out of stock items
    if (!cart.validate()) {
      return false;
    }

    // Calculate subtotal
    double subTotal = cart.calculateTotal();

    // Calculate shipping cost
    HashMap<IShippable, Integer> shippableProducts = cart.getShippableProducts();
    double shippingCost = ShippingService.calculateShippingCost(shippableProducts);

    // Total cost
    double total = subTotal + shippingCost;

    // Process payment
    if (!customer.updateBalance(-total)) {
      System.out.println("Insufficient balance for checkout.");
      return false;
    }

    // update inventory
    for (var entry : cart.getItems().entrySet()) {
      IProduct product = entry.getKey();
      int quantity = entry.getValue();
      product.updateQuantity(-quantity);
    }

    // Process shipment
    ShippingService.processShippment(shippableProducts);

    // Print invoice
    System.out.println("\n**\t\tCheckout Receipt\t\t**");

    for (var entry : cart.getItems().entrySet()) {
      IProduct product = entry.getKey();
      int quantity = entry.getValue();
      System.out.printf("%-30s %10.2f$%n", quantity + "x " + product.getName(), product.getPrice() * quantity);
    }

    System.out.println("------------------------------------------");
    System.out.printf("%-30s %10.2f$%n", "Subtotal:", subTotal);
    System.out.printf("%-30s %10.2f$%n", "Shipping Cost:", shippingCost);
    System.out.printf("%-30s %10.2f$%n%n", "Amount:", total);

    // Print customer balance
    System.out.println("Customer" + customer.getId() + " Current Balance: " + customer.getBalance() + "$");

    // Clear cart
    cart.clear();

    System.out.println("Checkout completed successfully.");

    return true;
  }
}
