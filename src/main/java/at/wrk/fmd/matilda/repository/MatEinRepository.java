package at.wrk.fmd.matilda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import at.wrk.fmd.matilda.model.Einheitentyp;
import at.wrk.fmd.matilda.model.Materialtyp_Einheitentyp;

public interface MatEinRepository extends JpaRepository<Materialtyp_Einheitentyp, Long> {
    Materialtyp_Einheitentyp findById(long id);

    List<Materialtyp_Einheitentyp> findByEinheitentyp(Einheitentyp einheitentyp);
    
    List<Materialtyp_Einheitentyp> findAllByOrderByIdAsc();
}
