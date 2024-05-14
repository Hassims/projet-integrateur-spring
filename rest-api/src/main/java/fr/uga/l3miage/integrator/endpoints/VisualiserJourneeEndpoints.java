package fr.uga.l3miage.integrator.endpoints;

import fr.uga.l3miage.integrator.response.JourneeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@Tag(name = "VisualiserJourneeEndpoints")
@RequestMapping("/api")
@CrossOrigin
public interface VisualiserJourneeEndpoints {
    @Operation(description = "Récupère un ensemble de journées.")
    @ApiResponse(responseCode= "200", description = "Un ensemble de journées.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/journees", produces = "application/json")
    Set<JourneeDTO> getJournees(
            @RequestParam(required = false) String entrepot,
            @RequestParam(required = false) String dateDepart,
            @RequestParam(required = false) String dateFin
    );
}
