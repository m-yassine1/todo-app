package com.servme.tes.todoapp.repository.implementation;

import com.servme.tes.todoapp.constant.Constant;
import com.servme.tes.todoapp.model.repository.ToDoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ToDoH2Repository extends JpaRepository<ToDoEntity, Long> {
    List<ToDoEntity> findByUserId(long userId);
    void deleteByIdAndUserId(long id, long userId);
    Optional<ToDoEntity> findByIdAndUserId(long id, long userId);
    List<ToDoEntity> findByUserIdAndCategoryAndStatusAndDueDate(long userId, String category, Constant.STATUS status, LocalDate dueDate);
}
