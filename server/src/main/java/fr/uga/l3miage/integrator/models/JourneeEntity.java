package fr.uga.l3miage.integrator.models;

import fr.uga.l3miage.integrator.enums.EtatsDeJournee;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@Builder
@EqualsAndHashCode(exclude = {"tournees"})
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="journee")
public class JourneeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EtatsDeJournee etat;
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
}
