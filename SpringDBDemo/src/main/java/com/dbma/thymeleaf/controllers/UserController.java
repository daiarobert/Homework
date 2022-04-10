package com.dbma.thymeleaf.controllers;


import com.dbma.thymeleaf.datalayer.DataBaseManager;
import com.dbma.thymeleaf.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    public DataBaseManager db;

    @GetMapping
    public ModelAndView getCreateUserForm() {
        ModelAndView modelAndView = new ModelAndView("create-user");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView addCustomer(@ModelAttribute User user) {
        ModelAndView modelAndView = new ModelAndView("user");
        db.createUser(user);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @GetMapping("/get/{id}")
    public ModelAndView getById(@PathVariable String id) {
        User user = db.getUserById(id);
        ModelAndView modelAndView = new ModelAndView("user");
        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @GetMapping("/get/firstName")
    public ModelAndView getByFirstName(@RequestParam String firstName) {
        List<User> user = db.searchUserByFirstName(firstName);
        ModelAndView modelAndView = new ModelAndView("user");
        modelAndView.addObject("user", user);

        return modelAndView;
    }
}
