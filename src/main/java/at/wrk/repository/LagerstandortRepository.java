package at.wrk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import at.wrk.model.Lagerstandort;

public interface LagerstandortRepository extends JpaRepository<Lagerstandort, Long>
{
	Lagerstandort findByName(String name);
	
	Lagerstandort findById(long id);
}
