package at.wrk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import at.wrk.model.Einheitentyp;

public interface EinheitentypRepository extends JpaRepository<Einheitentyp, Long>
{
	Einheitentyp findByName(String name);
	
	List<Einheitentyp> findAll();
	
	Einheitentyp findById(long id);
	
	void deleteById(Long id);
}
