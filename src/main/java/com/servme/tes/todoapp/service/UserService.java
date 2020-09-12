package com.servme.tes.todoapp.service;

import com.servme.tes.todoapp.model.request.ForgotPasswordRequest;
import com.servme.tes.todoapp.model.request.LoginRequest;
import com.servme.tes.todoapp.model.request.UpdateForgotPasswordRequest;
import com.servme.tes.todoapp.model.request.UserRequest;
import com.servme.tes.todoapp.model.response.LoginResponse;

public interface UserService {
    void register(UserRequest request);
    LoginResponse login(LoginRequest request);
    void updatePassword(ForgotPasswordRequest request);
    void updatePassword(String token, UpdateForgotPasswordRequest request);
}
