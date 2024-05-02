package fr.uga.l3miage.integrator.endpoints;

import fr.uga.l3miage.integrator.exceptions.NotFoundErrorResponse;
import fr.uga.l3miage.integrator.response.EntrepotDTO;
import fr.uga.l3miage.integrator.response.JourneeDTO;
import fr.uga.l3miage.integrator.response.VisualiserUneTourneeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@Tag(name = "VisualiserUneTourneeEndpoints")
@RequestMapping("/api/")
public interface VisualiserTourneeEndpoints {

    @Operation(description = "Récupère la visualisation d'une tournée.")
    @ApiResponse(responseCode= "200", description = "Tournée trouvée.")
    @ApiResponse(
            responseCode= "404",
            description = "Tournée non trouvée.",
            content=@Content(schema = @Schema(implementation = NotFoundErrorResponse.class))
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/tournee/{reference}/visu", produces = "application/json")
    VisualiserUneTourneeDTO getVisuTournee(@PathVariable(name = "reference")String reference);

    @Operation(description = "Récupère tous les entrepots")
    @ApiResponse(responseCode= "200", description = "Une liste d'entrepots")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/entrepot", produces = "application/json")
    Set<EntrepotDTO>getAllEntrepot();

    @Operation(description = "Récupère une journée associée à un entrepot et une date.")
    @ApiResponse(responseCode= "200", description = "Une journée")
    @ApiResponse(
            responseCode= "404",
            description = "Journée non trouvée.",
            content=@Content(schema = @Schema(implementation = NotFoundErrorResponse.class))
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/entrepot/{nom}/journee/{date}", produces = "application/json")
    JourneeDTO getJourneeByEntrepotAndDate(@PathVariable(name = "nom")String nomEntrepot, @PathVariable(name = "date")String dateJournee);
}
