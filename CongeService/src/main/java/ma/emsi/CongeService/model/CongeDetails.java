package ma.emsi.CongeService.model;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class CongeDetails {
    private Long id;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private TypeConge typeConge;
    private Employe employe;
}
