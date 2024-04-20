package fr.uga.l3miage.integrator.models;

import fr.uga.l3miage.integrator.enums.EtatsDeJournee;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name ="journee")
public class JourneeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private EtatsDeJournee etat;
    private Date date;
    @OneToMany(mappedBy="journee")
    private Set<TourneeEntity> tournees;
    @ManyToOne
    private EntrepotEntity entrepot;
}
