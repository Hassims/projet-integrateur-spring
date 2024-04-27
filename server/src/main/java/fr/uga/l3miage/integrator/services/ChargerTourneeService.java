package fr.uga.l3miage.integrator.services;

import fr.uga.l3miage.integrator.components.TourneeComponent;
import fr.uga.l3miage.integrator.enums.EtatsDeCommande;
import fr.uga.l3miage.integrator.enums.EtatsDeLivraison;
import fr.uga.l3miage.integrator.enums.EtatsDeTournee;
import fr.uga.l3miage.integrator.exceptions.rest.NotFoundEntityRestException;
import fr.uga.l3miage.integrator.models.CommandeEntity;
import fr.uga.l3miage.integrator.models.LivraisonEntity;
import fr.uga.l3miage.integrator.models.TourneeEntity;
import fr.uga.l3miage.integrator.repositories.CommandeRepository;
import fr.uga.l3miage.integrator.repositories.LivraisonRepository;
import fr.uga.l3miage.integrator.repositories.TourneeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChargerTourneeService {

    private final TourneeRepository tourneeRepository;
    private final LivraisonRepository livraisonRepository;
    private final CommandeRepository commandeRepository;
    private final TourneeComponent tourneeComponent;

    public TourneeEntity updateTourneeEtat(String reference, EtatsDeTournee etat) throws NotFoundEntityRestException {
        Optional<TourneeEntity> optTournee = tourneeComponent.findByReference(reference);

        if (optTournee.isPresent()) {
            TourneeEntity tournee = optTournee.get();
            switch(etat) {
                case planifiee -> {}
                case enChargement -> {}
                case enParcours -> {
                    for (LivraisonEntity livraison : tournee.getLivraisons()) {
                        for (CommandeEntity commande : livraison.getCommandes()) {
                            commande.setEtat(EtatsDeCommande.enLivraison);
                            commandeRepository.save(commande);
                        }
                        livraison.setEtat(EtatsDeLivraison.enParcours);
                        livraisonRepository.save(livraison);
                    }
                }
                case enDechargement -> {}
                case enClientele -> {}
                case enMontage -> {}
                case enRetour -> {}
                case effectuee -> {}
            }
            tournee.setEtat(etat);
            return tourneeRepository.save(tournee);
        }
        else {
            throw new NotFoundEntityRestException("Tournée non trouvée");
        }
    }
}
