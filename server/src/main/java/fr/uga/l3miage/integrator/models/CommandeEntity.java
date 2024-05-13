package fr.uga.l3miage.integrator.models;

import fr.uga.l3miage.integrator.enums.EtatsDeCommande ;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Set;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@EqualsAndHashCode(exclude = {"lignes"})
@ToString(exclude = {"lignes"})
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="commande")
public class CommandeEntity {
    @Id
    private String reference;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EtatsDeCommande etat;
    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime dateCreation;
    @Min(1)
    @Max(5)
    private Integer note;
    @Column(length = 1000)
    private String commentaire;
    private Integer tddEffectif;
    private Integer tdmEffectif;
    @ManyToOne
    private ClientEntity client;
    @OneToMany(mappedBy="commande")
    private Set<LigneEntity> lignes;
    @ManyToOne
    private LivraisonEntity livraison;

    public double getMontant() {
        return lignes.stream().map(LigneEntity::getMontant).reduce(0.0, Double::sum);
    }

    public Integer getTempsMontageTheorique() {
        return lignes.stream()
                .map(LigneEntity::getTempsMontageTheorique)
                .reduce(0, (a, b) -> a + (b == null ? 0 : b));
    }
}