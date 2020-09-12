package com.servme.tes.todoapp.model.repository;

import com.servme.tes.todoapp.model.UserView;
import lombok.*;
import lombok.experimental.SuperBuilder;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity(name = "user")
@EqualsAndHashCode(exclude = "toDos", callSuper = true)
@Table(name = "user")
public class UserEntity extends UserView {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    protected String password;

    @OneToMany(mappedBy="user", fetch=FetchType.EAGER)
    private List<ToDoEntity> toDos;
}
