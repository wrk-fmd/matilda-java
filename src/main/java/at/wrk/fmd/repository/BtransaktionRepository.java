package at.wrk.fmd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import at.wrk.fmd.model.Btransaktion;

public interface BtransaktionRepository extends JpaRepository<Btransaktion, Long> {

}
