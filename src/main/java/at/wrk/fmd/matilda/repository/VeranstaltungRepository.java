package at.wrk.fmd.matilda.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import at.wrk.fmd.matilda.model.Veranstaltung;

public interface VeranstaltungRepository extends JpaRepository<Veranstaltung, Long> {

    Veranstaltung findByName(String name);

    Veranstaltung findById(long id);
    
    List<Veranstaltung> findAll();
    
    List<Veranstaltung> findByEndeGreaterThan(LocalDateTime date);
    
    List<Veranstaltung> findAllByOrderByIdDesc();
}
