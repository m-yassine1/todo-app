package com.servme.tes.todoapp.service;

import com.servme.tes.todoapp.model.ToDoView;
import com.servme.tes.todoapp.model.request.ToDoQueryParameterRequest;
import com.servme.tes.todoapp.model.request.ToDoRequest;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ToDoService {
    public void addToDo(String token, ToDoRequest request);
    public void updateToDo(String token, long id, ToDoRequest request);
    public void deleteToDo(String token, long id);
    public void updateCategory(String token, long id, String category);
    public void deleteCategory(String token, long id);
    public List<ToDoView> getToDos(String token);
    public List<ToDoView> getToDos(String token, ToDoQueryParameterRequest parameterRequest);
}
