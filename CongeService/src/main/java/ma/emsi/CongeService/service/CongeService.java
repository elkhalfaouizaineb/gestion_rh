package ma.emsi.CongeService.service;

import ma.emsi.CongeService.model.Conge;
import ma.emsi.CongeService.model.CongeDetails;
import ma.emsi.CongeService.model.Employe;
import ma.emsi.CongeService.repository.CongeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CongeService {
    @Autowired
    CongeRepository congeRepository;
    @Autowired
    private RestTemplate restTemplate;

    private final String URLEmployes="http://localhost:8888/SERVICE-EMPLOYE";

    public List<CongeDetails> findAll(){

        List<Conge> conges=congeRepository.findAll();
        ResponseEntity<Employe[]> response=restTemplate.getForEntity(this.URLEmployes+"/api/Employes/",Employe[].class);
        Employe[] employe=response.getBody();

        return conges.stream().map((Conge e)->
                        mapToCongeResponse(e,employe))
                .toList();


    }
    public CongeDetails findCongeByID(Long id) throws Exception{

        Conge conge= congeRepository.findById(id).orElseThrow(()->new Exception("Conge Invalid"));
        Employe employe =restTemplate.getForObject(this.URLEmployes+"/api/Employes/"+conge.getId_Employe(),Employe.class);


        return CongeDetails.builder()
                .id(conge.getId())
                .dateDebut(conge.getDateDebut())
                .dateFin(conge.getDateFin())
                .typeConge(conge.getTypeConge())
                .employe(employe)
                .build();

    }
    private CongeDetails mapToCongeResponse(Conge conge,Employe[] employes){

        Employe foundEmploye= Arrays.stream(employes)
                .filter(employe->employe.getId().equals(conge.getId_Employe())).findFirst()
                .orElse(null);

        return CongeDetails.builder()
                .id(conge.getId())
                .dateDebut(conge.getDateDebut())
                .dateFin(conge.getDateFin())
                .typeConge(conge.getTypeConge())
                .employe(foundEmploye)
                .build();

    }
    public List<CongeDetails> findCongesByEmployeId(Long employeId) {
        List<Conge> conges = congeRepository.findByEmployeId(employeId); // Ajouter cette méthode dans votre interface CongeRepository
        Employe employe = restTemplate.getForObject(URLEmployes + "/api/Employes/" + employeId, Employe.class);

        return conges.stream()
                .map(conge -> CongeDetails.builder()
                        .id(conge.getId())
                        .dateDebut(conge.getDateDebut())
                        .dateFin(conge.getDateFin())
                        .typeConge(conge.getTypeConge())
                        .employe(employe)
                        .build())
                .toList();
    }
}
