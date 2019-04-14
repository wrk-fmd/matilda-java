package at.wrk.fmd.matilda.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PDFService {
    @Query(value = "SELECT v.name AS Veranstaltungsname, b.anzeigename, l.name AS Lagerstandortname FROM Benutzer b "
            + "INNER JOIN Veranstaltung v ON b.id = v.id " + "INNER JOIN Lagerstandort l ON l.benutzer = b.id "
            + "INNER JOIN Material m ON m.lagerstandort = l.id "
            + "INNER JOIN Materialtyp mtyp ON mtyp.id = m.materialtyp " + "WHERE m.einkaufsdatum BETWEEN ?1 AND ?2 "
            + "GROUP BY v.name, b.anzeigename, l.name", nativeQuery = true)
    List<?> findByDate(@Param("findAllByDate") Date dateBeginn, Date dateEnde);
}