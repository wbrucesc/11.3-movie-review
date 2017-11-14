package com.will.moviereview.controllers;

import com.will.moviereview.models.Role;
import com.will.moviereview.models.User;
import com.will.moviereview.repositories.RoleRepo;
import com.will.moviereview.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleRepo roleRepo;

    @RequestMapping(name = "/signup", method = RequestMethod.GET)
    public String signupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @RequestMapping(name = "/signup", method = RequestMethod.POST)
    public String signupForm(@ModelAttribute User user) {

        Role userRole = roleRepo.findByName("ROLE_USER");
        String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        user.setRole(userRole);
        user.setActive(true);
        userRepo.save(user);
        return "redirect:/login";
    }
}
