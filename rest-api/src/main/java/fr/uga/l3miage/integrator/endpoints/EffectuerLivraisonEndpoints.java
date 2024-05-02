package fr.uga.l3miage.integrator.endpoints;

import fr.uga.l3miage.integrator.exceptions.NotFoundErrorResponse;
import fr.uga.l3miage.integrator.requests.LivraisonPatchEtatRequest;
import fr.uga.l3miage.integrator.response.LivraisonDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "EffectuerLivraisonEndpoints")
@RequestMapping("/api/")
public interface EffectuerLivraisonEndpoints {
    @Operation(method = "patch")
    @ApiResponse(responseCode= "200", description = "Livraison modifiée.")
    @ApiResponse(
            responseCode= "404",
            description = "Livraison à mettre à jour non trouvée.",
            content=@Content(schema = @Schema(implementation = NotFoundErrorResponse.class))
    )
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/livraison/{reference}/etat", produces = "application/json")
    LivraisonDTO patchEtat(@PathVariable String reference, @RequestBody LivraisonPatchEtatRequest request);
}
