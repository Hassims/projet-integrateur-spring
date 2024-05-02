package fr.uga.l3miage.integrator.services;

import fr.uga.l3miage.integrator.components.LivraisonComponent;
import fr.uga.l3miage.integrator.enums.EtatsDeCommande;
import fr.uga.l3miage.integrator.enums.EtatsDeLivraison;
import fr.uga.l3miage.integrator.enums.EtatsDeTournee;
import fr.uga.l3miage.integrator.exceptions.rest.NotFoundEntityRestException;
import fr.uga.l3miage.integrator.models.CommandeEntity;
import fr.uga.l3miage.integrator.models.LivraisonEntity;
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

        switch (etat){
            case  PLANIFIEE -> {}
            case  EN_PARCOURS -> {}
            case  EN_DECHARGEMENT -> {

                    livraison.getTournee().setEtat(EtatsDeTournee.EN_DECHARGEMENT);
                    tourneeRepository.save(livraison.getTournee()) ;

            }
            case  EN_CLIENTELE -> {
                livraison.getTournee().setEtat(EtatsDeTournee.EN_CLIENTELE);
                tourneeRepository.save(livraison.getTournee()) ;
            }
            case  EN_MONTAGE -> {
                livraison.getTournee().setEtat(EtatsDeTournee.EN_MONTAGE);
                tourneeRepository.save(livraison.getTournee()) ;

            }
            case  EFFECTUEE -> {
                // La derniere livraison de la tournee
                if (livraison.getNumero() == livraison.getTournee().getLivraisons().size()) {
                    livraison.getTournee().setEtat(EtatsDeTournee.EN_RETOUR);
                }else {
                    livraison.getTournee().setEtat(EtatsDeTournee.EN_PARCOURS);
                }
                tourneeRepository.save(livraison.getTournee());

            }

        }

        livraison.setEtat(etat);
        return livraisonRepository.save(livraison) ;
    }
}
