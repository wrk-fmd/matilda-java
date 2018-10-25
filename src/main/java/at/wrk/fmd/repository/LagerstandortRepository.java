package at.wrk.fmd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import at.wrk.fmd.model.Lagerstandort;

public interface LagerstandortRepository extends JpaRepository<Lagerstandort, Long> {
    Lagerstandort findByName(String name);

    Lagerstandort findById(long id);
}
