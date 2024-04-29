package fr.uga.l3miage.integrator.endpoints;

import fr.uga.l3miage.integrator.response.EntrepotsDTO;
import fr.uga.l3miage.integrator.response.VisualiserUneTourneeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@Tag(name = "VisualiserUneTourneeEndpoints")
@RequestMapping("/api/")
public interface VisualiserTourneeEndpoints {
    /*
    @Operation(description = "Récupère tous les entrepôts.")
    @ApiResponse(responseCode= "200", description = "L'ensemble de tous les entrepôts.")
    @ApiResponse(responseCode = "503", description = "La base de donnée n'est pas disponible")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/entrepots")
    Set<EntrepotDTO> getAllEntrepots();

    @Operation(description = "Récupère une tournée.")
    @ApiResponse(responseCode= "200", description = "Une tournée.")
    @ApiResponse(responseCode = "404", description = "Tournée non trouvée.", content = @Content(schema = @Schema(implementation = NotFoundErrorResponse.class)))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/tournee/{reference}")
    VisualiserUneTourneeDTO getTournee(@PathVariable(name = "reference")String reference);

    @Operation(description = "Récupère les tournées d'une journée.")
    @ApiResponse(responseCode= "200", description = "L'ensemble des tournées qui composent la journée.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/journee/{reference}/tournees")
    VisualiserUneTourneeDTO getTourneesJournee(@PathVariable(name = "reference")String reference);

    @Operation(description = "Récupère une journée d'un entrepôt.")
    @ApiResponse(responseCode= "200", description = "Une journée.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/entrepot/{nom}/journee/{date}")
    VisualiserUneTourneeDTO getJourneeEntrepot(@PathVariable(name = "nom") String nom, @PathVariable(name = "date") Date date);
    */
    @Operation(description = "Récupère la visualisation d'une tournée..")
    @ApiResponse(responseCode= "200", description = "Un objet qui décrit la tournée et ses objets associés.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/tournee/{reference}/visu")
    ResponseEntity<VisualiserUneTourneeDTO> getVisuTournee(@PathVariable(name = "reference")String reference);

    @Operation(description = "Récupère toutes les tournées")
    @ApiResponse(responseCode= "200", description = "Une liste d'entrepots")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/entrepot")
    ResponseEntity<Set<EntrepotsDTO>>getAllEntrepot();
}
