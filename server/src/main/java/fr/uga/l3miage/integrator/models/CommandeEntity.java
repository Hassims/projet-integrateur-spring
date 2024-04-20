package fr.uga.l3miage.integrator.models;

import fr.uga.l3miage.integrator.enums.EtatsDeCommande ;

import javax.persistence.*;
import java.util.Set;
import java.time.LocalDateTime;

@Entity
@Table(name ="commande")
public class CommandeEntity {
    @Id
    private String reference;
    @Enumerated(EnumType.STRING)
    private EtatsDeCommande etat;
    private LocalDateTime dateCreation;
    private Integer note;
    private String commentaire;
    private Integer tddEffectif;
    private Integer tdmEffectif;
    @ManyToOne
    private ClientEntity client;
    @OneToMany(mappedBy="commande")
    private Set<LigneEntity> lignes;
    @ManyToOne
    private LivraisonEntity livraison;
}