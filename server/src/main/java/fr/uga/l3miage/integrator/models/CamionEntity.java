package fr.uga.l3miage.integrator.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Entity
@Data
@Table(name ="camion")
public class CamionEntity {
    @Id
    @Column(length = 9)
    @Pattern(regexp = "^[A-Z]{2}-[0-9]{3}-[A-Z]{2}$", message = "Format d'immatriculation non valide.")
    private String immatriculation;
    @OneToMany(mappedBy="camion")
    private Set<TourneeEntity> tournees;
    @ManyToOne
    private EntrepotEntity entrepot;
}
