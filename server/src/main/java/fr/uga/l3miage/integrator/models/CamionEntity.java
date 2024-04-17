package fr.uga.l3miage.integrator.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CamionEntity {
    @Id
    String immatriculation;
    Double latitude;
    Double longitude;
}

