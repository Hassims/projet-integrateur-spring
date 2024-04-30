package fr.uga.l3miage.integrator.response;

import fr.uga.l3miage.integrator.enums.EtatsDeJournee;

import java.time.LocalDate;
import java.util.Set;

public class JourneeDTO {
    private Long id;
    private EtatsDeJournee etat;
    private LocalDate date;
    private Set<String> tournees;
    private String entrepot;
}
