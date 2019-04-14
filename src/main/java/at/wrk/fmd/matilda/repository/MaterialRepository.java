package at.wrk.fmd.matilda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import at.wrk.fmd.matilda.model.Lagerstandort;
import at.wrk.fmd.matilda.model.Material;
import at.wrk.fmd.matilda.model.Materialtyp;


public interface MaterialRepository extends JpaRepository<Material, Long>
{
	Material findById(long id);	
	
	List<Material> findByMaterialtyp(Materialtyp materialtyp);
	
	List<Material> findByLagerstandort(Lagerstandort lagerstandort);	
	
	List<Material> findByLagerstandortOrderByMaterialtypAsc(Lagerstandort lagerstandort);
}
