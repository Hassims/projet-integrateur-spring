package fr.uga.l3miage.integrator.models;

import fr.uga.l3miage.integrator.enums.EtatsDeLivraison;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Set;
import java.time.LocalTime;

@Entity
@Data
@EqualsAndHashCode(exclude = {"commandes"})
@ToString(exclude = {"commandes"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="livraison")
public class LivraisonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Min(1)
    private int numero;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EtatsDeLivraison etat;
    @Column(columnDefinition = "TIME")
    private LocalTime heureLivraisonEffective;
    private Integer tdmEffectif;
    @ManyToOne
    private TourneeEntity tournee;
    @OneToMany(mappedBy="livraison")
    private Set<CommandeEntity> commandes;

    public String getReference() {
        return "l" + tournee.getReference().substring(1) + this.numero;
    }

    public double getMontant() {
        return commandes.stream().map(CommandeEntity::getMontant).reduce(0.0, Double::sum);
    }

    public Integer getTempsMontageTheorique() {
        return commandes.stream().map(CommandeEntity::getTempsMontageTheorique).reduce(0, Integer::sum);
    }
}
