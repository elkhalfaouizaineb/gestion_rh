package ma.emsi.StagiaireService.service;

import ma.emsi.StagiaireService.model.Departement;
import ma.emsi.StagiaireService.model.Stagiaire;
import ma.emsi.StagiaireService.model.StagiaireDetails;
import ma.emsi.StagiaireService.repository.StagiaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class StagiaireService {
    @Autowired
    StagiaireRepository stagiaireRepository;
    @Autowired
    private RestTemplate restTemplate;

    private final String URLDepartements="http://localhost:8888/SERVICE-DEPARTEMENT";

    public List<StagiaireDetails> findAll(){

        List<Stagiaire> stagiaires=stagiaireRepository.findAll();
        ResponseEntity<Departement[]> response=restTemplate.getForEntity(this.URLDepartements+"/api/Departement/",Departement[].class);
        Departement[] departement=response.getBody();

        return stagiaires.stream().map((Stagiaire e)->
                        mapToStagiaireResponse(e,departement))
                .toList();


    }

    public List<StagiaireDetails> findStagiairesByDepartement(Long idDepartement) {
        List<Stagiaire> stagiaires = stagiaireRepository.findById_Departement(idDepartement);
        return stagiaires.stream().map((Stagiaire e) -> mapToStagiaireResponse(e, getDepartements()))
                .toList();
    }
    public long countStagiairesByDepartement(Long idDepartement) {
        List<StagiaireDetails> stagiaires = findStagiairesByDepartement(idDepartement);
        return stagiaires.size();
        // Ou utilisez employes.stream().count() pour Java 8 et versions ultérieures
    }

    private Departement[] getDepartements() {
        ResponseEntity<Departement[]> response = restTemplate.getForEntity(this.URLDepartements + "/api/Departement/",
                Departement[].class);
        return response.getBody();
    }
    public StagiaireDetails findStagiaireByID(Long id) throws Exception{

        Stagiaire stagiaire= stagiaireRepository.findById(id).orElseThrow(()->new Exception("Stagiaire Invalid"));
        Departement departement =restTemplate.getForObject(this.URLDepartements+"/api/Departement/"+stagiaire.getId_Departement(),Departement.class);


        return StagiaireDetails.builder()
                .id(stagiaire.getId())
                .nom(stagiaire.getNom())
                .prenom(stagiaire.getPrenom())
                .email(stagiaire.getEmail())
                .tele(stagiaire.getTele())
                .adresse(stagiaire.getAdresse())
                .salaire(stagiaire.getSalaire())
                .dateDebut(stagiaire.getDateDebut())
                .dateFin(stagiaire.getDateFin())
                .niveau(stagiaire.getNiveau())
                .duree(stagiaire.getDuree())
                .remuneration(stagiaire.isRemuneration())
                .departement(departement)
                .build();

    }
    private StagiaireDetails mapToStagiaireResponse(Stagiaire stagiaire,Departement[] departements){


        Departement foundDepartement= Arrays.stream(departements)
                .filter(department->department.getId().equals(stagiaire.getId_Departement())).findFirst()
                .orElse(null);

        return StagiaireDetails.builder()
                .id(stagiaire.getId())
                .nom(stagiaire.getNom())
                .prenom(stagiaire.getPrenom())
                .email(stagiaire.getEmail())
                .tele(stagiaire.getTele())
                .adresse(stagiaire.getAdresse())
                .salaire(stagiaire.getSalaire())
                .dateDebut(stagiaire.getDateDebut())
                .dateFin(stagiaire.getDateFin())
                .niveau(stagiaire.getNiveau())
                .duree(stagiaire.getDuree())
                .remuneration(stagiaire.isRemuneration())
                .departement(foundDepartement)
                .build();

    }
}
