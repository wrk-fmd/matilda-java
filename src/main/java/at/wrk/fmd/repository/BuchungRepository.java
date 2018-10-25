package at.wrk.fmd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import at.wrk.fmd.model.Buchung;

public interface BuchungRepository extends JpaRepository<Buchung, Long> {

}
