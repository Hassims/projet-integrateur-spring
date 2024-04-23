package fr.uga.l3miage.integrator.models;

import fr.uga.l3miage.integrator.enums.Emploi ;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Entity
@Data
@Table(name ="employe")
public class EmployeEntity {
    @Id
    private String trigramme;
    @Column(nullable = false)
    private String prenom;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false, length = 10)
    @Pattern(regexp = "^[0-9]{10}$", message = "Numéro de téléphone non valide.")
    private String telephone;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Emploi emploi;
    @ManyToOne
    private EntrepotEntity entrepot;
    @ManyToMany(mappedBy="livreurs")
    private Set<TourneeEntity> tournees;
}
