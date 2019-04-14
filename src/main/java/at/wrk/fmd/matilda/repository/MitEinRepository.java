package at.wrk.fmd.matilda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import at.wrk.fmd.matilda.model.Einheitentyp;
import at.wrk.fmd.matilda.model.Mitarbeitertyp_Einheitentyp;

// TODO SMELL: This shortened name of the repository makes no sense at all, neither in English nor in German.
public interface MitEinRepository extends JpaRepository<Mitarbeitertyp_Einheitentyp, Long> {
    Mitarbeitertyp_Einheitentyp findById(long id);

    List<Mitarbeitertyp_Einheitentyp> findByEinheitentyp(Einheitentyp einheitentyp);
    
    List<Mitarbeitertyp_Einheitentyp> findAllByOrderByIdAsc();
}
