package fr.uga.l3miage.integrator.models;

import fr.uga.l3miage.integrator.enums.EtatsDeTournee ;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@EqualsAndHashCode(exclude = {"livreurs", "livraisons"})
@ToString(exclude = {"livreurs", "livraisons"})
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="tournee")
public class TourneeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EtatsDeTournee etat;
    @Column(nullable = false, length = 1)
    @Pattern(regexp = "^[A-Z]$", message = "Lettre non valide.")
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

    public String getReference() {
        return "t" + journee.getReference().substring(1) + "-" + lettre;
    }

    public double getMontant() {
        return livraisons.stream().map(LivraisonEntity::getMontant).reduce(0.0, Double::sum);
    }

    public Integer getTempsMontageTheorique() {
        return livraisons.stream().map(LivraisonEntity::getTempsMontageTheorique).reduce(0, Integer::sum);
    }
}
