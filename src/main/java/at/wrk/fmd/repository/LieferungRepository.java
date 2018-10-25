package at.wrk.fmd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import at.wrk.fmd.model.Lieferung;

public interface LieferungRepository extends JpaRepository<Lieferung, Long> {
}
