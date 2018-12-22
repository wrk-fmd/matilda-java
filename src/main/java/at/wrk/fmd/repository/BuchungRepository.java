package at.wrk.fmd.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import at.wrk.fmd.model.Buchung;
import at.wrk.fmd.model.Veranstaltung;


public interface BuchungRepository extends JpaRepository<Buchung, Long>{
	
	List<Buchung> findByCreatedAtGreaterThan(Date createdAt);
	
	List<Buchung> findByVeranstaltung(Veranstaltung veranstaltung);
}

