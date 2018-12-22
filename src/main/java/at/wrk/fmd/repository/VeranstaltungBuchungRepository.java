package at.wrk.fmd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import at.wrk.fmd.model.Veranstaltung;
import at.wrk.fmd.pojo.VeranstaltungBuchung;

@Repository
public interface VeranstaltungBuchungRepository extends JpaRepository<VeranstaltungBuchung, Integer>
{
	VeranstaltungBuchung findById(int id);
	
	List<VeranstaltungBuchung> findByVeranstaltung(Veranstaltung veranstaltung);
}
