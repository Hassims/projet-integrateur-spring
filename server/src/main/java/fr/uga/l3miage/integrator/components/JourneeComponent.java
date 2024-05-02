package fr.uga.l3miage.integrator.components;

import fr.uga.l3miage.integrator.models.JourneeEntity;
import fr.uga.l3miage.integrator.repositories.JourneeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class JourneeComponent {
    private final JourneeRepository journeeRepository;

    public  JourneeEntity findByEntrepotNomAndDate(String entrepotNom, LocalDate localDate) {
        return journeeRepository.findAll()
                .stream()
                .filter(j -> j.getEntrepot().getNom().equals(entrepotNom) && j.getDate().isEqual(localDate))
                .findFirst()
                .orElseThrow();
    }


}
