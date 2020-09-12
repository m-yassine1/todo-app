package com.servme.tes.todoapp.repository;

import com.servme.tes.todoapp.ObjectBuilder;
import com.servme.tes.todoapp.constant.Constant;
import com.servme.tes.todoapp.model.repository.ForgotPasswordEntity;
import com.servme.tes.todoapp.model.repository.LoginTokenEntity;
import com.servme.tes.todoapp.model.repository.UserEntity;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginTokenRepository loginTokenRepository;
    @Autowired
    private ForgotPasswordRepository forgotPasswordRepository;

    @Test
    public void AddUser() {
        UserEntity user = ObjectBuilder.getUser();
        userRepository.save(user);
        Optional<UserEntity> fetchedUser = userRepository.findByEmail(user.getEmail());
        Assert.assertTrue(fetchedUser.isPresent());
        Assert.assertEquals("Invalid email",fetchedUser.get().getEmail(), user.getEmail());
    }

    @Test
    public void LoginUser() {
        final String uuid = UUID.randomUUID().toString();
        UserEntity user = ObjectBuilder.getUser();
        userRepository.save(user);
        Optional<UserEntity> fetchedUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        Assert.assertTrue(fetchedUser.isPresent());
        loginTokenRepository.save(
                LoginTokenEntity.builder()
                        .Token(uuid)
                        .user(user).build());
        Optional<LoginTokenEntity> loginToken = loginTokenRepository.findById(uuid);
        Assert.assertTrue(loginToken.isPresent());
        Assert.assertEquals("Invalid login token",loginToken.get().getToken(), uuid);
    }

    @Test
    public void addForgetPassword() {
        final String uuid = UUID.randomUUID().toString();
        UserEntity user = ObjectBuilder.getUser();
        userRepository.save(user);
        forgotPasswordRepository.save(
                ForgotPasswordEntity.builder()
                        .token(uuid)
                       .user(user).build());
        Optional<ForgotPasswordEntity> forgotPasswordToken = forgotPasswordRepository.findById(uuid);
        Assert.assertTrue(forgotPasswordToken.isPresent());
        Assert.assertEquals("Invalid forgot password token",forgotPasswordToken.get().getToken(), uuid);
    }

    @Test
    public void updateForgetPassword() {
        final String uuid = UUID.randomUUID().toString();
        final String newPassword = "1234567";
        UserEntity createdUser = ObjectBuilder.getUser();
        userRepository.save(createdUser);
        forgotPasswordRepository.save(
                ForgotPasswordEntity.builder()
                        .token(uuid)
                        .user(createdUser).build());
        Optional<ForgotPasswordEntity> forgotPasswordToken = forgotPasswordRepository.findById(uuid);
        Assert.assertTrue(forgotPasswordToken.isPresent());
        UserEntity user = forgotPasswordToken.get().getUser();
        user.setPassword(newPassword);
        userRepository.save(user);
        Optional<UserEntity> fetchedUser = userRepository.findByEmail(createdUser.getEmail());
        Assert.assertTrue(fetchedUser.isPresent());
        Assert.assertEquals("Invalid new password", fetchedUser.get().getPassword(), newPassword);
    }
}
