package at.wrk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import at.wrk.model.Veranstaltung;

public interface VeranstaltungRepository extends JpaRepository<Veranstaltung, Long> {
	
	Veranstaltung findByName(String name);
	
	Veranstaltung findById(long id);
}
