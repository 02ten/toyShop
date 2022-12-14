package com.ten.kr.controllers;

import com.ten.kr.entity.Product;
import com.ten.kr.entity.User;
import com.ten.kr.services.OrderService;
import com.ten.kr.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminPanelController {
    @Autowired
    ProductService productService;
    @Autowired
    OrderService orderService;
    @GetMapping("/admin/orders")
    public String moveToOrdersPanel(Model model) {
        model.addAttribute ("orders", orderService.getAllOrders());
        return "AdminPanel/Orders";
    }

    @GetMapping("/admin/products")
    public String moveToProductsPanel(Model model){
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("new_product", new Product());
        return "AdminPanel/Products";
    }

    @PostMapping("/admin/newProduct")
    public String addProduct(@ModelAttribute("new_user") Product newProduct){
        productService.saveProduct(newProduct);
        return "redirect:/admin/products";
    }

    @PostMapping("/admin/removeProduct/{id}")
    public String removeProduct(@PathVariable Long id){
        productService.removeProduct(id);
        return "redirect:/admin/products";
    }

    @PostMapping("/admin/removeOrder/{id}")
    public String removeOrder(@PathVariable Long id){
        orderService.removeOrder(id);
        return "redirect:/admin/orders";
    }


}
