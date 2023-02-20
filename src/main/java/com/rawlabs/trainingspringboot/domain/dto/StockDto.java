package com.rawlabs.trainingspringboot.domain.dto;

import com.rawlabs.trainingspringboot.constant.StockType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockDto {

    @Schema(
            description = "Book update stock",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "100"
    )
    private Integer value;

    @Schema(
            description = "Stock type operation",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "ADDITIONS"
    )
    private StockType type;

}
