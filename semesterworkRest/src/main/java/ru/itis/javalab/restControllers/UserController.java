package ru.itis.javalab.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.dto.EmailPasswordDto;
import ru.itis.javalab.dto.TokenDto;
import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.models.User;
import ru.itis.javalab.services.LoginService;
import ru.itis.javalab.services.UsersService;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
public class UserController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private LoginService loginService;




    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody EmailPasswordDto emailPassword) {
        return ResponseEntity.ok(loginService.login(emailPassword));
    }

    @PostMapping("/users")
    public ResponseEntity<List<UserDto>> users(@RequestHeader("X-TOKEN") String token) {
        return ResponseEntity.ok(usersService.getAllUsers());
    }
}
