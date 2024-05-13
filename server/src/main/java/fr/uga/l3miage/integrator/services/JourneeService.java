package fr.uga.l3miage.integrator.services;

import fr.uga.l3miage.integrator.components.JourneeComponent;
import fr.uga.l3miage.integrator.enums.EtatsDeJournee;
import fr.uga.l3miage.integrator.enums.EtatsDeTournee;
import fr.uga.l3miage.integrator.exceptions.rest.ConstraintViolationEntityRestException;
import fr.uga.l3miage.integrator.exceptions.rest.NotFoundEntityRestException;
import fr.uga.l3miage.integrator.models.JourneeEntity;
import fr.uga.l3miage.integrator.models.LivraisonEntity;
import fr.uga.l3miage.integrator.models.TourneeEntity;
import fr.uga.l3miage.integrator.repositories.EntrepotRepository;
import fr.uga.l3miage.integrator.repositories.JourneeRepository;
import fr.uga.l3miage.integrator.repositories.LivraisonRepository;
import fr.uga.l3miage.integrator.repositories.TourneeRepository;
import fr.uga.l3miage.integrator.requests.CreerLivraisonDTO;
import fr.uga.l3miage.integrator.requests.CreerTourneeDTO;
import fr.uga.l3miage.integrator.response.TourneeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class JourneeService {

    private final JourneeRepository journeeRepository;
    private final TourneeRepository tourneeRepository;
    private final LivraisonRepository livraisonRepository;
    private final EntrepotRepository entrepotRepository;
    private final JourneeComponent journeeComponent;

    public JourneeEntity findJourneeByEntrepotAndDate(String entrepotNom, LocalDate localDate) {
        JourneeEntity journee = journeeRepository.findByEntrepot_NomAndDateEquals(entrepotNom, localDate);
        if (journee == null)
            throw new NotFoundEntityRestException("Journée non trouvée");
        return journee;
    }

    public JourneeEntity updateJourneeDate(String reference, LocalDate date ) {

        JourneeEntity journee;
        try {
            journee = journeeComponent.findByReference(reference);
        } catch (Exception e) {
            throw new NotFoundEntityRestException("Journée non trouvée");
        }

        journee.setDate(date);
        return journeeRepository.save(journee);
    }

    public JourneeEntity construireJournee(LocalDate date, String nomEntrepot, Set<CreerTourneeDTO> tournees) {
        JourneeEntity journee = null;
        try {
            journee = journeeComponent.construireJournee(date, nomEntrepot, tournees);
        } catch(ConstraintViolationException e) {
            throw new ConstraintViolationEntityRestException("Violation de contrainte");
        }
        return journee;
    }
}
