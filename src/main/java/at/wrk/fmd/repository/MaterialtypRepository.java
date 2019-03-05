package at.wrk.fmd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import at.wrk.fmd.model.Materialtyp;

public interface MaterialtypRepository extends JpaRepository<Materialtyp, Long> {
    Materialtyp findByName(String name);

    List<Materialtyp> findAll();

    Materialtyp findById(long id);

    void deleteById(Long id);
    
    List<Materialtyp> findAllByOrderByIdAsc();
}
