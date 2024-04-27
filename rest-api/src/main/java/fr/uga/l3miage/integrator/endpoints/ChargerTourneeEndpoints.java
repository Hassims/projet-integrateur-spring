package fr.uga.l3miage.integrator.endpoints;

import fr.uga.l3miage.integrator.requests.TourneePatchRequest;
import fr.uga.l3miage.integrator.response.VisualiserUneTourneeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "ChargerTourneeEndpoints")
@RequestMapping("/api/")
public interface ChargerTourneeEndpoints {

    @Operation(method = "patch")
    @ApiResponses({
            @ApiResponse(responseCode= "200", description = "Tournée modifiée."),
            @ApiResponse(responseCode= "404", description = "Tournée non trouvée.")
    })
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/tournee/{reference}")
    VisualiserUneTourneeDTO patchTourneeEtat(@PathVariable(name = "reference")String reference, @RequestBody TourneePatchRequest request);
}
