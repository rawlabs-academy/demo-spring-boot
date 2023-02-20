package com.rawlabs.trainingspringboot.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "anyToken"
    )
    private String accessToken;

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private LocalDateTime expiresIn;

}
