package at.wrk.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import at.wrk.model.Benutzer;
import at.wrk.pojo.User;

@Repository
public interface UserRepository extends JpaRepository<Benutzer, Long>
{
	Benutzer findByBenutzername(String benutzername);
	
	List<Benutzer> findAll();
	
	void deleteById(Long id);
}