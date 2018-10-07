package at.wrk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import at.wrk.model.Einheitentyp;
import at.wrk.model.Mitarbeitertyp_Einheitentyp;

public interface MitEinRepository extends JpaRepository<Mitarbeitertyp_Einheitentyp, Long>
{
	Mitarbeitertyp_Einheitentyp findById(long id);
	
	List<Mitarbeitertyp_Einheitentyp> findByEinheitentyp(Einheitentyp einheitentyp);
}
