package com.university.controller;

import com.university.dao.UserDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        boolean isValid = UserDAO.validate(email, password);

        if (isValid) {
            request.getSession().setAttribute("user", email);
            return "redirect:/courses";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }
    }
}
