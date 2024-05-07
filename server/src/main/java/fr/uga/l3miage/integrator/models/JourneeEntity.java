package fr.uga.l3miage.integrator.models;

import fr.uga.l3miage.integrator.enums.EtatsDeJournee;
import fr.uga.l3miage.integrator.enums.EtatsDeTournee;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@Builder
@EqualsAndHashCode(exclude = {"tournees"})
@ToString(exclude = {"tournees"})
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="journee")
public class JourneeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate date;
    @OneToMany(mappedBy="journee")
    private Set<TourneeEntity> tournees;
    @ManyToOne
    private EntrepotEntity entrepot;

    public String getReference() {
        String day = String.format("%03d", date.getDayOfYear());
        return "j" + day + entrepot.getLettre();
    }

    public double getMontant() {
        return tournees.stream().map(TourneeEntity::getMontant).reduce(0.0, Double::sum);
    }

    public Integer getTempsMontageTheorique() {
        return tournees.stream().map(TourneeEntity::getTempsMontageTheorique).reduce(0, Integer::sum);
    }

    public EtatsDeJournee getEtat() {    // TODO à tester

        // SI toutes les tournées sont planifiées
        if (tournees.stream().allMatch(tournee -> tournee.getEtat() == EtatsDeTournee.PLANIFIEE))
            return EtatsDeJournee.PLANIFIEE;

        // SINON SI toutes les tournées sont effectuées
        else if (tournees.stream().allMatch(tournee -> tournee.getEtat() == EtatsDeTournee.EFFECTUEE))
            return  EtatsDeJournee.EFFECTUEE;

        return EtatsDeJournee.EN_COURS;
    }
}
