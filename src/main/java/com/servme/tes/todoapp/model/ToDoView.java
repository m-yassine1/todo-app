package com.servme.tes.todoapp.model;

import com.servme.tes.todoapp.constant.Constant;
import com.servme.tes.todoapp.model.repository.ToDoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@MappedSuperclass
public class ToDoView {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column
    @NotBlank(message = "name is required")
    protected String name;

    @Column
    protected String description;

    @Column
    protected String category;

    @Column
    @Enumerated(EnumType.STRING)
    protected Constant.STATUS status;

    @Column(name = "due_date")
    protected LocalDate dueDate;

    public static ToDoView getToDoView(ToDoEntity todo) {
        return ToDoView.builder()
                .category(todo.getCategory())
                .description(todo.getDescription())
                .dueDate(todo.getDueDate())
                .id(todo.getId())
                .name(todo.getName())
                .status(todo.getStatus())
                .build();
    }
}
