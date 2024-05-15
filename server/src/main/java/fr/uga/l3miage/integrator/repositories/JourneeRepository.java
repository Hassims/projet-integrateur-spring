package fr.uga.l3miage.integrator.repositories;

import fr.uga.l3miage.integrator.models.JourneeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;

@Repository
public interface JourneeRepository extends JpaRepository<JourneeEntity, Long> {
    Set<JourneeEntity> findByEntrepot_Nom(String entrepot_nom);
    Set<JourneeEntity> findByDateAfter(LocalDate date);
    Set<JourneeEntity> findByDateBefore(LocalDate date);
    Set<JourneeEntity> findByDateBetween(LocalDate dateDepart, LocalDate dateFin);
    JourneeEntity findByEntrepot_NomAndDateEquals(String nomEntrepot, LocalDate date);
    Set<JourneeEntity> findByEntrepot_NomAndDateAfter(String entrepot_nom, LocalDate date);
    Set<JourneeEntity> findByEntrepot_NomAndDateBefore(String entrepot_nom, LocalDate date);
    Set<JourneeEntity> findByEntrepot_NomAndDateBetween(String entrepot_nom, LocalDate dateDepart, LocalDate dateFin);
}
