package fr.uga.l3miage.integrator.services;

import fr.uga.l3miage.integrator.exceptions.rest.NotFoundEntityRestException;
import fr.uga.l3miage.integrator.models.EntrepotEntity;
import fr.uga.l3miage.integrator.models.JourneeEntity;
import fr.uga.l3miage.integrator.models.TourneeEntity;
import fr.uga.l3miage.integrator.repositories.VisualiserTourneeRepository;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class VisualiserTourneeService {
    private final VisualiserTourneeRepository tourneeRepository;


    public String construireReferenceTournee(TourneeEntity tournee) {
        String ref = construireReferanceJournee(tournee.getJournee()) ;
        return "t"+ ref.substring(1,ref.length())+"-"+tournee.getLettre() ;
    }

    public String construireReferanceJournee (JourneeEntity journee){
        return "j"+journee.getDate().toString()+journee.getEntrepot().getLettre();
    }
    /*
    public Set<TourneeEntity> allTournees(){
        try {
            return new HashSet(tourneeRepository.findAll());
        } catch (Exception e) {
            throw e;
        }
    }
*/
    public TourneeEntity findTournee(String reference) {
        for (TourneeEntity tournee :  tourneeRepository.findAll()){
            if (construireReferenceTournee(tournee) == reference){
                return tournee ;
            }
        }

            throw new NotFoundEntityRestException("Tournée non trouvée");

    }
}
