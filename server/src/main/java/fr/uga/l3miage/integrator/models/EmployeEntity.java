package fr.uga.l3miage.integrator.models;

import fr.uga.l3miage.integrator.enums.Emploi ;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Entity ;
import java.util.Set;


@Entity
public class EmployeEntity extends BaseEntity {

    private String trigramme;
    private String email;
    private String prenom;
    private String nom;
    private String photo;
    private String telephone;
    private Emploi emploi;

    @OneToOne(mappedBy="planificateur")
    private EntrepotEntity entrepot;
    @ManyToMany(mappedBy="livreurs")
    private Set<TourneeEntity> tournees;

}
