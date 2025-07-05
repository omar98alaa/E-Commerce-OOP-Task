package org.example;

public interface IProduct {
    String getName();

    double getPrice();

    int getQuantity();

    int updateQuantity(int amount);

    boolean isExpired();
}
