package fr.uga.l3miage.integrator.models;


import fr.uga.l3miage.integrator.enums.EtatsDeJournee;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Entity ;
import java.util.Date;
import java.util.Set;


@Entity
public class JourneeEntity extends BaseEntity {

    private EtatsDeJournee etat;
    private Date date;

    @OneToMany(mappedBy="journee")
    private Set<TourneeEntity> tournees;
    @ManyToOne
    private EntrepotEntity entrepot;
}
