package ma.emsi.DepartementService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.emsi.DepartementService.model.Departement;

@Repository
public interface DepartementRepository extends JpaRepository<Departement, Long> {

	
}
