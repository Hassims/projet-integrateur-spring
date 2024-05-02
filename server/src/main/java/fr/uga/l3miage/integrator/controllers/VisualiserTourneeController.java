package fr.uga.l3miage.integrator.controllers;

import fr.uga.l3miage.integrator.endpoints.VisualiserTourneeEndpoints;
import fr.uga.l3miage.integrator.exceptions.rest.NotFoundEntityRestException;
import fr.uga.l3miage.integrator.mappers.EntrepotMapper;
import fr.uga.l3miage.integrator.mappers.JourneeMapper;
import fr.uga.l3miage.integrator.mappers.TourneeMapper;
import fr.uga.l3miage.integrator.models.JourneeEntity;
import fr.uga.l3miage.integrator.response.EntrepotDTO;
import fr.uga.l3miage.integrator.response.JourneeDTO;
import fr.uga.l3miage.integrator.response.VisualiserUneTourneeDTO;
import fr.uga.l3miage.integrator.services.EntrepotService;
import fr.uga.l3miage.integrator.services.JourneeService;
import fr.uga.l3miage.integrator.services.TourneeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class VisualiserTourneeController implements VisualiserTourneeEndpoints {

    private final EntrepotService entrepotService;
    private final JourneeService journeeService;
    private final TourneeService tourneeService;
    private final TourneeMapper tourneeMapper;
    private final EntrepotMapper entrepotMapper;
    private final JourneeMapper journeeMapper ;

    @Override
    public VisualiserUneTourneeDTO getVisuTournee(@PathVariable String reference) {
        return tourneeMapper.toResponse(tourneeService.findTournee(reference));
    }

    @Override
    public Set<EntrepotDTO> getAllEntrepot() {

        return entrepotMapper.toDTOSet(entrepotService.getAllEntrepots());
    }

    @Override
    public JourneeDTO getJourneeByEntrepotAndDate(String nomEntrepot, String date) throws NotFoundEntityRestException  {
        LocalDate localDate = LocalDate.parse(date) ;
        Optional<JourneeEntity> journee = journeeService.findJourneeByEntrepotAndDate(nomEntrepot, localDate) ;

        if(journee.isPresent()){
            return journeeMapper.toDTO(journee.get());
        }else {
            throw new NotFoundEntityRestException("Tournée non trouvée");
        }
    }
}
