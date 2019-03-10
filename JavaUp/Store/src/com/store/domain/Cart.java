package com.store.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Cart implements Serializable{

    private Map<String,CartItem> map = new LinkedHashMap<>();
    private double totalPrice;

    public Map<String, CartItem> getMap() {
        return map;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    //构建getItmes方法，便于前端直接用items这个beans属性进行取值
    public  Collection<CartItem> getItems(){
        Collection<CartItem> values = map.values();
        return values;
    }
    // 方法：将购物项添加到购物车
    public void add2Cart(CartItem cartItem) {
        String id = cartItem.getProduct().getPid();
        if (map.containsKey(id)) {
            CartItem item = map.get(id);
            item.setCount(item.getCount() + cartItem.getCount());
        } else {
            map.put(id, cartItem);
        }

        totalPrice += cartItem.getSubtotal();
    }

    // 方法：从购物车中移除购物项
    public void removeFromCart(String id) {
        CartItem remove = map.remove(id);
        if (remove != null) {
            totalPrice -= remove.getSubtotal();
        }
    }

    // 方法：清空购物车
    public void clearCart(){ //
        //  将map集合清空.
        map.clear();
        // 将总结设置为0.
        totalPrice = 0;
    }



}
