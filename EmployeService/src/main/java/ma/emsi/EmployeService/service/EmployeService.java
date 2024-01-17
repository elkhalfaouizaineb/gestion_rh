package ma.emsi.EmployeService.service;

import ma.emsi.EmployeService.model.Departement;
import ma.emsi.EmployeService.model.Employe;
import ma.emsi.EmployeService.model.EmployeDetails;
import ma.emsi.EmployeService.repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;
import java.util.List;

@Service
public class EmployeService {

    @Autowired
    EmployeRepository employeRepository;
    @Autowired
    private RestTemplate restTemplate;

    private final String URLDepartements="http://localhost:8888/SERVICE-DEPARTEMENT";

    public List<EmployeDetails> findAll(){

        List<Employe> employes=employeRepository.findAll();
        ResponseEntity<Departement[]> response=restTemplate.getForEntity(this.URLDepartements+"/api/Departement/",Departement[].class);
        Departement[] departement=response.getBody();

        return employes.stream().map((Employe e)->
                        mapToEmployeResponse(e,departement))
                .toList();


    }
    public EmployeDetails findEmployeByID(Long id) throws Exception{

        Employe employe= employeRepository.findById(id).orElseThrow(()->new Exception("Employe Invalid"));
        Departement departement =restTemplate.getForObject(this.URLDepartements+"/api/Departement/"+employe.getId_Departement(),Departement.class);


        return EmployeDetails.builder()
                .id(employe.getId())
                .nom(employe.getNom())
                .prenom(employe.getPrenom())
                .email(employe.getEmail())
                .tele(employe.getTele())
                .adresse(employe.getAdresse())
                .salaire(employe.getSalaire())
                .departement(departement)
                .build();

    }
    private EmployeDetails mapToEmployeResponse(Employe employe,Departement[] departements){


        Departement foundDepartement= Arrays.stream(departements)
                .filter(department->department.getId().equals(employe.getId_Departement())).findFirst()
                .orElse(null);

        return EmployeDetails.builder()
                .id(employe.getId())
                .nom(employe.getNom())
                .prenom(employe.getPrenom())
                .email(employe.getEmail())
                .tele(employe.getTele())
                .adresse(employe.getAdresse())
                .salaire(employe.getSalaire())
                .departement(foundDepartement)
                .build();

    }



    public List<EmployeDetails> findEmployesByDepartement(Long idDepartement) {
        List<Employe> employes = employeRepository.findById_Departement(idDepartement);
        return employes.stream().map((Employe e) -> mapToEmployeResponse(e, getDepartements()))
                .toList();
    }
    public long countEmployesByDepartement(Long idDepartement) {
        List<EmployeDetails> employes = findEmployesByDepartement(idDepartement);
        return employes.size();
        // Ou utilisez employes.stream().count() pour Java 8 et versions ultérieures
    }

    // Méthode pour récupérer tous les départements
    private Departement[] getDepartements() {
        ResponseEntity<Departement[]> response = restTemplate.getForEntity(this.URLDepartements + "/api/Departement/",
                Departement[].class);
        return response.getBody();
    }
}
