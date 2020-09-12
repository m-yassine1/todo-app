package com.servme.tes.todoapp.model.request;

import com.servme.tes.todoapp.constant.Constant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToDoQueryParameterRequest {
    private String category;
    @Enumerated(EnumType.STRING)
    private Constant.STATUS status;
    @Range(min = 1, max = 31, message = "Invalid day selected")
    private Optional<Integer> day;
    @Range(min = 1, max = 12, message = "Invalid month selected")
    private Optional<Integer> month;
    private Optional<Integer> pageSize;
    private Optional<Integer> pageNumber;
}
