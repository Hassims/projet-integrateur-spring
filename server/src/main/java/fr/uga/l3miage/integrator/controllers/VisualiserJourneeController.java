package fr.uga.l3miage.integrator.controllers;

import fr.uga.l3miage.integrator.endpoints.VisualiserJourneeEndpoints;
import fr.uga.l3miage.integrator.mappers.JourneeMapper;
import fr.uga.l3miage.integrator.response.JourneeDTO;
import fr.uga.l3miage.integrator.services.JourneeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class VisualiserJourneeController implements VisualiserJourneeEndpoints {

    private final JourneeService journeeService;
    private final JourneeMapper journeeMapper ;

    @Override
    public Set<JourneeDTO> getJournees(String entrepot, String dateDepart, String dateFin) {
        return journeeMapper.toSetDTO(
                journeeService.findJourneeByEntrepotAndDateRange(
                        entrepot,
                        dateDepart == null ? null : LocalDate.parse(dateDepart),
                        dateFin == null ? null : LocalDate.parse(dateFin)
                )
        );
    }
}
