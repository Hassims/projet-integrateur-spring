package fr.uga.l3miage.integrator.controllers;

import fr.uga.l3miage.integrator.endpoints.VisualiserTourneeEndpoints;
import fr.uga.l3miage.integrator.mappers.EntrepotMapper;
import fr.uga.l3miage.integrator.mappers.TourneeMapper;
import fr.uga.l3miage.integrator.response.EntrepotDTO;
import fr.uga.l3miage.integrator.response.JourneeDTO;
import fr.uga.l3miage.integrator.response.VisualiserUneTourneeDTO;
import fr.uga.l3miage.integrator.services.VisualiserTourneeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class VisualiserTourneeController implements VisualiserTourneeEndpoints {

    private final VisualiserTourneeService service;
    private final TourneeMapper tourneeMapper;
    private final EntrepotMapper entrepotMapper;

    @Override
    public VisualiserUneTourneeDTO getVisuTournee(@PathVariable String reference) {
        return tourneeMapper.toResponse(service.findTournee(reference));
    }

    @Override
    public Set<EntrepotDTO> getAllEntrepot() {
        return entrepotMapper.toDTOSet(service.getAllEntrepots());
    }

    @Override
    public JourneeDTO getJourneeByEntrepotAndDate(String nomEntrepot, String dateJournee) {
        return null;
    }
}
