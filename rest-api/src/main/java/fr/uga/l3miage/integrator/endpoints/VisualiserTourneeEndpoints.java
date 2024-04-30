package fr.uga.l3miage.integrator.endpoints;

import fr.uga.l3miage.integrator.response.EntrepotDTO;
import fr.uga.l3miage.integrator.response.JourneeDTO;
import fr.uga.l3miage.integrator.response.VisualiserUneTourneeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@Tag(name = "VisualiserUneTourneeEndpoints")
@RequestMapping("/api/")
public interface VisualiserTourneeEndpoints {

    @Operation(description = "Récupère la visualisation d'une tournée..")
    @ApiResponse(responseCode= "200", description = "Un objet qui décrit la tournée et ses objets associés.")
    @ApiResponse(responseCode= "404", description = "Aucune visualisation de tournee trouvée.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/tournee/{reference}/visu")
    VisualiserUneTourneeDTO getVisuTournee(@PathVariable(name = "reference")String reference);

    @Operation(description = "Récupère tous les entrepots")
    @ApiResponse(responseCode= "200", description = "Une liste d'entrepots")
    @ApiResponse(responseCode= "404", description = "Aucun entrepots trouvée.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/entrepot")
    Set<EntrepotDTO>getAllEntrepot();

    @Operation(description = "Récupère une journée associée à un entrepot et une date.")
    @ApiResponse(responseCode= "200", description = "Une journée")
    @ApiResponse(responseCode= "404", description = "Journée non trouvée.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/entrepot/{nom}/journee/{date}")
    JourneeDTO getJourneeByEntrepotAndDate(@PathVariable(name = "nom")String nomEntrepot, @PathVariable(name = "date")String dateJournee);
}
