package org.example;

import java.util.Date;

public class Product implements IProduct {
    protected String name;
    protected double price;
    protected int quantity;
    protected Date expirationDate;

    public Product(String name, double price, int quantity, Date expirationDate) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public int updateQuantity(int amount) {
        if (amount < 0 && Math.abs(amount) > this.quantity) {
            return 0;
        }

        this.quantity += amount;
        return amount;
    }

    @Override
    public boolean isExpired() {
        if (expirationDate == null) {
            return false; // No expiration date set, not expired
        }
        return new Date().after(expirationDate);
    }

    @Override
    public boolean requiresShipping() {
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof IProduct))
            return false;
        IProduct other = (IProduct) obj;
        return name.equals(other.getName());
    }
}
