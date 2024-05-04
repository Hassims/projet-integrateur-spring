package fr.uga.l3miage.integrator.controllers;

import fr.uga.l3miage.integrator.endpoints.EffectuerLivraisonEndpoints;
import fr.uga.l3miage.integrator.mappers.LivraisonMapper;
import fr.uga.l3miage.integrator.requests.LivraisonPatchEtatRequest;
import fr.uga.l3miage.integrator.response.LivraisonDTO;
import fr.uga.l3miage.integrator.services.LivraisonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class EffectuerLivraisonController implements EffectuerLivraisonEndpoints {

    private final LivraisonService livraisonService ;
    private final LivraisonMapper livraisonMapper ;

    @Override
    public LivraisonDTO patchEtat(String reference, LivraisonPatchEtatRequest request) {

        return livraisonMapper.toDTO(livraisonService.updateEtat(reference, request.getEtat()));
    }
}
