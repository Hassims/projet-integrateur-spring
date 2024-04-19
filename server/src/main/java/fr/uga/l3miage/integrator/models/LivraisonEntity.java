package fr.uga.l3miage.integrator.models;

import fr.uga.l3miage.integrator.enums.EtatsDeLivraison;

import javax.persistence.*;
import java.util.Set;
import java.time.LocalTime;

@Entity
@Table(name ="livraison")
public class LivraisonEntity {
    @Id
    @GeneratedValue
    private Long id;
    private int numero;
    private EtatsDeLivraison etat;
    private LocalTime heureLivraisonEffective;
    private Integer tdmEffectif;
    @ManyToOne
    private TourneeEntity tournee;
    @OneToMany(mappedBy="livraison")
    private Set<CommandeEntity> commandes;
}
