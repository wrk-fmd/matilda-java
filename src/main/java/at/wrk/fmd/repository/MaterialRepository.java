package at.wrk.fmd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import at.wrk.fmd.model.Lagerstandort;
import at.wrk.fmd.model.Material;

public interface MaterialRepository extends JpaRepository<Material, Long> {
    Material findById(long id);

    List<Material> findByLagerstandort(Lagerstandort lagerstandort);
}
