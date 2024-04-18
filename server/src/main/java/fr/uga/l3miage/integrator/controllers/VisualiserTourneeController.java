package fr.uga.l3miage.integrator.controllers;

import fr.uga.l3miage.integrator.endpoints.VisualiserTourneeEndpoints;
import fr.uga.l3miage.integrator.mappers.TourneeMapper;
import fr.uga.l3miage.integrator.response.VisualiserUneTourneeDTO;
import fr.uga.l3miage.integrator.services.VisualiserTourneeService;
import lombok.RequiredArgsConstructor;
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
    public VisualiserUneTourneeDTO getVisuTournee(String reference) {
        return null;
    }
}
