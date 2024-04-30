package fr.uga.l3miage.integrator.services;

import fr.uga.l3miage.integrator.models.JourneeEntity;
import fr.uga.l3miage.integrator.repositories.JourneeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class JourneeService {

    private final JourneeRepository journeeRepository;

    public JourneeEntity findJourneeByEntrepotAndDate(String entrepotNom, LocalDate date) {
        return null;
    }
}
