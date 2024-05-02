package fr.uga.l3miage.integrator.repositories;

import fr.uga.l3miage.integrator.models.CamionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CamionRepository extends JpaRepository<CamionEntity, String> {
}
