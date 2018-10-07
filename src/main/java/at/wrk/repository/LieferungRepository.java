package at.wrk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import at.wrk.model.Lieferung;


public interface LieferungRepository extends JpaRepository<Lieferung, Long> {
}
