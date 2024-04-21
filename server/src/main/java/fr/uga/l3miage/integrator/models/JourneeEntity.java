package fr.uga.l3miage.integrator.models;

import fr.uga.l3miage.integrator.enums.EtatsDeJournee;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@Table(name ="journee")
public class JourneeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EtatsDeJournee etat;
    @Column(nullable = false)
    private Date date;
    @OneToMany(mappedBy="journee")
    private Set<TourneeEntity> tournees;
    @ManyToOne
    private EntrepotEntity entrepot;
}
