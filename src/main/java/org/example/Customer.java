package org.example;

public class Customer {
  private static int nextId = 1;

  private int id;
  private String name;
  private double balance;
  private Cart cart;

  public Customer(String name, double balance) {
    this.id = nextId++;
    this.name = name;
    this.balance = balance;
    this.cart = new Cart();
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public double getBalance() {
    return balance;
  }

  public boolean updateBalance(double amount) {
    if (amount < 0 && Math.abs(amount) > this.balance) {
      return false;
    }

    this.balance += amount;
    return true;
  }

  public Cart getCart() {
    return cart;
  }
}
