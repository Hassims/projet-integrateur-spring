package fr.uga.l3miage.integrator.models;


import fr.uga.l3miage.integrator.enums.EtatsDeLivraison;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Entity ;
import java.util.Set;
import java.time.LocalTime;



@Entity
public class LivraisonEntity extends BaseEntity {

    private int numero;
    private EtatsDeLivraison etat;
    private LocalTime  heureLivraisonEffective;
    private Integer tdmEffectif;

    @ManyToOne
    private TourneeEntity tournee;
    @OneToMany(mappedBy="livraison")
    private Set<CommandeEntity> commandes;
}
