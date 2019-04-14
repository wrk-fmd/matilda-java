package at.wrk.fmd.matilda.repository;

import java.util.List;

import at.wrk.fmd.matilda.model.Veranstaltung;
import at.wrk.fmd.matilda.pojo.VeranstaltungBuchung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeranstaltungBuchungRepository extends JpaRepository<VeranstaltungBuchung, Integer>
{
	VeranstaltungBuchung findById(int id);
	
	List<VeranstaltungBuchung> findByVeranstaltung(Veranstaltung veranstaltung);
}
