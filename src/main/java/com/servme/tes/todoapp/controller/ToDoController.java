package com.servme.tes.todoapp.controller;

import com.servme.tes.todoapp.model.ToDoView;
import com.servme.tes.todoapp.model.request.ToDoQueryParameterRequest;
import com.servme.tes.todoapp.model.request.ToDoRequest;
import com.servme.tes.todoapp.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("todo-api/v1")
@RestController
public class ToDoController {
    private final ToDoService toDoService;

    @Autowired
    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @PostMapping(path = "tokens/{token}/todos")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addToDo(@PathVariable String token, @Valid @RequestBody ToDoRequest request) {
        toDoService.addToDo(token, request);
    }

    @PutMapping(path = "tokens/{token}/todos/{todoId}")
    @ResponseStatus(code = HttpStatus.OK)
    public void updateToDo(@PathVariable String token, @PathVariable long todoId, @Valid @RequestBody ToDoRequest request) {
        toDoService.updateToDo(token, todoId, request);
    }

    @DeleteMapping(path = "tokens/{token}/todos/{todoId}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteToDo(@PathVariable String token, @PathVariable long todoId) {
        toDoService.deleteToDo(token, todoId);
    }

    @PatchMapping(path = "tokens/{token}/todos/{todoId}/categories/{category}")
    @ResponseStatus(code = HttpStatus.OK)
    public void updateToDoCategory(@PathVariable String token, @PathVariable long todoId, @PathVariable String category) {
        toDoService.updateCategory(token, todoId, category);
    }

    @DeleteMapping(path = "tokens/{token}/todos/{todoId}/categories")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteToDoCategory(@PathVariable String token, @PathVariable long todoId) {
        toDoService.deleteCategory(token, todoId);
    }

    @GetMapping(path = "tokens/{token}/todos")
    @ResponseStatus(code = HttpStatus.OK)
    public List<ToDoView> getToDos(@PathVariable String token, ToDoQueryParameterRequest parameterRequest) {
        return toDoService.getToDos(token, parameterRequest);
    }
}
