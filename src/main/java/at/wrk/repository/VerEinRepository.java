package at.wrk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import at.wrk.model.Veranstaltung;
import at.wrk.model.Veranstaltung_Einheitentyp;

public interface VerEinRepository extends JpaRepository<Veranstaltung_Einheitentyp, Long>
{
	List<Veranstaltung_Einheitentyp> findByVeranstaltung(Veranstaltung veranstaltung);
}
