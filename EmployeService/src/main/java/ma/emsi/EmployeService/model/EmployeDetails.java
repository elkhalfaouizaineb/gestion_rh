package ma.emsi.EmployeService.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class EmployeDetails {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String tele;
    private String adresse;
    private double salaire;
    private Departement departement;

}
