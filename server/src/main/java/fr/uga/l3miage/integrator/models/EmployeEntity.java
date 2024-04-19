package fr.uga.l3miage.integrator.models;

import fr.uga.l3miage.integrator.enums.Emploi ;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name ="employe")
public class EmployeEntity {
    @Id
    private String trigramme;
    private String email;
    private String prenom;
    private String nom;
    private String telephone;
    private Emploi emploi;
    @ManyToOne
    private EntrepotEntity entrepot;
    @ManyToMany(mappedBy="livreurs")
    private Set<TourneeEntity> tournees;
}
