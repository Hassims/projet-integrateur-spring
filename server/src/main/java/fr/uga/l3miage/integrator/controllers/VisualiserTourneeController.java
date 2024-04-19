package fr.uga.l3miage.integrator.controllers;

import fr.uga.l3miage.integrator.endpoints.VisualiserTourneeEndpoints;
import fr.uga.l3miage.integrator.exceptions.rest.NotFoundEntityRestException;
import fr.uga.l3miage.integrator.mappers.TourneeMapper;
import fr.uga.l3miage.integrator.models.TourneeEntity;
import fr.uga.l3miage.integrator.response.VisualiserUneTourneeDTO;
import fr.uga.l3miage.integrator.services.VisualiserTourneeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@org.springframework.stereotype.Controller
public class VisualiserTourneeController implements VisualiserTourneeEndpoints {

    private final VisualiserTourneeService service;
    private final TourneeMapper mapper;

    /**
     * @param reference
     * @return
     */
    @Override
    public ResponseEntity<VisualiserUneTourneeDTO> getVisuTournee(@PathVariable String reference) {
        try {
            TourneeEntity tourneeEntity = service.findTournee(reference);
            VisualiserUneTourneeDTO tourneeDTO = mapper.toResponse(tourneeEntity);
            return ResponseEntity.ok(tourneeDTO);
        } catch (NotFoundEntityRestException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
