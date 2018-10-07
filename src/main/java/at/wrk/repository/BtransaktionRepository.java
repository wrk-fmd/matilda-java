package at.wrk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import at.wrk.model.Btransaktion;

public interface BtransaktionRepository extends JpaRepository<Btransaktion, Long>
{

}
