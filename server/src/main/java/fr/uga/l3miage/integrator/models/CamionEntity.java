package fr.uga.l3miage.integrator.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name ="camion")
public class CamionEntity {
    @Id
    private String immatriculation;
    @OneToMany(mappedBy="camion")
    private Set<TourneeEntity> tournees;
    @ManyToOne
    private EntrepotEntity entrepot;
}
