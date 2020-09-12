package com.servme.tes.todoapp.model.repository;


import com.servme.tes.todoapp.model.ToDoView;
import lombok.*;
import lombok.experimental.SuperBuilder;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity(name = "todo")
@EqualsAndHashCode(callSuper = true)
@Table(name = "todo")
public class ToDoEntity extends ToDoView {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;
}
