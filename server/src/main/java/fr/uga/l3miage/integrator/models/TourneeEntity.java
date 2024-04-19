package fr.uga.l3miage.integrator.models;

import fr.uga.l3miage.integrator.enums.EtatsDeTournee ;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Table(name ="tournee")
public class TourneeEntity {
    @Id
    @GeneratedValue
    private Long id;
    private EtatsDeTournee etat;
    private String lettre;
    private Integer tdmEffectif;
    @ManyToOne
    private CamionEntity camion;
    @ManyToOne
    private JourneeEntity journee;
    @OneToMany(mappedBy="tournee")
    private List<LivraisonEntity> livraisons;
    @ManyToMany
    private Set<EmployeEntity> livreurs;

    public String getLettre() {
        return lettre;
    }
    public JourneeEntity getJournee(){
        return  journee ;
    }
}
