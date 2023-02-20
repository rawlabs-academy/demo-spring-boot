package com.rawlabs.trainingspringboot.domain.dto;

import com.rawlabs.trainingspringboot.constant.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {

    @Schema(
            description = "Author name",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "John"
    )
    private String name;

    @Schema(
            description = "Author's gender",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "M"
    )
    private Gender gender;

}
