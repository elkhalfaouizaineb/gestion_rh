package ma.emsi.DepartementService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.emsi.DepartementService.model.Departement;
import ma.emsi.DepartementService.repository.DepartementRepository;

@RestController
@RequestMapping("/api/Departement/")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8888"}, allowedHeaders = "*")
public class DepartementController {

	@Autowired
	private DepartementRepository departementRepository;


	@PostMapping("/Ajout")
	public Departement CreateDepartement(@RequestBody Departement d) {
		return departementRepository.save(d);

	}
	@GetMapping
	public List<Departement> GetAllDepartements(){
		return departementRepository.findAll();
	}
	
    @DeleteMapping("/{id}")
    public Departement deleteDepartement(@PathVariable Long id) {
        // Supprime le département par son ID
        Departement departementToDelete = departementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Departement non trouvé avec l'ID : " + id));

        departementRepository.delete(departementToDelete);

        // Retourne le département supprimé si nécessaire
        return departementToDelete;
    }

	@GetMapping("/{id}")
	public Departement getDepartementById(@PathVariable Long id) throws Exception{

		return departementRepository.findById(id).orElseThrow(() -> new Exception("Departement Invalid"));
	}

	@PutMapping("/Modifier/{id}")
	public Departement UpdateDepartement( @PathVariable Long id , @RequestBody Departement departement) throws Exception {
		Departement findDepartement=departementRepository.findById(id).orElseThrow(() -> new Exception("Departement Invalid"));
		if (findDepartement != null){
			findDepartement.setNom(departement.getNom());
			findDepartement.setDescription(departement.getDescription());
		}
		departementRepository.save(findDepartement);
		return  findDepartement;
	}
	@GetMapping("/Nom/{id}")
	public String getNomDepartementById(@PathVariable Long id) throws Exception {
		Departement departement = departementRepository.findById(id)
				.orElseThrow(() -> new Exception("Departement Invalid"));

		return departement.getNom();
	}
}
