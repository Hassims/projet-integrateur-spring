package fr.uga.l3miage.integrator.models;

import fr.uga.l3miage.integrator.enums.EtatsDeLivraison;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Set;
import java.time.LocalTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="livraison")
public class LivraisonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Min(0)
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
}
