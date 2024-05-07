package fr.uga.l3miage.integrator.controllers;

import fr.uga.l3miage.integrator.endpoints.AjusterJourneeEndpoints;
import fr.uga.l3miage.integrator.requests.JourneePatchDateRequest;
import fr.uga.l3miage.integrator.requests.LivraisonPatchNumeroRequest;
import fr.uga.l3miage.integrator.requests.TourneePatchJourneeRequest;
import fr.uga.l3miage.integrator.response.JourneeDTO;
import fr.uga.l3miage.integrator.response.LivraisonDTO;
import fr.uga.l3miage.integrator.response.TourneeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AjusterJourneeController implements AjusterJourneeEndpoints {
    @Override
    public JourneeDTO patchJournee_Date(String reference, JourneePatchDateRequest request) {
        return null;
    }

    @Override
    public TourneeDTO patchTournee_Journee(String reference, TourneePatchJourneeRequest request) {
        return null;
    }

    @Override
    public LivraisonDTO patchLivraison_Numero(String reference, LivraisonPatchNumeroRequest request) {
        return null;
    }
}
