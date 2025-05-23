package fr.uga.l3miage.integrator.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NotFoundErrorResponse {
    @Schema(description = "end point call", example = "/api/tournee/t000G-A")
    private final String uri;
    @Schema(description = "error message", example = "Tournée non trouvée.")
    private final String errorMessage;
}
