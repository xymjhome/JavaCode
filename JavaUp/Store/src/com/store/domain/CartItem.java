package com.store.domain;

import java.io.Serializable;

public class CartItem implements Serializable{
    private Product product;
    private int count = 0;
    private double subtotal = 0.0;



    public CartItem() {
    }

    public CartItem(Product product, int count) {
        this.product = product;
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getSubtotal() {
        return product.getShop_price()*count;
    }
}
