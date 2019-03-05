package at.wrk.fmd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import at.wrk.fmd.model.Lagerstandort;

public interface LagerstandortRepository extends JpaRepository<Lagerstandort, Long> {
    Lagerstandort findByName(String name);

    List<Lagerstandort> findAllByOrderByIdAsc();
     
    Lagerstandort findById(long id);
}
