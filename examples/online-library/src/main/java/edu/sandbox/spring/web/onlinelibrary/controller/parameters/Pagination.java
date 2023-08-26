package edu.sandbox.spring.web.onlinelibrary.controller.parameters;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class Pagination {

    @Positive
    private long limit;

    @PositiveOrZero
    private long offset;
}
