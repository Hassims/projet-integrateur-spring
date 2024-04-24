package fr.uga.l3miage.integrator.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="entrepot")
public class EntrepotEntity {
    @Id
    private String nom;
    @Column(nullable = false, length = 1)
    @Pattern(regexp = "^[A-Z]$")
    private String lettre;
    @Column(nullable = false)
    private String adresse;
    @Column(nullable = false, length = 5)
    @Pattern(regexp = "^[0-9]{5}$", message = "Code postal non valide.")
    private String codePostal;
    @Column(nullable = false)
    private String ville;
    @OneToMany(mappedBy="entrepot")
    private Set<StockEntity> stocks;
    @OneToMany(mappedBy="entrepot")
    private Set<EmployeEntity> employes;
    @OneToMany(mappedBy="entrepot")
    private Set<CamionEntity> camions;
    @OneToMany(mappedBy="entrepot")
    private Set<JourneeEntity> journees;
}
