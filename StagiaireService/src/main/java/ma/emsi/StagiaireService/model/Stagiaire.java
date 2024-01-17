package ma.emsi.StagiaireService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Stagiaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Long id_Departement;
}
