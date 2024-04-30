package fr.uga.l3miage.integrator.repositories;

import fr.uga.l3miage.integrator.models.JourneeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JourneeRepository extends JpaRepository<JourneeEntity, Long> {
}
