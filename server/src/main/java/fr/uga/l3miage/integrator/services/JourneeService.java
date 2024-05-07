package fr.uga.l3miage.integrator.services;

import fr.uga.l3miage.integrator.exceptions.rest.NotFoundEntityRestException;
import fr.uga.l3miage.integrator.models.JourneeEntity;
import fr.uga.l3miage.integrator.repositories.JourneeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class JourneeService {

    private final JourneeRepository journeeRepository;

    public JourneeEntity findJourneeByEntrepotAndDate(String entrepotNom, LocalDate localDate) {
        JourneeEntity journee = journeeRepository.findByEntrepot_NomAndDateEquals(entrepotNom, localDate);
        if (journee == null)
            throw new NotFoundEntityRestException("Journée non trouvée");
        return journee;
    }
}
