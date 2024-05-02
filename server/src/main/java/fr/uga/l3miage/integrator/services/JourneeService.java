package fr.uga.l3miage.integrator.services;

import fr.uga.l3miage.integrator.components.JourneeComponent;
import fr.uga.l3miage.integrator.components.TourneeComponent;
import fr.uga.l3miage.integrator.exceptions.rest.NotFoundEntityRestException;
import fr.uga.l3miage.integrator.models.JourneeEntity;
import fr.uga.l3miage.integrator.repositories.EntrepotRepository;
import fr.uga.l3miage.integrator.repositories.JourneeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JourneeService {

    private final JourneeComponent journeeComponent;

    public JourneeEntity findJourneeByEntrepotAndDate(String entrepotNom, LocalDate localDate) {
        try {
            return journeeComponent.findByEntrepotNomAndDate(entrepotNom, localDate);
        } catch(Exception e) {
            throw new NotFoundEntityRestException("Journée non trouvée");
        }
    }
}
