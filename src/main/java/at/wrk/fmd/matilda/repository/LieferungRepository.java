package at.wrk.fmd.matilda.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import at.wrk.fmd.matilda.model.Lieferung;

public interface LieferungRepository extends JpaRepository<Lieferung, Long> {
	
	List<Lieferung> findByCreatedAtGreaterThan(Date createdAt);
}
