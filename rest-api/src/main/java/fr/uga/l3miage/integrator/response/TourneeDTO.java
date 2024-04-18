package fr.uga.l3miage.integrator.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TourneeDTO {
    private String reference;
    private String lettre;
}
