package fr.uga.l3miage.integrator.controllers;

import fr.uga.l3miage.integrator.endpoints.AjusterJourneeEndpoints;
import fr.uga.l3miage.integrator.mappers.JourneeMapper;
import fr.uga.l3miage.integrator.mappers.LivraisonMapper;
import fr.uga.l3miage.integrator.requests.JourneePatchDateRequest;
import fr.uga.l3miage.integrator.requests.LivraisonPatchNumeroRequest;
import fr.uga.l3miage.integrator.requests.TourneePatchJourneeRequest;
import fr.uga.l3miage.integrator.response.JourneeDTO;
import fr.uga.l3miage.integrator.response.LivraisonDTO;
import fr.uga.l3miage.integrator.response.TourneeDTO;
import fr.uga.l3miage.integrator.services.JourneeService;
import fr.uga.l3miage.integrator.services.LivraisonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class AjusterJourneeController implements AjusterJourneeEndpoints {

    private final JourneeService journeeService ;
    private final JourneeMapper journeeMapper ;
    private final LivraisonService livraisonService ;
    private final LivraisonMapper livraisonMapper ;


    @Override
    public JourneeDTO patchJournee_Date(String reference, JourneePatchDateRequest request) {

        return journeeMapper.toDTO(journeeService.updateJourneeDate( reference, request.getDate()));
    }

    @Override
    public TourneeDTO patchTournee_Journee(String reference, TourneePatchJourneeRequest request) {
        return null;
    }

    @Override
    public LivraisonDTO patchLivraison_Numero(String reference, LivraisonPatchNumeroRequest request) {

        return livraisonMapper.toDTO(livraisonService.updateLivraisonNumero(reference,request.getNumero())) ;
    }
}
