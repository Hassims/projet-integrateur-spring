package fr.uga.l3miage.integrator.services;

import fr.uga.l3miage.integrator.components.LivraisonComponent;
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


@Service
@RequiredArgsConstructor
public class LivraisonService {

    private final CommandeRepository commandeRepository;
    private final LivraisonRepository livraisonRepository;
    private final LivraisonComponent livraisonComponent ;
    private final TourneeRepository tourneeRepository ;

    public LivraisonEntity updateEtat(String reference, EtatsDeLivraison etat) {
        LivraisonEntity livraison ;
        try {
            livraison = livraisonComponent.findByReference(reference);
        } catch (Exception e) {
            throw new NotFoundEntityRestException("Livraison non trouvée");
        }

        TourneeEntity tournee = livraison.getTournee();
        EtatsDeTournee tourneeEtat = null;
        switch (etat){
            case  PLANIFIEE -> {}
            case  EN_PARCOURS -> {}
            case  EN_DECHARGEMENT -> tourneeEtat = EtatsDeTournee.EN_DECHARGEMENT;
            case  EN_CLIENTELE -> tourneeEtat = EtatsDeTournee.EN_CLIENTELE;
            case  EN_MONTAGE -> tourneeEtat = EtatsDeTournee.EN_MONTAGE;
            case  EFFECTUEE -> {
                for (CommandeEntity commande : livraison.getCommandes()) {
                    commande.setEtat(EtatsDeCommande.LIVREE);
                    commandeRepository.save(commande);
                }
                // La derniere livraison de la tournee
                if (livraison.getNumero() == tournee.getLivraisons().size()) {
                    tourneeEtat = EtatsDeTournee.EN_RETOUR;
                } else {
                    tourneeEtat = EtatsDeTournee.EN_PARCOURS;
                }
            }
        }
        tournee.setEtat(tourneeEtat);
        tourneeRepository.save(tournee);

        livraison.setEtat(etat);
        return livraisonRepository.save(livraison) ;
    }
}
