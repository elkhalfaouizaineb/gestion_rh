package ma.emsi.StagiaireService.controller;

import ma.emsi.StagiaireService.exception.ResourceNotFoundException;
import ma.emsi.StagiaireService.model.Stagiaire;
import ma.emsi.StagiaireService.model.StagiaireDetails;
import ma.emsi.StagiaireService.repository.StagiaireRepository;
import ma.emsi.StagiaireService.service.StagiaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Stagiaires/")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8888"}, allowedHeaders = "*")
public class StagiaireController {
    @Autowired
    StagiaireRepository stagiaireRepository;
    @Autowired
    private StagiaireService stagiaireService;


    @PostMapping("/Ajout")
    public Stagiaire CreateStagiaire(@RequestBody Stagiaire e) {
        return stagiaireRepository.save(e);

    }

    @DeleteMapping("/{id}")
    public void deleteStagiaire(@PathVariable Long id) {
        stagiaireRepository.deleteById(id);
    }

    @PutMapping("/Modifier/{id}")
    public ResponseEntity<Stagiaire> modifierStagiaire(@PathVariable Long id, @RequestBody Stagiaire nouveauStagiaire) {
        Stagiaire stagiaire = stagiaireRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stagiaire non trouvé avec l'ID : " + id));

        stagiaire.setNom(nouveauStagiaire.getNom());
        stagiaire.setPrenom(nouveauStagiaire.getPrenom());
        stagiaire.setEmail(nouveauStagiaire.getEmail());
        stagiaire.setTele(nouveauStagiaire.getTele());
        stagiaire.setAdresse(nouveauStagiaire.getAdresse());
        stagiaire.setSalaire(nouveauStagiaire.getSalaire());
        stagiaire.setDateDebut(nouveauStagiaire.getDateDebut());
        stagiaire.setDateFin(nouveauStagiaire.getDateFin());
        stagiaire.setNiveau(nouveauStagiaire.getNiveau());
        stagiaire.setDuree(nouveauStagiaire.getDuree());
        stagiaire.setRemuneration(nouveauStagiaire.isRemuneration());
        stagiaire.setId_Departement(nouveauStagiaire.getId_Departement());

        Stagiaire updatedStagiaire = stagiaireRepository.save(stagiaire);
        return ResponseEntity.ok(updatedStagiaire);
    }


    @GetMapping
    public List<StagiaireDetails> GetAllStagiaires(){
        return stagiaireService.findAll();
    }

    @GetMapping("/{id}")
    public StagiaireDetails getStagiaireById(@PathVariable Long id) throws Exception{

        return stagiaireService.findStagiaireByID(id);
    }

    @GetMapping("/ParDepartement/{idDepartement}")
    public List<StagiaireDetails> getStagiairesByDepartement(@PathVariable Long idDepartement) {
        return stagiaireService.findStagiairesByDepartement(idDepartement);
    }

    @GetMapping("/ParDepartement/Count/{idDepartement}")
    public ResponseEntity<Long> countStagiairesByDepartement(@PathVariable Long idDepartement) {
        long count = stagiaireService.countStagiairesByDepartement(idDepartement);
        return ResponseEntity.ok(count);
    }
    @GetMapping("/Count/Total")
    public ResponseEntity<Long> countTotalStagiaires() {
        long count = stagiaireRepository.count();
        return ResponseEntity.ok(count);
    }
}
