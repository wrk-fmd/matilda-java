package at.wrk.fmd.matilda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import at.wrk.fmd.matilda.model.Benutzer;

// TODO SMELL: Please never-ever use lowercase names for classes! Btw, its quite tricky to rename it now in Windows :)
public interface updateOldPasswordRepository extends JpaRepository<Benutzer, Long> {
    @Modifying
    // TODO SMELL: Is it necessary to use a native query here? Imo it is more error-prone.
    @Query(value = "UPDATE benutzer SET passwort = :passwort WHERE benutzername = :benutzername", nativeQuery = true)
    @Transactional
    void updatePassword(@Param("benutzername") String benutzername, @Param("passwort") String passwort);
}