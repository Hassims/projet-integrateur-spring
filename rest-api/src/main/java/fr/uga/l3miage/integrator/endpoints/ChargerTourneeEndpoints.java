package fr.uga.l3miage.integrator.endpoints;

import fr.uga.l3miage.integrator.exceptions.NotFoundErrorResponse;
import fr.uga.l3miage.integrator.requests.TourneePatchEtatRequest;
import fr.uga.l3miage.integrator.response.VisualiserUneTourneeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "ChargerTourneeEndpoints")
@RequestMapping("/api/")
@CrossOrigin
public interface ChargerTourneeEndpoints {

    @Operation(method = "patch")
    @ApiResponse(responseCode= "200", description = "Tournée modifiée.")
    @ApiResponse(
            responseCode= "404",
            description = "Tournée à mettre à jour non trouvée.",
            content=@Content(schema = @Schema(implementation = NotFoundErrorResponse.class))
    )
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/tournee/{reference}", produces = "application/json")
    VisualiserUneTourneeDTO patchTourneeEtat(@PathVariable(name = "reference")String reference, @RequestBody TourneePatchEtatRequest request);
}
