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
public class BookDto {

    @Schema(
            description = "Book title",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "Mastering Spring Boot"
    )
    private String title;

    @Schema(
            description = "Book price",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "1500000"
    )
    private Integer price;

    @Schema(
            description = "Author's ID",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "1"
    )
    private Long authorId;

    @Schema(
            description = "Category's ID",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "1"
    )
    private Long categoryId;

}
