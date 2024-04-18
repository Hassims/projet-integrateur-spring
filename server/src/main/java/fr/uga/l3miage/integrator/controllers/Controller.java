package fr.uga.l3miage.integrator.controllers;

import fr.uga.l3miage.integrator.endpoints.VisualiserUneTourneeEndpoints;
import fr.uga.l3miage.integrator.mappers.TourneeMapper;
import fr.uga.l3miage.integrator.models.TourneeEntity;
import fr.uga.l3miage.integrator.response.TourneeDTO;
import fr.uga.l3miage.integrator.services.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@org.springframework.stereotype.Controller
public class Controller implements VisualiserUneTourneeEndpoints {

    private final Service service;
    private final TourneeMapper mapper;

    @Override
    public Set<TourneeDTO> getAllTournees() {
        Set<TourneeEntity> entities = service.allTournees();
        Set<TourneeDTO> dtos = new HashSet<>();

        for (var entity : entities) {
            dtos.add(mapper.toResponse(entity));
        }

        return dtos;
    }

    /**
     * @return 
     */
    @Override
    public Set<TourneeDTO> getAllEntrepots() {
        return null;
    }

    @Override
    public TourneeDTO getTournee(String reference) {
        TourneeEntity tournee = service.findTournee(reference);

        return mapper.toResponse(tournee);
    }

    /**
     * @param reference 
     * @return
     */
    @Override
    public TourneeDTO getTourneesJournee(String reference) {
        return null;
    }

    /**
     * @param nom 
     * @param date
     * @return
     */
    @Override
    public TourneeDTO getJourneeEntrepot(String nom, Date date) {
        return null;
    }

    /**
     * @param reference 
     * @return
     */
    @Override
    public TourneeDTO getVisuTournee(String reference) {
        return null;
    }
}
