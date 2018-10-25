package at.wrk.fmd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import at.wrk.fmd.model.Ntransaktion;

public interface NtransaktionRepository extends JpaRepository<Ntransaktion, Long> {

}
