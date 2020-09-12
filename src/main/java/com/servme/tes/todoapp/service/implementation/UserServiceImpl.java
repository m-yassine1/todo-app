package com.servme.tes.todoapp.service.implementation;

import com.servme.tes.todoapp.exception.ResourceNotFoundException;
import com.servme.tes.todoapp.exception.UnauthorizedException;
import com.servme.tes.todoapp.model.UserView;
import com.servme.tes.todoapp.model.repository.ForgotPasswordEntity;
import com.servme.tes.todoapp.model.repository.LoginTokenEntity;
import com.servme.tes.todoapp.model.repository.UserEntity;
import com.servme.tes.todoapp.model.request.ForgotPasswordRequest;
import com.servme.tes.todoapp.model.request.LoginRequest;
import com.servme.tes.todoapp.model.request.UpdateForgotPasswordRequest;
import com.servme.tes.todoapp.model.request.UserRequest;
import com.servme.tes.todoapp.model.response.LoginResponse;
import com.servme.tes.todoapp.repository.ForgotPasswordRepository;
import com.servme.tes.todoapp.repository.LoginTokenRepository;
import com.servme.tes.todoapp.repository.UserRepository;
import com.servme.tes.todoapp.service.EmailService;
import com.servme.tes.todoapp.service.UserService;
import com.servme.tes.todoapp.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final LoginTokenRepository loginTokenRepository;
    private final ForgotPasswordRepository forgotPasswordRepository;
    private final EmailService emailService;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, LoginTokenRepository loginTokenRepository, ForgotPasswordRepository forgotPasswordRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.loginTokenRepository = loginTokenRepository;
        this.forgotPasswordRepository = forgotPasswordRepository;
        this.emailService = emailService;
    }

    @Override
    public void register(UserRequest request) {
        userRepository.save(
                UserEntity.builder()
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .birthday(request.getBirthday())
                        .email(request.getEmail())
                        .gender(request.getGender())
                        .mobileNumber(request.getMobileNumber())
                        .password(getHashedPassword(request.getPassword()))
                        .build()
        );

        emailService.sendEmail(request.getEmail(), "User Registered", "Welcome "+ request.getFirstName() + ",\nLogin to add your todos.");
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        UserEntity user = userRepository.findByEmailAndPassword(request.getUsername(), getHashedPassword(request.getPassword()))
                .orElseThrow(new UnauthorizedException("Invalid credentials for " + request.getUsername()));


        LoginTokenEntity token = LoginTokenEntity.builder()
                                            .Token(generateUuid())
                                            .user(user)
                                            .build();
        loginTokenRepository.save(token);
        return LoginResponse.builder()
                .token(token.getToken())
                .user(UserView.getUserView(user))
                .build();
    }

    @Override
    public void forgotPassword(ForgotPasswordRequest request) {
        UserEntity user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(new ResourceNotFoundException("Email does not exist " + request.getEmail()));

        forgotPasswordRepository.save(
                ForgotPasswordEntity.builder()
                    .token(generateUuid())
                    .user(user)
                    .build()
        );
    }

    @Override
    public void forgotPassword(String token, UpdateForgotPasswordRequest request) {
        ForgotPasswordEntity forgotPasswordEntity = forgotPasswordRepository.findById(token)
                .orElseThrow(new ResourceNotFoundException("Token does not exist " + token));
        UserEntity user = forgotPasswordEntity.getUser();
        user.setPassword(getHashedPassword(request.password));
        userRepository.save(user);
        forgotPasswordRepository.deleteById(token);
        emailService.sendEmail(user.getEmail(), "Password Reset", "Dear "+ user.getFirstName() + ",\nYour password has been reset.");
    }

    private String generateUuid() {
        return UUID.randomUUID().toString();
    }

    private String getHashedPassword(String password) {
        return Util.hashMessage(password);
    }
}
