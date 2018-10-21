package at.wrk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import at.wrk.model.Benutzer;

public interface updateOldPasswordRepository extends JpaRepository<Benutzer, Long>{
	@Modifying
    @Query(value = "UPDATE benutzer SET passwort = :passwort WHERE benutzername = :benutzername", nativeQuery = true)
	@Transactional
    void updatePassword(@Param("benutzername") String benutzername, @Param("passwort") String passwort);
}