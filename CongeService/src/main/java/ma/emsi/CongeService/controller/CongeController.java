package ma.emsi.CongeService.controller;

import ma.emsi.CongeService.exception.ResourceNotFoundException;
import ma.emsi.CongeService.model.Conge;
import ma.emsi.CongeService.model.CongeDetails;
import ma.emsi.CongeService.repository.CongeRepository;
import ma.emsi.CongeService.service.CongeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/Conges/")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8888"}, allowedHeaders = "*")
public class CongeController {
    @Autowired
    CongeRepository congeRepository;
    @Autowired
    private CongeService congeService;


    @PostMapping("/Ajout")
    public Conge CreateConge(@RequestBody Conge e) {
        return congeRepository.save(e);
    }

    @PutMapping("/Modifier/{id}")
    public ResponseEntity<Conge> modifierConge(@PathVariable Long id, @RequestBody Conge nouveauConge) {
        Conge conge = congeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Conge non trouvé avec l'ID : " + id));

        conge.setDateDebut(nouveauConge.getDateDebut());
        conge.setDateFin(nouveauConge.getDateFin());
        conge.setTypeConge(nouveauConge.getTypeConge());
        conge.setId_Employe(nouveauConge.getId_Employe());

        Conge updatedConge = congeRepository.save(conge);
        return ResponseEntity.ok(updatedConge);
    }

    @DeleteMapping("/{id}")
    public void deleteConge(@PathVariable Long id) {
        congeRepository.deleteById(id);
    }

    @GetMapping
    public List<CongeDetails> GetAllConges(){
        return congeService.findAll();
    }

    @GetMapping("/{id}")
    public CongeDetails getCongeById(@PathVariable Long id) throws Exception{
        return congeService.findCongeByID(id);
    }
    @GetMapping("/employe/{employeId}")
    public List<CongeDetails> getCongesByEmployeId(@PathVariable Long employeId) {
        return congeService.findCongesByEmployeId(employeId);
    }
}
