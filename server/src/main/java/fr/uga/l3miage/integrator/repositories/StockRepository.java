package fr.uga.l3miage.integrator.repositories;

import fr.uga.l3miage.integrator.models.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<StockEntity, Long> {
}
