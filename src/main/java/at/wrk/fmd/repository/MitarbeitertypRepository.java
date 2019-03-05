package at.wrk.fmd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import at.wrk.fmd.model.Mitarbeitertyp;

public interface MitarbeitertypRepository extends JpaRepository<Mitarbeitertyp, Long> {
    Mitarbeitertyp findByKuerzel(String kuerzel);

    List<Mitarbeitertyp> findAll();

    Mitarbeitertyp findById(long id);

    void deleteById(Long id);
    
    List<Mitarbeitertyp> findAllByOrderByIdAsc();
}
