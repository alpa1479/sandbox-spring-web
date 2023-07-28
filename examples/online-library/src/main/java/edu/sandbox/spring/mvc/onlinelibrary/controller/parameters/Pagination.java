package edu.sandbox.spring.mvc.onlinelibrary.controller.parameters;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pagination {

    @Positive
    private long limit;

    @PositiveOrZero
    private long offset;
}
