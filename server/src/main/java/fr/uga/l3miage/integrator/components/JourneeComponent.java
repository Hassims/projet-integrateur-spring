package fr.uga.l3miage.integrator.components;

import fr.uga.l3miage.integrator.models.EntrepotEntity;
import fr.uga.l3miage.integrator.models.JourneeEntity;
import fr.uga.l3miage.integrator.models.TourneeEntity;
import fr.uga.l3miage.integrator.repositories.EntrepotRepository;
import fr.uga.l3miage.integrator.repositories.JourneeRepository;
import fr.uga.l3miage.integrator.repositories.TourneeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JourneeComponent {
    private  final JourneeRepository journeeRepository;
    private  final EntrepotRepository entrepotRepository ;

    public  Optional<JourneeEntity> findByEntrepotNomAndDate(String entrepotNom, LocalDate localDate) {
        for (JourneeEntity journee :  journeeRepository.findAll() ){
            for (EntrepotEntity entrepot : entrepotRepository.findAll()) {
                if (journee.getDate().equals(localDate) && entrepot.getNom().equals(entrepotNom)) {
                    return Optional.of(journee);
                }
            }
        }
        return Optional.empty();
    }


}
