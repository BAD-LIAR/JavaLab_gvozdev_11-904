package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.dto.UserForm;
import ru.itis.javalab.services.UsersService;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;






//    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
//    public String getSignUp(Model model) {
//        return "signUp";
//    }
//
//    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
//    public String signUp(UserDto user) {
//        usersService.addUser(user);
//        return "redirect:/users";
//    }

    @GetMapping("/signUp")
    public String getSignUpPage(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUp(@Valid UserForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().stream().anyMatch(error -> {
                if (Objects.requireNonNull(error.getCodes())[0].equals("userForm.ValidNames")) {
                    model.addAttribute("namesErrorMessage", error.getDefaultMessage());
                }
                return true;
            });
            model.addAttribute("userForm", form);
            return "signUp";
        }
        usersService.addUser(form);
        return "redirect:/success";
    }




    @GetMapping("/signIn")
    public String getSignInPage() {
        return "login";
    }



//    @PostMapping(value = "/signIn")
//    public ModelAndView signIn(HttpServletRequest request, HttpServletResponse response) {
//        HttpSession session = request.getSession();
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//        if (usersService.containsUser(email, password)) {
//            session.setAttribute("email", email);
//            return new ModelAndView("redirect:/users");
//        } else {
//            return new ModelAndView("login");
//        }
//    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users")
    public String getUsersPage(Model model) {
        model.addAttribute("usersList", usersService.getAllUsers());
        return "users_page";
    }


}
