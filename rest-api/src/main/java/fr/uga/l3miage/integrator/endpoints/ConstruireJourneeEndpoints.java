package fr.uga.l3miage.integrator.endpoints;

import fr.uga.l3miage.integrator.enums.EtatsDeCommande;
import fr.uga.l3miage.integrator.exceptions.BadRequestErrorResponse;
import fr.uga.l3miage.integrator.requests.ConstruireJourneePostRequest;
import fr.uga.l3miage.integrator.response.CommandeDTO;
import fr.uga.l3miage.integrator.response.JourneeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@Tag(name = "ConstruireJourneeEndpoints")
@RequestMapping("/api/")
@CrossOrigin
public interface ConstruireJourneeEndpoints {
    @Operation(method = "post")
    @ApiResponse(responseCode= "201", description = "Journée créée.")
    @ApiResponse(
            responseCode= "400",
            description = "Requête non valide",
            content=@Content(schema = @Schema(implementation = BadRequestErrorResponse.class))
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/journee", produces = "application/json")
    JourneeDTO postJournee(@RequestBody ConstruireJourneePostRequest request);

    @Operation(method = "get")
    @ApiResponse(responseCode= "200", description = "Commandes récupérées.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/commandes", produces = "application/json")
    Set<CommandeDTO> getCommandes(@RequestParam(required = false) EtatsDeCommande etat);
}
