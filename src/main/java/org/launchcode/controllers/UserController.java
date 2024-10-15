package org.launchcode.controllers;

import org.launchcode.data.UserData;
import org.launchcode.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("user")
public class UserController {

    @GetMapping("add")
    public String displayAddUserForm() {
        return "user/add";
    }

    @PostMapping
    public String processAddUserForm(Model model, @ModelAttribute User user, String verify) {

        if (verify.equals(user.getPassword())) {
            UserData.add(user);
            Collection<User> users = UserData.getAll();
            model.addAttribute("users", users);
            return "user/index";
        } else {
            model.addAttribute("username", user.getUsername());
            model.addAttribute("email", user.getEmail());
            model.addAttribute("error", "Passwords must match");
            return "user/add";
        }
    }

    @GetMapping("detail/{userId}")
    public String displayUserDetails(Model model, @PathVariable int userId) {
        model.addAttribute("user", UserData.getById(userId));
        return "user/detail";
    }

}
