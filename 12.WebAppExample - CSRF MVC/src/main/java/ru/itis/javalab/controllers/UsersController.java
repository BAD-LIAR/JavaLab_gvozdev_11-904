package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.models.User;
import ru.itis.javalab.services.UsersService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 19.11.2020
 * 14. Web MVC Extended
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

//    @RequestMapping(value = "/users", method = RequestMethod.GET)
//    public ModelAndView getUsersPage(HttpServletRequest request, HttpServletResponse response) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("users_view");
//        modelAndView.addObject("users", usersService.getAllUsers());
//        System.out.println("request - " + request + " , response - " + response);
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/users", method = RequestMethod.POST)
//    public ModelAndView addUser(UserDto user) {
//        usersService.addUser(user);
//        return new ModelAndView("redirect:/users");
//    }

    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public String getSignUp(Model model) {
        return "signUp";
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public String signUp(UserDto user) {
        usersService.addUser(user);
        return "redirect:/users";
    }


    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    public String getSignIn(Model model) {
        return "signIn";
    }

//    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
//    public String signIn(UserDto user) {
//        if(usersService.containsUser(user.getEmail(), user.getPassword())){
//
//        }
//
//        return "redirect:/profile";
//    }


    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public ModelAndView signIn(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (usersService.containsUser(email, password)) {
            session.setAttribute("email", email);
            return new ModelAndView("redirect:/users");
        } else {
            return new ModelAndView("redirect:/signIn");
        }
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUsersPage(@RequestParam(value = "page", required = false) Integer page,
                               @RequestParam(value = "size", required = false) Integer size,
                               Model model) {
        if (page != null && size != null) {
            model.addAttribute("users", usersService.getAllUsers(page, size));
        } else {
            model.addAttribute("users", usersService.getAllUsers());
        }
        return "users_view";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String addUser(UserDto user) {
        usersService.addUser(user);
        return "redirect:/users";
    }

    @RequestMapping(value = "/users/{user-id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UserDto getUser(@PathVariable("user-id") Long userId) {
        return usersService.getUser(userId);
    }

    @RequestMapping(value = "/users/json", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<UserDto> addUserFromJson(@RequestBody UserDto user) {
        usersService.addUser(user);
        return usersService.getAllUsers();
    }
}
