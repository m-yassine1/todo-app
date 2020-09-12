package com.servme.tes.todoapp.service;

import com.servme.tes.todoapp.model.request.ForgotPasswordRequest;
import com.servme.tes.todoapp.model.request.LoginRequest;
import com.servme.tes.todoapp.model.request.UpdateForgotPasswordRequest;
import com.servme.tes.todoapp.model.request.UserRequest;
import com.servme.tes.todoapp.model.response.LoginResponse;
import org.springframework.stereotype.Service;

public interface UserService {
    void register(UserRequest request);
    LoginResponse login(LoginRequest request);
    void forgotPassword(ForgotPasswordRequest request);
    void forgotPassword(String token, UpdateForgotPasswordRequest request);
}
