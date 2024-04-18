package fr.uga.l3miage.integrator.endpoints;

import fr.uga.l3miage.integrator.exceptions.NotFoundErrorResponse;
import fr.uga.l3miage.integrator.response.TourneeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Set;


@RestController
@Tag(name = "VisualiserUneTourneeEndpoints")
@RequestMapping("/api/v1/")
public interface VisualiserUneTourneeEndpoints {
    @Operation(description = "Controller principal")
    @ApiResponse(responseCode= "200", description = "L'ensemble de toutes les tournées.")
    @ApiResponse(responseCode = "503", description = "La base de donnée n'est pas disponible")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/tournees")
    Set<TourneeDTO> getAllTournees();

    @Operation(description = "Récupère tous les entrepôts.")
    @ApiResponse(responseCode= "200", description = "L'ensemble de tous les entrepôts.")
    @ApiResponse(responseCode = "503", description = "La base de donnée n'est pas disponible")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/entrepots")
    Set<TourneeDTO> getAllEntrepots();

    @Operation(description = "Récupère une tournée.")
    @ApiResponse(responseCode= "200", description = "Une tournée.")
    @ApiResponse(responseCode = "404", description = "Tournée non trouvée.", content = @Content(schema = @Schema(implementation = NotFoundErrorResponse.class)))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/tournee/{reference}")
    TourneeDTO getTournee(@PathVariable(name = "reference")String reference);

    @Operation(description = "Récupère les tournées d'une journée.")
    @ApiResponse(responseCode= "200", description = "L'ensemble des tournées qui composent la journée.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/journee/{reference}/tournees")
    TourneeDTO getTourneesJournee(@PathVariable(name = "reference")String reference);

    /*
    @Operation(description = "Récupère les livraisons d'une tournée.")
    @ApiResponse(responseCode= "200", description = "L'ensemble des livraisons qui composent la tournée.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/tournee/{reference}/livraisons")
    TourneeDTO getLivraisonsTournee(@PathVariable(name = "reference")String reference);

    @Operation(description = "Récupère les livreurs d'une tournée.")
    @ApiResponse(responseCode= "200", description = "L'ensemble des livreurs affectés à une tournée.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/tournee/{reference}/livreurs")
    TourneeDTO getLivreursTournee(@PathVariable(name = "reference")String reference);

    @Operation(description = "Récupère les commandes d'une livraison.")
    @ApiResponse(responseCode= "200", description = "L'ensemble des commandes d'une livraison.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/livraison/{reference}/commandes")
    TourneeDTO getCommandesLivraison(@PathVariable(name = "reference")String reference);

    @Operation(description = "Récupère un client.")
    @ApiResponse(responseCode= "200", description = "Un client.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/client/{email}")
    TourneeDTO getClient(@PathVariable(name = "email")String email);
    */

    @Operation(description = "Récupère une journée d'un entrepôt.")
    @ApiResponse(responseCode= "200", description = "Une journée.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/entrepot/{nom}/journee/{date}")
    TourneeDTO getJourneeEntrepot(@PathVariable(name = "nom") String nom, @PathVariable(name = "date") Date date);

    @Operation(description = "Récupère la visualisation d'une tournée..")
    @ApiResponse(responseCode= "200", description = "Un objet qui décrit la tournée et ses objets associés.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/tournee/{reference}/visu")
    TourneeDTO getVisuTournee(@PathVariable(name = "reference")String reference);
}
