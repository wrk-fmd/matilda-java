package at.wrk.fmd.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import at.wrk.fmd.model.Lieferung;

public interface LieferungRepository extends JpaRepository<Lieferung, Long> {
	
	List<Lieferung> findByCreatedAtGreaterThan(Date createdAt);
}
