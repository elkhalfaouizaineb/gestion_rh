package ma.emsi.CongeService.repository;

import ma.emsi.CongeService.model.Conge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CongeRepository  extends JpaRepository<Conge, Long > {
    @Query("SELECT c FROM Conge c WHERE c.id_Employe = :employeId")
    List<Conge> findByEmployeId(@Param("employeId") Long employeId);
}
