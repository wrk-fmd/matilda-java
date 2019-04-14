package at.wrk.fmd.matilda.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import at.wrk.fmd.matilda.model.Buchung;
import at.wrk.fmd.matilda.model.Material;
import at.wrk.fmd.matilda.model.Veranstaltung;


public interface BuchungRepository extends JpaRepository<Buchung, Long>{
	
	List<Buchung> findByCreatedAtGreaterThan(Date createdAt);
	
	List<Buchung> findByVeranstaltung(Veranstaltung veranstaltung);
	
	List<Buchung> findByMaterial(Material material);
}

