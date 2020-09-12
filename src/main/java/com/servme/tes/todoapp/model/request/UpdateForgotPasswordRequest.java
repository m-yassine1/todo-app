package com.servme.tes.todoapp.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateForgotPasswordRequest {
    @NotBlank(message = "Password is required")
    public String password;
    @NotBlank(message = "Confirm password is required")
    public String confirmPassword;
}
