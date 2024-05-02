package fr.uga.l3miage.integrator.services;

import fr.uga.l3miage.integrator.models.EntrepotEntity;
import fr.uga.l3miage.integrator.repositories.EntrepotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EntrepotService {

    private final EntrepotRepository entrepotRepository;

    public Set<EntrepotEntity> getAllEntrepots() {
        return new HashSet<>(entrepotRepository.findAll());
    }
}
