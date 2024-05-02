package fr.uga.l3miage.integrator.repositories;

import fr.uga.l3miage.integrator.models.JourneeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;


public interface JourneeRepository extends JpaRepository<JourneeEntity, Long> {

    JourneeEntity findByEntrepotNomAndDate(String nomEntrepot, LocalDate date);
}
