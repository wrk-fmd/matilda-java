package at.wrk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import at.wrk.model.Ntransaktion;

public interface NtransaktionRepository extends JpaRepository<Ntransaktion, Long>
{

}
