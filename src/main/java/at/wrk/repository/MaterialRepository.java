package at.wrk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import at.wrk.model.Lagerstandort;
import at.wrk.model.Material;

public interface MaterialRepository extends JpaRepository<Material, Long>
{
	Material findById(long id);
	
	List<Material> findByLagerstandort(Lagerstandort lagerstandort);
}
