 package fr.uga.l3miage.integrator.mappers;

import fr.uga.l3miage.integrator.models.*;
import fr.uga.l3miage.integrator.response.VisualiserLivraisonDTO;
import fr.uga.l3miage.integrator.response.VisualiserUneTourneeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

 @Mapper
 public interface VisualiserTourneeMapper {

     VisualiserUneTourneeDTO toDTO(TourneeEntity tourneeEntity);

     default String mapJournee(JourneeEntity journee) {
         return journee.getReference();
     }

     default String mapCamion(CamionEntity camion) { return camion.getImmatriculation(); }

     default Set<String> mapSetEmploye(Set<EmployeEntity> employes) {
         return employes.stream().map(EmployeEntity::getTrigramme).collect(Collectors.toSet());
     }

     default List<VisualiserLivraisonDTO> mapLivraisons(List<LivraisonEntity> livraisons) {
         return livraisons.stream().map(l -> Mappers.getMapper(VisualiserLivraisonMapper.class).toDTO(l)).collect(Collectors.toList());
     }
 }
