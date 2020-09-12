package com.servme.tes.todoapp.repository;

import com.servme.tes.todoapp.ObjectBuilder;
import com.servme.tes.todoapp.constant.Constant;
import com.servme.tes.todoapp.model.repository.ToDoEntity;
import com.servme.tes.todoapp.model.repository.UserEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ToDoRepositoryTest {
    @Autowired
    private ToDoRepository toDoRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void AddToDoItem() {
        UserEntity user = ObjectBuilder.getUser();
        userRepository.save(user);
        ToDoEntity todo = ObjectBuilder.getToDo(user);
        toDoRepository.save(todo);
        Optional<ToDoEntity> fetchedTodo = toDoRepository.findByIdAndUserId(todo.getId(), user.getId());
        Assert.assertTrue(fetchedTodo.isPresent());
        Assert.assertEquals("Invalid todo name", fetchedTodo.get().getName(), todo.getName());
    }
}
