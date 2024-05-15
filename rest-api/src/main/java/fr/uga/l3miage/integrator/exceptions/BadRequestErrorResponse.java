package fr.uga.l3miage.integrator.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BadRequestErrorResponse {
    @Schema(description = "end point call", example = "/api/journee")
    private final String uri;
    @Schema(description = "error message", example = "Requête incorrecte.")
    private final String errorMessage;
}
