package fr.uga.l3miage.integrator.components;


import fr.uga.l3miage.integrator.enums.EtatsDeLivraison;
import fr.uga.l3miage.integrator.enums.EtatsDeTournee;
import fr.uga.l3miage.integrator.models.JourneeEntity;
import fr.uga.l3miage.integrator.models.LivraisonEntity;
import fr.uga.l3miage.integrator.models.TourneeEntity;
import fr.uga.l3miage.integrator.repositories.*;
import fr.uga.l3miage.integrator.requests.CreerLivraisonDTO;
import fr.uga.l3miage.integrator.requests.CreerTourneeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JourneeComponent {
    private final JourneeRepository journeeRepository ;
    private final TourneeRepository tourneeRepository;
    private final LivraisonRepository livraisonRepository;
    private final EntrepotRepository entrepotRepository;
    private final CommandeRepository commandeRepository;

    public JourneeEntity findByReference(String reference) throws Exception {

        return journeeRepository.findAll()
                .stream()
                .filter(j -> j.getReference().equals(reference))
                .findFirst()
                .orElseThrow();
    }

    public JourneeEntity construireJournee(LocalDate date, String nomEntrepot, Set<CreerTourneeDTO> tourneeDtos) {

        JourneeEntity journee = JourneeEntity.builder()
                .date(date)
                .entrepot(entrepotRepository.getOne(nomEntrepot))
                .build();
        journee = journeeRepository.save(journee);

        Set<TourneeEntity> setTourneeEntity = new HashSet<>();
        for (CreerTourneeDTO tourneeDTO : tourneeDtos) {
            TourneeEntity tournee = TourneeEntity.builder()
                    .journee(journee)
                    .etat(EtatsDeTournee.PLANIFIEE)
                    .lettre(tourneeDTO.getLettre())
                    .build();

            tourneeRepository.save(tournee);
            setTourneeEntity.add(tournee);

            List<LivraisonEntity> livraisonEntitySet = new ArrayList<>();
            int numero = 1;
            for (CreerLivraisonDTO livraisonDTO : tourneeDTO.getLivraisons()) {
                LivraisonEntity livraison = LivraisonEntity.builder()
                        .etat(EtatsDeLivraison.PLANIFIEE)
                        .numero(numero)
                        .tournee(tournee)
                        .commandes(livraisonDTO.getCommandes().stream().map(commandeRepository::findByReference).collect(Collectors.toSet()))
                        .build();
                livraisonRepository.save(livraison);
                livraisonEntitySet.add(livraison);
                numero++;
            }
            tournee.setLivraisons(livraisonEntitySet);
            tourneeRepository.save(tournee);
        }
        journee.setTournees(setTourneeEntity);
        return journeeRepository.save(journee);
    }
}
