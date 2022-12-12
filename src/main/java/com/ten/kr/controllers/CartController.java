package com.ten.kr.controllers;

import com.ten.kr.entity.Cart;
import com.ten.kr.entity.Order;
import com.ten.kr.entity.Product;
import com.ten.kr.entity.User;
import com.ten.kr.repositories.CartRepository;
import com.ten.kr.repositories.ProductRepository;
import com.ten.kr.services.CartService;
import com.ten.kr.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;
    @GetMapping("/cart")
    public String moveToCart(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("new_user", new User());
        User user = (User) auth.getPrincipal();
        List<Cart> cartList = cartService.listCartItems(user);
        int total = cartList.stream().mapToInt(Cart::getQuantity).sum();
        model.addAttribute("cartList", cartList);
        model.addAttribute("current_user",user);
        model.addAttribute("new_order", new Order());
        return "shop/cart";
    }

    @PostMapping("/cart/{id}/{qty}")
    public @ResponseBody String addToCart(@PathVariable("id") Long id,@PathVariable("qty") int quantity){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        Integer addedQuantity = cartService.addProduct(id, quantity, user);

        return addedQuantity + "было успешно добавлено";
    }

    @PostMapping("/cart/update/{id}/{qty}")
    public @ResponseBody String updateCart(@PathVariable("id") Long id,@PathVariable("qty") int quantity){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        double subTotal = cartService.updateQuantity(id, quantity, user);
        return String.valueOf(subTotal);
    }

    @PostMapping("/cart/remove/{id}")
    public String removeFromCart(@PathVariable("id") Long id){
        System.out.println("hello");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        cartService.removeProduct(id,user);
        return "redirect:/cart";
    }
    @PostMapping("/cart/createOrder")
    public String createOrder(@ModelAttribute("new_order") Order newOrder){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        orderService.createOrder(newOrder, user, cartService.listCartItems(user));
        return "redirect:/cart";
    }

}
