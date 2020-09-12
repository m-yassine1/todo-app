package com.servme.tes.todoapp.service;

import com.servme.tes.todoapp.repository.ForgotPasswordRepository;
import com.servme.tes.todoapp.repository.LoginTokenRepository;
import com.servme.tes.todoapp.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private LoginTokenRepository loginTokenRepository;
    @Mock
    private ForgotPasswordRepository forgotPasswordRepository;
    @Mock
    private EmailService emailService;

    @InjectMocks
    private UserService userService;

    @Test
    public void register() {

    }
}
