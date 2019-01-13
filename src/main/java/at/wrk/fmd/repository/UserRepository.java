package at.wrk.fmd.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.wrk.fmd.model.Benutzer;

@Repository
public interface UserRepository extends JpaRepository<Benutzer, Long> {
    Benutzer findByBenutzername(String benutzername);

    List<Benutzer> findAll();
    
    List<Benutzer> findAllByOrderByIdAsc();

    void deleteById(Long id);
    
    @Modifying
    @Query(value = "UPDATE benutzer SET active = :active WHERE id = :id", nativeQuery = true)
    @Transactional
    void setUserActive(@Param("active") Boolean active, @Param("id") long id);

    Benutzer findByBenutzername(String username, Sort sortByIdAsc);
}