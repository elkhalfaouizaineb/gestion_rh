package ma.emsi.EmployeService.controller;

import java.util.List;

import ma.emsi.EmployeService.model.EmployeDetails;
import ma.emsi.EmployeService.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.emsi.EmployeService.exception.ResourceNotFoundException;
import ma.emsi.EmployeService.model.Employe;
import ma.emsi.EmployeService.repository.EmployeRepository;

@RestController
@RequestMapping("/api/Employes/")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8888"}, allowedHeaders = "*")
public class EmployrController {
	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	private EmployeService employeService;


	@PostMapping("/Ajout")
	public Employe CreateEmploye(@RequestBody Employe e) {
		return employeRepository.save(e);

	}

	@PutMapping("/Modifier/{id}")
	public ResponseEntity<Employe> modifierEmploye(@PathVariable Long id, @RequestBody Employe nouveauEmploye) {
		Employe employe = employeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employe non trouvé avec l'ID : " + id));
		employe.setNom(nouveauEmploye.getNom());
		employe.setPrenom(nouveauEmploye.getPrenom());
		employe.setEmail(nouveauEmploye.getEmail());
		employe.setTele(nouveauEmploye.getTele());
		employe.setAdresse(nouveauEmploye.getAdresse());
		employe.setSalaire(nouveauEmploye.getSalaire());
		employe.setId_Departement(nouveauEmploye.getId_Departement());

		Employe updatedEmploye = employeRepository.save(employe);
		return ResponseEntity.ok(updatedEmploye);
	}
	@DeleteMapping("/{id}")
	public void deleteEmploye(@PathVariable Long id) {
		employeRepository.deleteById(id);
	}

	@GetMapping
	public List<EmployeDetails> GetAllEmployes(){
		return employeService.findAll();
	}

	@GetMapping("/{id}")
	public EmployeDetails getEmployeById(@PathVariable Long id) throws Exception{
		return employeService.findEmployeByID(id);
	}
	@GetMapping("/ParDepartement/{idDepartement}")
	public List<EmployeDetails> getEmployesByDepartement(@PathVariable Long idDepartement) {
		return employeService.findEmployesByDepartement(idDepartement);
	}

	@GetMapping("/ParDepartement/Count/{idDepartement}")
	public ResponseEntity<Long> countEmployesByDepartement(@PathVariable Long idDepartement) {
		long count = employeService.countEmployesByDepartement(idDepartement);
		return ResponseEntity.ok(count);
	}

	@GetMapping("/Count/Total")
	public ResponseEntity<Long> countTotalEmployes() {
		long count = employeRepository.count();
		return ResponseEntity.ok(count);
	}
}
