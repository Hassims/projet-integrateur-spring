package fr.uga.l3miage.integrator.services;

import fr.uga.l3miage.integrator.enums.EtatsDeCommande;
import fr.uga.l3miage.integrator.models.CommandeEntity;
import fr.uga.l3miage.integrator.repositories.CommandeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CommandeService {

    private final CommandeRepository commandeRepository;

    public Set<CommandeEntity> findAllByEtat(EtatsDeCommande etat) {
        return etat == null ?
                new HashSet<>(commandeRepository.findAll()) :
                commandeRepository.findAllByEtat(etat);
    }
}
