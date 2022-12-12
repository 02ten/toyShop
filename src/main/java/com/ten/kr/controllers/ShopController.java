package com.ten.kr.controllers;

import com.ten.kr.entity.Cart;
import com.ten.kr.entity.User;
import com.ten.kr.services.ProductService;
import com.ten.kr.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ShopController {

    @Autowired
    private ProductService productService;
    @GetMapping("/")
    public String getHome(Model model){
        model.addAttribute("new_user", new User());
        return "shop/index";
    }

    @GetMapping("/catalog")
    public String getCatalog(Model model){
        model.addAttribute("new_user", new User());
        model.addAttribute("products", productService.getAllProducts());
        return "shop/catalog";
    }
    @GetMapping("/about")
    public String getAbout(Model model){
        model.addAttribute("new_user", new User());
        model.addAttribute("products", productService.getAllProducts());
        return "shop/about";
    }



}
