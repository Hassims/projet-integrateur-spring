package fr.uga.l3miage.integrator.controllers;

import fr.uga.l3miage.integrator.endpoints.ChargerTourneeEndpoints;
import fr.uga.l3miage.integrator.mappers.TourneeMapper;
import fr.uga.l3miage.integrator.requests.TourneePatchRequest;
import fr.uga.l3miage.integrator.response.VisualiserUneTourneeDTO;
import fr.uga.l3miage.integrator.services.TourneeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChargerTourneeController implements ChargerTourneeEndpoints {

    private final TourneeService service;
    private final TourneeMapper tourneeMapper;

    @Override
    public VisualiserUneTourneeDTO patchTourneeEtat(String reference, TourneePatchRequest request) {
        return tourneeMapper.toDTO(service.updateTourneeEtat(reference, request.getEtat()));
    }
}
