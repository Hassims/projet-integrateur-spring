package fr.uga.l3miage.integrator.models;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Entity ;
import javax.persistence.Table;
import java.util.Set;



@Entity
@Table(name ="entrepot")

public class EntrepotEntity extends BaseEntity {

    private String nom;
    private String lettre;
    private String photo;
    private String adresse;
    private String codePostal;
    private String ville;

    @OneToMany(mappedBy="entrepot")
    private Set<StockEntity> stocks;
    @OneToOne
    private EmployeEntity planificateur;
    @OneToMany(mappedBy="entrepot")
    private Set<CamionEntity> camions;
    @OneToMany(mappedBy="entrepot")
    private Set<JourneeEntity> journees;
}
