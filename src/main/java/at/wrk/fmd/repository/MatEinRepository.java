package at.wrk.fmd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import at.wrk.fmd.model.Einheitentyp;
import at.wrk.fmd.model.Materialtyp_Einheitentyp;

public interface MatEinRepository extends JpaRepository<Materialtyp_Einheitentyp, Long> {
    Materialtyp_Einheitentyp findById(long id);

    List<Materialtyp_Einheitentyp> findByEinheitentyp(Einheitentyp einheitentyp);
}
