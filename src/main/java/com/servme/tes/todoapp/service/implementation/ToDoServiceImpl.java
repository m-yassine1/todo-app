package com.servme.tes.todoapp.service.implementation;

import com.servme.tes.todoapp.exception.ResourceNotFoundException;
import com.servme.tes.todoapp.exception.UnauthorizedException;
import com.servme.tes.todoapp.model.ToDoView;
import com.servme.tes.todoapp.model.repository.LoginTokenEntity;
import com.servme.tes.todoapp.model.repository.ToDoEntity;
import com.servme.tes.todoapp.model.repository.UserEntity;
import com.servme.tes.todoapp.model.request.ToDoQueryParameterRequest;
import com.servme.tes.todoapp.model.request.ToDoRequest;
import com.servme.tes.todoapp.repository.LoginTokenRepository;
import com.servme.tes.todoapp.repository.ToDoRepository;
import com.servme.tes.todoapp.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ToDoServiceImpl implements ToDoService {
    private final LoginTokenRepository loginTokenRepository;
    private final ToDoRepository toDoRepository;


    @Autowired
    public ToDoServiceImpl(LoginTokenRepository loginTokenRepository, ToDoRepository toDoRepository) {
        this.loginTokenRepository = loginTokenRepository;
        this.toDoRepository = toDoRepository;
    }

    @Override
    public void addToDo(String token, ToDoRequest request) {
        toDoRepository.save(
                ToDoEntity.builder()
                    .name(request.getName())
                    .description(request.getDescription())
                    .category(request.getCategory())
                    .dueDate(request.getDueDate())
                    .status(request.getStatus())
                    .user(getUser(token))
                    .build()
        );
    }

    @Override
    public void updateToDo(String token, long id, ToDoRequest request) {
        UserEntity user = getUser(token);
        ToDoEntity todo = user.getToDos().stream().filter(t -> Objects.equals(t.getId(), id)).findFirst().orElseThrow(new ResourceNotFoundException("Todo item " + id + " for "+ user.getId() + " is not found"));
        todo.setCategory(Objects.isNull(request.getCategory()) ? todo.getCategory() : request.getCategory() );
        todo.setName(Objects.isNull(request.getName()) ? todo.getName() : request.getName());
        todo.setDescription(Objects.isNull(request.getDescription()) ? todo.getDescription() : request.getDescription());
        todo.setDueDate(Objects.isNull(request.getDueDate()) ? todo.getDueDate() : request.getDueDate());
        todo.setStatus(Objects.isNull(request.getStatus()) ? todo.getStatus() : request.getStatus());
        toDoRepository.save(todo);
    }

    @Override
    @Transactional
    public void deleteToDo(String token, long id) {
        toDoRepository.deleteByIdAndUserId(id, getUser(token).getId());
    }

    @Override
    public void updateCategory(String token, long id, String category) {
        UserEntity user = getUser(token);
        ToDoEntity todo = user.getToDos().stream().filter(t -> Objects.equals(t.getId(), id)).findFirst().orElseThrow(new ResourceNotFoundException("Todo item " + id + " for "+ user.getId() + " is not found"));
        todo.setCategory(category);
        toDoRepository.save(todo);
    }

    @Override
    public void deleteCategory(String token, long id) {
        updateCategory(token, id, null);
    }

    @Override
    public List<ToDoView> getToDos(String token) {
        return getUser(token).getToDos().stream().map(ToDoView::getToDoView).collect(Collectors.toList());
    }

    @Override
    public List<ToDoView> getToDos(String token, ToDoQueryParameterRequest parameterRequest) {
        List<ToDoEntity> todos = getUser(token).getToDos();
        return todos.stream()
                .filter(t -> getCategoryFilter(t, parameterRequest))
                .filter(t -> getMonthFilter(t, parameterRequest))
                .filter(t -> getDayFilter(t, parameterRequest))
                .filter(t -> getStatusFilter(t, parameterRequest))
                .skip(getPageNumber(parameterRequest))
                .limit(getPageSize(parameterRequest))
                .map(ToDoView::getToDoView)
                .collect(Collectors.toList());
    }

    private boolean getCategoryFilter(ToDoEntity todo, ToDoQueryParameterRequest parameterRequest) {
        return Objects.equals(todo.getCategory(), !Objects.isNull(parameterRequest.getCategory()) ? parameterRequest.getCategory() : todo.getCategory());
    }

    private boolean getMonthFilter(ToDoEntity todo, ToDoQueryParameterRequest parameterRequest) {
        return Objects.equals(todo.getDueDate().getMonthValue(), !Objects.isNull(parameterRequest.getMonth()) && parameterRequest.getMonth().isPresent() ? parameterRequest.getMonth().get(): todo.getDueDate().getMonthValue());
    }

    private boolean getDayFilter(ToDoEntity todo, ToDoQueryParameterRequest parameterRequest) {
        return Objects.equals(todo.getDueDate().getDayOfMonth(), !Objects.isNull(parameterRequest.getDay()) && parameterRequest.getDay().isPresent()? parameterRequest.getDay().get() : todo.getDueDate().getDayOfMonth());
    }

    private boolean getStatusFilter(ToDoEntity todo, ToDoQueryParameterRequest parameterRequest) {
        return Objects.equals(todo.getStatus(), !Objects.isNull(parameterRequest.getStatus()) ? parameterRequest.getCategory() : todo.getStatus());
    }

    private int getPageNumber(ToDoQueryParameterRequest parameterRequest) {
        return Objects.isNull(parameterRequest.getPageSize()) ? 0
                : Objects.isNull(parameterRequest.getPageNumber()) ? 0
                    : Integer.sum(parameterRequest.getPageNumber().get(), - 1) * parameterRequest.getPageSize().get();
    }

    private int getPageSize(ToDoQueryParameterRequest parameterRequest) {
        return Objects.isNull(parameterRequest.getPageSize()) ? Integer.MAX_VALUE
                : parameterRequest.getPageSize().get();
    }

    private UserEntity getUser(String token){
        LoginTokenEntity user = loginTokenRepository.findById(token)
                .orElseThrow(new UnauthorizedException("Login token has expired or does not exist " + token));
        return user.getUser();
    }


}
