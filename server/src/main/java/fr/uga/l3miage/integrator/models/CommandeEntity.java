package fr.uga.l3miage.integrator.models;

import fr.uga.l3miage.integrator.enums.EtatsDeCommande ;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Entity ;
import javax.persistence.Table;
import java.util.Set;
import java.time.LocalDateTime;


@Entity
@Table(name ="commande")

public class CommandeEntity extends BaseEntity {

    private String reference;
    private EtatsDeCommande etat;
    private LocalDateTime  dateDeCreation;
    private int note;
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