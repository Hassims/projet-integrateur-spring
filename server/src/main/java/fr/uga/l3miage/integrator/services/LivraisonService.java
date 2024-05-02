package fr.uga.l3miage.integrator.services;

import fr.uga.l3miage.integrator.enums.EtatsDeLivraison;
import fr.uga.l3miage.integrator.exceptions.rest.NotFoundEntityRestException;
import fr.uga.l3miage.integrator.models.LivraisonEntity;
import fr.uga.l3miage.integrator.repositories.LivraisonRepository;

public class LivraisonService {

    private LivraisonRepository livraisonRepository;

    public LivraisonEntity updateEtat(String reference, EtatsDeLivraison etat) {
        throw new NotFoundEntityRestException("Livraison non trouvée.");
    }
}
