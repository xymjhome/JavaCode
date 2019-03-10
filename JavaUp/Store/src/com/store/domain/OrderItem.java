package com.store.domain;

import java.io.Serializable;

/**
 * `itemid` varchar(32) NOT NULL,
 `count` int(11) DEFAULT NULL,
 `subtotal` double DEFAULT NULL,
 `pid` varchar(32) DEFAULT NULL,
 `oid` varchar(32) DEFAULT NULL,
 */
public class OrderItem implements Serializable {
    private String itemid;
    private int count;
    private Double subtotal;
    private Product product;
    private Order order;

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
