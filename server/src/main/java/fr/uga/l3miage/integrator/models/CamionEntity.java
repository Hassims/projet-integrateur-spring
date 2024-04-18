package fr.uga.l3miage.integrator.models;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Entity ;
import java.util.Set;


@Entity
public class CamionEntity extends BaseEntity {

    private String immatriculation;
    private double latitude;
    private double longitude;

    @OneToMany(mappedBy="camion")
    public Set<TourneeEntity> tournees;
    @ManyToOne
    private EntrepotEntity entrepot;
}
