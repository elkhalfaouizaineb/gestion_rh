package ma.emsi.EmployeService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.emsi.EmployeService.model.Employe;

import java.util.List;

@Repository
public interface EmployeRepository extends JpaRepository<Employe, Long >{
    @Query("SELECT e FROM Employe e WHERE e.id_Departement = :departementId")
    java.util.List<Employe> findById_Departement(@Param("departementId") Long departementId);
}
