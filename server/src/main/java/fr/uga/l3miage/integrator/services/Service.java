package fr.uga.l3miage.integrator.services;

import fr.uga.l3miage.integrator.exceptions.rest.NotFoundEntityRestException;
import fr.uga.l3miage.integrator.models.TourneeEntity;
import fr.uga.l3miage.integrator.repositories.TourneeRepository;
import fr.uga.l3miage.integrator.response.TourneeDTO;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class Service {
    private final TourneeRepository tourneeRepository;

    public Set<TourneeEntity> allTournees(){
        try {
            return new HashSet(tourneeRepository.findAll());
        } catch (Exception e) {
            throw e;
        }
    }

    public TourneeEntity findTournee(String reference) {
        Optional<TourneeEntity> tournee = tourneeRepository.findById(reference);

        if (tournee.isPresent())
            return tournee.get();
        else
            throw new NotFoundEntityRestException("Tournée non trouvée");
    }

}
