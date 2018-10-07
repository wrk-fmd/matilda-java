package at.wrk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import at.wrk.model.Buchung;

public interface BuchungRepository extends JpaRepository<Buchung, Long>
{

}
