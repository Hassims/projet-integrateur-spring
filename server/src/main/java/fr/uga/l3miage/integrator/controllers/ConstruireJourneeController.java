package fr.uga.l3miage.integrator.controllers;

import fr.uga.l3miage.integrator.endpoints.ConstruireJourneeEndpoints;
import fr.uga.l3miage.integrator.enums.EtatsDeCommande;
import fr.uga.l3miage.integrator.mappers.CommandeMapper;
import fr.uga.l3miage.integrator.mappers.JourneeMapper;
import fr.uga.l3miage.integrator.requests.ConstruireJourneePostRequest;
import fr.uga.l3miage.integrator.response.CommandeDTO;
import fr.uga.l3miage.integrator.response.JourneeDTO;
import fr.uga.l3miage.integrator.services.CommandeService;
import fr.uga.l3miage.integrator.services.JourneeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class ConstruireJourneeController implements ConstruireJourneeEndpoints {

    private final JourneeService journeeService;
    private final CommandeService commandeService;
    private final JourneeMapper journeeMapper;
    private final CommandeMapper commandeMapper;

    @Override
    public JourneeDTO postJournee(ConstruireJourneePostRequest request) {
        return journeeMapper.toDTO(journeeService.construireJournee(request.getDate(), request.getNomEntrepot(), request.getTournees()));
    }

    @Override
    public Set<CommandeDTO> getCommandes(EtatsDeCommande etat) {
        return commandeMapper.toSetDTO(commandeService.findAllByEtat(etat));
    }
}
