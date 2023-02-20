package com.rawlabs.trainingspringboot.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    @Schema(
            description = "Category name",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "Programming"
    )
    private String name;

}
