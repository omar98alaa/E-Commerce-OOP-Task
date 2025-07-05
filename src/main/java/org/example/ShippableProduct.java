package org.example;

import java.util.Date;

public class ShippableProduct extends Product implements IShippable {
  private double weight;

  public ShippableProduct(String name, double price, int quantity, Date expirationDate, double weight) {
    super(name, price, quantity, expirationDate);
    this.weight = weight;
  }

  @Override
  public double getWeight() {
    return weight;
  }
}
