package ma.emsi.StagiaireService.model;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class StagiaireDetails {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String tele;
    private String adresse;
    private String niveau;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Double duree;
    private boolean remuneration;
    private double salaire;
    private Departement departement;
}
