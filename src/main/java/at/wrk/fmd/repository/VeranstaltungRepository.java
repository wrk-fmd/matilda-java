package at.wrk.fmd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import at.wrk.fmd.model.Veranstaltung;

public interface VeranstaltungRepository extends JpaRepository<Veranstaltung, Long> {

    Veranstaltung findByName(String name);

    Veranstaltung findById(long id);
    
    List<Veranstaltung> findAll();
}
