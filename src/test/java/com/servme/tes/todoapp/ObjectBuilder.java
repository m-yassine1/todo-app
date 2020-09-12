package com.servme.tes.todoapp;

import com.servme.tes.todoapp.constant.Constant;
import com.servme.tes.todoapp.model.repository.ToDoEntity;
import com.servme.tes.todoapp.model.repository.UserEntity;

import java.time.LocalDate;

public final class ObjectBuilder {
    private static final String email = "test@test.com";
    private static final String firstName = "moe";
    private static final String lastName = "test";
    private static final LocalDate birthday = LocalDate.of(2010, 10, 10);
    private static final Constant.GENDER gender = Constant.GENDER.male;
    private static final String mobileNumber = "123456789";
    private static final String password = "Qazwsx123";
    private static final String category = "work";
    private static final String description = "This is a description";
    private static final LocalDate dueDate = LocalDate.of(2021, 10,10);
    private static final String name = "Todo Item name";
    private static final Constant.STATUS status = Constant.STATUS.initial;
    private static final Long toDoId = 1L;

    public static UserEntity getUser() {
        UserEntity user = UserEntity.builder()
                .birthday(birthday)
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .gender(gender)
                .mobileNumber(mobileNumber)
                .password(password)
                .build();
        return user;
    }

    public static ToDoEntity getToDo(UserEntity user) {
        ToDoEntity todo = ToDoEntity.builder()
                .id(toDoId)
                .user(user)
                .category(category)
                .description(description)
                .dueDate(dueDate)
                .name(name)
                .status(status)
                .build();
        return todo;
    }
}
