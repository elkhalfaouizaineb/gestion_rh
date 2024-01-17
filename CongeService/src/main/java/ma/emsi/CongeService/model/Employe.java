package ma.emsi.CongeService.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class Employe {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String tele;
    private String adresse;
    private double salaire;
    private Long id_Departement;
}
