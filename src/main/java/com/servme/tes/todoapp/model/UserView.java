package com.servme.tes.todoapp.model;

import com.servme.tes.todoapp.constant.Constant;
import com.servme.tes.todoapp.model.repository.ToDoEntity;
import com.servme.tes.todoapp.model.repository.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@MappedSuperclass
public class UserView {
    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "First name is required")
    protected String firstName;

    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "Last name is required")
    protected String lastName;

    @Column(unique = true, nullable = false)
    @Email(message = "Email should be a valid email")
    protected String email;

    @Column(name = "mobile_number" ,unique = true, nullable = false)
    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "\\+?[0-9]{1,15}", message = "Invalid mobile number provided")
    protected String mobileNumber;

    @Enumerated(EnumType.STRING)
    @Column
    protected Constant.GENDER gender;

    @Column
    @PastOrPresent(message = "Birthday should be be in the past or now.")
    protected LocalDate birthday;

    public static UserView getUserView(UserEntity user) {
        return UserView.builder()
                .birthday(user.getBirthday())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .gender(user.getGender())
                .lastName(user.getLastName())
                .mobileNumber(user.getMobileNumber())
                .build();
    }
}
