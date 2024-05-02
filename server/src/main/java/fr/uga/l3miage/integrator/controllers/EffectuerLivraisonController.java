package fr.uga.l3miage.integrator.controllers;

import fr.uga.l3miage.integrator.endpoints.EffectuerLivraisonEndpoints;
import fr.uga.l3miage.integrator.requests.LivraisonPatchEtatRequest;
import fr.uga.l3miage.integrator.response.LivraisonDTO;

public class EffectuerLivraisonController implements EffectuerLivraisonEndpoints {
    @Override
    public LivraisonDTO patchEtat(String reference, LivraisonPatchEtatRequest request) {
        return null;
    }
}
