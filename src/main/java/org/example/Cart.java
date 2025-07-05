package org.example;

import java.util.HashMap;

public class Cart {
  private HashMap<IProduct, Integer> items;

  public Cart() {
    this.items = new HashMap<>();
  }

  public HashMap<IProduct, Integer> getItems() {
    return items;
  }

  public void addItem(IProduct product, int quantity) {
    if (product == null || quantity <= 0) {
      return;
    }

    if (product.getQuantity() < quantity) {
      System.out.println("Not enough stock for " + product.getName());
      return;
    }

    items.put(product, items.getOrDefault(product, 0) + quantity);

    System.out.println("Added " + quantity + "x " + product.getName() + " to cart.");
  }

  public void updateItemQuantity(IProduct product, int delta) {
    if (product == null || !items.containsKey(product)) {
      return;
    }

    int currentQuantity = items.get(product);
    int newQuantity = currentQuantity + delta;

    if (product.getQuantity() < newQuantity) {
      System.out.println("Not enough stock for " + product.getName());
      return;
    }

    if (newQuantity <= 0) {
      items.remove(product);
    } else {
      items.put(product, newQuantity);
    }

    System.out
        .println(
            "Updated " + product.getName() + " cart quantity from " + currentQuantity + " to " + newQuantity + ".");
  }

  public void removeItem(IProduct product) {
    if (product == null || !items.containsKey(product)) {
      return;
    }

    int quantity = items.remove(product);

    System.out.println("Removed " + quantity + "x " + product.getName() + " from cart.");
  }

  public void clear() {
    items.clear();
    System.out.println("Cart cleared.");
  }

  public boolean isEmpty() {
    return items.isEmpty();
  }

  public boolean validate() {
    if (items.isEmpty()) {
      System.out.println("Cart is empty.");
      return false;
    }

    for (HashMap.Entry<IProduct, Integer> entry : items.entrySet()) {
      IProduct product = entry.getKey();
      int quantity = entry.getValue();

      if (product.isExpired()) {
        System.out.println("Product " + product.getName() + " is expired.");
        return false;
      }

      if (product.getQuantity() < quantity) {
        System.out.println("Not enough stock for " + product.getName());
        return false;
      }
    }

    return true;
  }

  public double calculateTotal() {
    double total = 0.0;

    for (HashMap.Entry<IProduct, Integer> entry : items.entrySet()) {
      IProduct product = entry.getKey();
      int quantity = entry.getValue();
      total += product.getPrice() * quantity;
    }

    return total;
  }

  public HashMap<IShippable, Integer> getShippableProducts() {
    HashMap<IShippable, Integer> shippableProducts = new HashMap<>();

    for (HashMap.Entry<IProduct, Integer> entry : items.entrySet()) {
      IProduct product = entry.getKey();
      int quantity = entry.getValue();

      if (product instanceof IShippable) {
        shippableProducts.put((IShippable) product, quantity);
      }
    }

    return shippableProducts;
  }
}
