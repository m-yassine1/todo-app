package com.servme.tes.todoapp.model.request;

import com.servme.tes.todoapp.model.UserView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserRequest extends UserView {
    @NotBlank(message = "Password is required")
    private String password;
}
