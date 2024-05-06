package fr.uga.l3miage.integrator.repositories;

import fr.uga.l3miage.integrator.models.JourneeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface JourneeRepository extends JpaRepository<JourneeEntity, Long> {

    JourneeEntity findByEntrepot_NomAndDateEquals(String nomEntrepot, LocalDate date);
}
