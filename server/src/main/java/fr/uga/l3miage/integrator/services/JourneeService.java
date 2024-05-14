package fr.uga.l3miage.integrator.services;

import fr.uga.l3miage.integrator.components.JourneeComponent;
import fr.uga.l3miage.integrator.exceptions.rest.ConstraintViolationEntityRestException;
import fr.uga.l3miage.integrator.exceptions.rest.NotFoundEntityRestException;
import fr.uga.l3miage.integrator.models.JourneeEntity;
import fr.uga.l3miage.integrator.repositories.JourneeRepository;
import fr.uga.l3miage.integrator.requests.CreerTourneeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class JourneeService {

    private final JourneeRepository journeeRepository;
    private final JourneeComponent journeeComponent;

    public JourneeEntity findJourneeByEntrepotAndDate(String entrepotNom, LocalDate localDate) {
        JourneeEntity journee = journeeRepository.findByEntrepot_NomAndDateEquals(entrepotNom, localDate);
        if (journee == null)
            throw new NotFoundEntityRestException("Journée non trouvée");
        return journee;
    }

    public Set<JourneeEntity> findJourneeByEntrepotAndDateRange(String entrepot, LocalDate dateDepart, LocalDate dateFin) {
        Set<JourneeEntity> entitySet = new HashSet<>();
        if (entrepot == null) {
            if (dateDepart == null) {
                if (dateFin == null)
                    entitySet.addAll(journeeRepository.findAll());
                else
                    entitySet.addAll(journeeRepository.findByDateBefore(dateFin.plusDays(1)));
            } else {
                if (dateFin == null)
                    entitySet.addAll(journeeRepository.findByDateAfter(dateDepart.minusDays(1)));
                else
                    entitySet.addAll(journeeRepository.findByDateBetween(dateDepart, dateFin));
            }
        } else {
            if (dateDepart == null) {
                if (dateFin == null)
                    entitySet.addAll(journeeRepository.findByEntrepot_Nom(entrepot));
                else
                    entitySet.addAll(journeeRepository.findByEntrepot_NomAndDateBefore(entrepot, dateFin.plusDays(1)));
            } else {
                if (dateFin == null)
                    entitySet.addAll(journeeRepository.findByEntrepot_NomAndDateAfter(entrepot, dateDepart.minusDays(1)));
                else
                    entitySet.addAll(journeeRepository.findByEntrepot_NomAndDateBetween(entrepot, dateDepart, dateFin));
            }
        }
        return entitySet;
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
        JourneeEntity journee;
        try {
            journee = journeeComponent.construireJournee(date, nomEntrepot, tournees);
        } catch(ConstraintViolationException e) {
            throw new ConstraintViolationEntityRestException("Violation de contrainte");
        }
        return journee;
    }
}
