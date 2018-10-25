package at.wrk.fmd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import at.wrk.fmd.model.Einheitentyp;

public interface EinheitentypRepository extends JpaRepository<Einheitentyp, Long> {
    Einheitentyp findByName(String name);

    List<Einheitentyp> findAll();

    Einheitentyp findById(long id);

    void deleteById(Long id);
}
