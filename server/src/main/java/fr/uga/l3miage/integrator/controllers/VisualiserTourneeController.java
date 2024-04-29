package fr.uga.l3miage.integrator.controllers;

import fr.uga.l3miage.integrator.endpoints.VisualiserTourneeEndpoints;
import fr.uga.l3miage.integrator.exceptions.rest.NotFoundEntityRestException;
import fr.uga.l3miage.integrator.mappers.EntrepotMapper;
import fr.uga.l3miage.integrator.mappers.TourneeMapper;
import fr.uga.l3miage.integrator.models.EntrepotEntity;
import fr.uga.l3miage.integrator.models.TourneeEntity;
import fr.uga.l3miage.integrator.response.EntrepotsDTO;
import fr.uga.l3miage.integrator.response.VisualiserUneTourneeDTO;
import fr.uga.l3miage.integrator.services.VisualiserTourneeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class VisualiserTourneeController implements VisualiserTourneeEndpoints {

    private final VisualiserTourneeService service;
    private final TourneeMapper mapper;
    private final EntrepotMapper entrepotMapper;

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

    @Override
    public ResponseEntity<Set<EntrepotsDTO>> getAllEntrepot() {
        Set<EntrepotEntity> set = service.getAllEntrepots();
        Set<EntrepotsDTO> setDTO = new HashSet<>();
        for (EntrepotEntity entrepot : set) {
            setDTO.add(entrepotMapper.toDTO(entrepot));
        }
        return ResponseEntity.ok(setDTO);
    }


}
