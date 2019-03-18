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

    @Override
	List<Benutzer> findAll();
    
    List<Benutzer> findAllByOrderByIdAsc();

    @Override
	void deleteById(Long id);
    
    @Modifying
    @Query(value = "UPDATE benutzer SET active = :active,"
            + "benutzername = :benutzername,"
            + "anzeigename = :anzeigename,"
            + "dienstnummer = :dienstnummer"
            + " WHERE id = :id", nativeQuery = true)
    @Transactional
    void setUserActive(@Param("active") Boolean active, @Param("benutzername") String benutzername, @Param("anzeigename") String anzeigename, @Param("dienstnummer") String dienstnummer, @Param("id") long id);

    Benutzer findByBenutzername(String username, Sort sortByIdAsc);
}