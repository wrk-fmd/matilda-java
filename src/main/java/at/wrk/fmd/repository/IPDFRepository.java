package at.wrk.fmd.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import at.wrk.fmd.model.Benutzer;

@Repository
public interface IPDFRepository extends JpaRepository<Benutzer, Long> {
    @Query(value = "SELECT v.name as veranstaltung, l.name as lagerstandort, m.bezeichnung " 
            + "FROM veranstaltung v " 
            + "INNER JOIN lagerstandort l ON v.lagerstandort = l.id "
            + "INNER JOIN material m ON m.id = l.id "
            + "INNER JOIN buchung b ON b.veranstaltung = v.id "
            + "WHERE m.einkaufsdatum BETWEEN ?1 AND ?2 "
            + "GROUP BY v.name, l.name, m.bezeichnung", nativeQuery = true)
    List<?> findByDate(@Param("findAllByDate") Date dateBeginn, Date dateEnde);
    
    @Query(value = "SELECT v.name AS Veranstaltungsname, b.anzeigename, l.name AS Lagerstandortname FROM Benutzer b "
            + "INNER JOIN Veranstaltung v ON b.id = v.id "
            + "INNER JOIN Lagerstandort l ON l.benutzer = b.id "
            + "INNER JOIN Material m ON m.lagerstandort = l.id "
            + "INNER JOIN Materialtyp mtyp ON mtyp.id = m.materialtyp "
            + "WHERE m.einkaufsdatum BETWEEN ?1 AND ?2 "
            + "GROUP BY v.name, b.anzeigename, l.name", nativeQuery = true)
    List<?> findByAusgabeschein(@Param("findAllByDate") Date dateBeginn, Date dateEnde);

    @Query(value = "SELECT v.name as veranstaltung, l.name as lagerstandort, m.bezeichnung FROM lagerstandort l "
            + "INNER JOIN material m ON l.id = m.lagerstandort " 
            + "INNER JOIN veranstaltung v ON v.lagerstandort = l.id "
            + "WHERE v.beginn BETWEEN ?1 AND ?2 "
            + "GROUP BY v.name, l.name, m.bezeichnung "
            + "ORDER BY v.name", nativeQuery = true)
    List<?> findByPackliste(@Param("findAllByDate") Date dateBeginn, Date dateEnde);    
}