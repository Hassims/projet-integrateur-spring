package fr.uga.l3miage.integrator.repositories;

import fr.uga.l3miage.integrator.models.TourneeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface VisualiserTourneeRepository extends JpaRepository<TourneeEntity, Long> {
    Set<TourneeEntity> findTourneeEntityByReferance (Date journeeRef, String lettreEntrepot, String lettre);
}
