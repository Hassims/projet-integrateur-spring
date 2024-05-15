package fr.uga.l3miage.integrator.services;

import fr.uga.l3miage.integrator.components.JourneeComponent;
import fr.uga.l3miage.integrator.components.TourneeComponent;
import fr.uga.l3miage.integrator.enums.EtatsDeCommande;
import fr.uga.l3miage.integrator.enums.EtatsDeLivraison;
import fr.uga.l3miage.integrator.enums.EtatsDeTournee;
import fr.uga.l3miage.integrator.exceptions.rest.NotFoundEntityRestException;
import fr.uga.l3miage.integrator.models.CommandeEntity;
import fr.uga.l3miage.integrator.models.JourneeEntity;
import fr.uga.l3miage.integrator.models.LivraisonEntity;
import fr.uga.l3miage.integrator.models.TourneeEntity;
import fr.uga.l3miage.integrator.repositories.CommandeRepository;
import fr.uga.l3miage.integrator.repositories.LivraisonRepository;
import fr.uga.l3miage.integrator.repositories.TourneeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TourneeService {

    private final TourneeRepository tourneeRepository;
    private final LivraisonRepository livraisonRepository;
    private final CommandeRepository commandeRepository;
    private final TourneeComponent tourneeComponent;
    private final JourneeComponent journeeComponent ;

    public TourneeEntity findByReference(String reference) {
        try {
            return tourneeComponent.findByReference(reference);
        } catch(Exception e) {
            throw new NotFoundEntityRestException("Tournée non trouvée");
        }
    }

    public TourneeEntity updateTourneeEtat(String reference, EtatsDeTournee etat) {

        TourneeEntity tournee;
        try {
            tournee = tourneeComponent.findByReference(reference);
        } catch (Exception e) {
            throw new NotFoundEntityRestException("Tournée non trouvée");
        }

        switch(etat) {
            case PLANIFIEE -> {}
            case EN_CHARGEMENT -> {}
            case EN_PARCOURS -> {
                for (LivraisonEntity livraison : tournee.getLivraisons()) {
                    for (CommandeEntity commande : livraison.getCommandes()) {
                        commande.setEtat(EtatsDeCommande.EN_LIVRAISON);
                        commandeRepository.save(commande);
                    }
                    livraison.setEtat(EtatsDeLivraison.EN_PARCOURS);
                    livraisonRepository.save(livraison);
                }
            }
            case EN_DECHARGEMENT -> {}
            case EN_CLIENTELE -> {}
            case EN_MONTAGE -> {}
            case EN_RETOUR -> {}
            case EFFECTUEE -> {}
        }
        tournee.setEtat(etat);
        return tourneeRepository.save(tournee);
    }

    public TourneeEntity updateTourneeJournee(String reference, String referencejournee) {

        TourneeEntity tournee;
        JourneeEntity journee ;
        try {
            tournee = tourneeComponent.findByReference(reference);
        } catch (Exception e) {
            throw new NotFoundEntityRestException("Tournée non trouvée");
        }

        try {
            journee = journeeComponent.findByReference(referencejournee) ;

        } catch (Exception e) {
            throw new NotFoundEntityRestException("journée non trouvée");
        }


        tournee.setJournee(journee);
        return tourneeRepository.save(tournee);

    }
}
