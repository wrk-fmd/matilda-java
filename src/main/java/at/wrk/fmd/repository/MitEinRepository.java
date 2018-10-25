package at.wrk.fmd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import at.wrk.fmd.model.Einheitentyp;
import at.wrk.fmd.model.Mitarbeitertyp_Einheitentyp;

public interface MitEinRepository extends JpaRepository<Mitarbeitertyp_Einheitentyp, Long> {
    Mitarbeitertyp_Einheitentyp findById(long id);

    List<Mitarbeitertyp_Einheitentyp> findByEinheitentyp(Einheitentyp einheitentyp);
}
