package at.wrk.fmd.matilda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import at.wrk.fmd.matilda.model.Benutzer;

public interface IUpdateOldPasswordRepository extends JpaRepository<Benutzer, Long> {
    @Modifying
    @Query(value = "UPDATE benutzer SET passwort = :passwort WHERE benutzername = :benutzername", nativeQuery = true)
    @Transactional
    void updatePassword(@Param("benutzername") String benutzername, @Param("passwort") String passwort);
}