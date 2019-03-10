package com.store.servlet;

import com.store.domain.Cart;
import com.store.domain.CartItem;
import com.store.domain.Product;
import com.store.service.ProductService;
import com.store.utils.BeanFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CartServlet",urlPatterns = "/cart")
public class CartServlet extends BaseServlet {

    private Cart getCart(HttpServletRequest request){
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            request.getSession().setAttribute("cart",cart);
        }
        return cart;
    }

    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("enter cartServlet add function");
        String pid = request.getParameter("pid");
        System.out.println("enter cartServlet add function pid:" + pid);
        int count = Integer.parseInt(request.getParameter("count"));
        ProductService productService = (ProductService)BeanFactory.getBean("ProductService");
        try {
            Product product = productService.getProductById(pid);
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setCount(count);
            Cart cart = getCart(request);
            cart.add2Cart(cartItem);
            System.out.println("enter cart size :" + cart.getItems().size());
            //resp.sendRedirect(req.getContextPath()+"/jsp/cart.jsp");
            response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return null;
    }

    public String remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pid = request.getParameter("pid");

        Cart cart = getCart(request);
        cart.removeFromCart(pid);

        response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
        return null;
    }

    public String clearCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cart cart = getCart(request);
       cart.clearCart();

        response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
        return null;
    }

}
