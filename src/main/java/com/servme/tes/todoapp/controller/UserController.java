package com.servme.tes.todoapp.controller;

import com.servme.tes.todoapp.model.request.ForgotPasswordRequest;
import com.servme.tes.todoapp.model.request.LoginRequest;
import com.servme.tes.todoapp.model.request.UpdateForgotPasswordRequest;
import com.servme.tes.todoapp.model.request.UserRequest;
import com.servme.tes.todoapp.model.response.LoginResponse;
import com.servme.tes.todoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("user-api/v1/users")
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void register(@Valid @RequestBody UserRequest request)
    {
        userService.register(request);
    }

    @PostMapping(path = "login")
    @ResponseStatus(code = HttpStatus.OK)
    public LoginResponse login( @Valid @RequestBody LoginRequest request)
    {
        return userService.login(request);
    }

    @PostMapping(path = "forgot-password")
    @ResponseStatus(code = HttpStatus.OK)
    public void forgotPassword( @Valid @RequestBody ForgotPasswordRequest request)
    {
        userService.forgotPassword(request);
    }

    @PatchMapping(path = "tokens/{token}/forgot-password")
    @ResponseStatus(code = HttpStatus.OK)
    public void forgotPassword(@PathVariable String token, @Valid @RequestBody UpdateForgotPasswordRequest request)
    {
        userService.forgotPassword(token, request);
    }
}
