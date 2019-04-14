package at.wrk.fmd.matilda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import at.wrk.fmd.matilda.model.Einheitentyp;

public interface EinheitentypRepository extends JpaRepository<Einheitentyp, Long> {
    Einheitentyp findByName(String name);

    List<Einheitentyp> findAll();

    List<Einheitentyp> findAllByOrderByIdAsc();
    
    Einheitentyp findById(long id);

    void deleteById(Long id);
}
