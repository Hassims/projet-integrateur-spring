package fr.uga.l3miage.integrator.endpoints;

import fr.uga.l3miage.integrator.exceptions.NotFoundErrorResponse;
import fr.uga.l3miage.integrator.requests.JourneePatchDateRequest;
import fr.uga.l3miage.integrator.requests.LivraisonPatchNumeroRequest;
import fr.uga.l3miage.integrator.requests.TourneePatchJourneeRequest;
import fr.uga.l3miage.integrator.response.JourneeDTO;
import fr.uga.l3miage.integrator.response.LivraisonDTO;
import fr.uga.l3miage.integrator.response.TourneeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Ajuster une journée")
@RequestMapping("/api/")
@CrossOrigin
public interface AjusterJourneeEndpoints {

    @Operation(method = "patch")
    @ApiResponse(responseCode= "200", description = "Journée modifiée.")
    @ApiResponse(
            responseCode= "404",
            description = "Journée à mettre à jour non trouvée.",
            content=@Content(schema = @Schema(implementation = NotFoundErrorResponse.class))
    )
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/journee/{reference}/date", produces = "application/json")
    JourneeDTO patchJournee_Date(@PathVariable String reference, @RequestBody JourneePatchDateRequest request);

    @Operation(method = "patch")
    @ApiResponse(responseCode= "200", description = "Tournéee modifiée.")
    @ApiResponse(
            responseCode= "404",
            description = "Tournée à mettre à jour non trouvée.",
            content=@Content(schema = @Schema(implementation = NotFoundErrorResponse.class))
    )
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/tournee/{reference}/journee", produces = "application/json")
    TourneeDTO patchTournee_Journee(@PathVariable String reference, @RequestBody TourneePatchJourneeRequest request);

    @Operation(method = "patch")
    @ApiResponse(responseCode= "200", description = "Livraison modifiée.")
    @ApiResponse(
            responseCode= "404",
            description = "Livraison à mettre à jour non trouvée.",
            content=@Content(schema = @Schema(implementation = NotFoundErrorResponse.class))
    )
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/livraison/{reference}", produces = "application/json")
    LivraisonDTO patchLivraison_Numero(@PathVariable String reference, @RequestBody LivraisonPatchNumeroRequest request);
}
