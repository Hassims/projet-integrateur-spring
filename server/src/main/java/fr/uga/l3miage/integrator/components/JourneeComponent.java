package fr.uga.l3miage.integrator.components;


import fr.uga.l3miage.integrator.models.JourneeEntity;
import fr.uga.l3miage.integrator.repositories.JourneeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JourneeComponent {
    private final JourneeRepository journeeRepository ;

    public JourneeEntity findByReference(String reference) throws Exception {

        return journeeRepository.findAll()
                .stream()
                .filter(j -> j.getReference().equals(reference))
                .findFirst()
                .orElseThrow();
    }


}
