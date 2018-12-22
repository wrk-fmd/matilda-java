package at.wrk.fmd.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import at.wrk.fmd.model.Benutzer;

@Repository
public interface UserRepository extends JpaRepository<Benutzer, Long> {
    Benutzer findByBenutzername(String benutzername);

    List<Benutzer> findAll();

    void deleteById(Long id);
}