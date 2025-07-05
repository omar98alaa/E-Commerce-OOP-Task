package org.example;

import java.util.HashMap;

public final class ShippingService {
  private ShippingService() {
    // Private constructor to prevent instantiation
  }

  public static double calculateShippingCost(HashMap<IShippable, Integer> products) {
    if (products == null || products.isEmpty()) {
      return 0.0;
    }

    double totalWeight = 0.0;
    for (var entry : products.entrySet()) {
      IShippable product = entry.getKey();
      int quantity = entry.getValue();
      double weight = product.getWeight() * quantity;
      totalWeight += weight;
    }

    // Assuming a simple shipping cost calculation based on total weight
    return totalWeight * 5.0;
  }

  public static void processShippment(HashMap<IShippable, Integer> products) {
    System.out.println("\n**\t\tShipment notice\t\t**");

    if (products == null || products.isEmpty()) {
      System.out.println("No products to ship.");
      return;
    }

    double totalWeight = 0.0;

    for (var entry : products.entrySet()) {
      IShippable product = entry.getKey();
      int quantity = entry.getValue();
      totalWeight += product.getWeight() * quantity;
      System.out.printf("%-30s %9.2fKg%n", quantity + "x " + product.getName(), product.getWeight() * quantity);
    }

    System.out.println("------------------------------------------");
    System.out.printf("%-30s %9.2fKg%n", "Total Package Weight:", totalWeight);
  }
}
