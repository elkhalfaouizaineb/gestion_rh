package ma.emsi.StagiaireService.repository;

import ma.emsi.StagiaireService.model.Stagiaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StagiaireRepository extends JpaRepository<Stagiaire, Long > {
    @Query("SELECT e FROM Stagiaire e WHERE e.id_Departement = :departementId")
    java.util.List<Stagiaire> findById_Departement(@Param("departementId") Long departementId);
}
