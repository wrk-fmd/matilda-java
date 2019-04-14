package at.wrk.fmd.matilda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import at.wrk.fmd.matilda.model.Mitarbeitertyp_Einheitentyp;
import at.wrk.fmd.matilda.model.Veranstaltung;
import at.wrk.fmd.matilda.model.Veranstaltung_Einheitentyp;

public interface VerEinRepository extends JpaRepository<Veranstaltung_Einheitentyp, Long> {
    List<Veranstaltung_Einheitentyp> findByVeranstaltung(Veranstaltung veranstaltung);
    
    Veranstaltung_Einheitentyp findById(long id);
    
    List<Mitarbeitertyp_Einheitentyp> findAllByOrderByIdAsc();
}
