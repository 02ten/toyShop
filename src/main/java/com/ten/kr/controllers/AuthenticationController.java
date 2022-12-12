package com.ten.kr.controllers;

import com.ten.kr.entity.User;
import com.ten.kr.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthenticationController {
    @Autowired
    private UserService userService;
    @PostMapping("/registration")
    public String addUser(@ModelAttribute("new_user") @Valid User userForm, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()) {
            return "shop/index";
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "shop/index";
        }
        if (!userService.saveUser(userForm)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "shop/index";
        }

        return "redirect:/index";
    }
}
