package at.wrk.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import at.wrk.model.Benutzer;
import at.wrk.model.Rolle;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
	
    @Autowired
    private UserRepository userRepository;
    
    @Test
    public void testIfAdminIsCreated()
    {
    	Benutzer b = new Benutzer("ADMIN", "ADMIN");
    	assertEquals(b.getBenutzername(), "ADMIN");
    	
    	String dienstnummer = b.getDienstnummer();
    	assertTrue("Dienstnummer is nicht 1", dienstnummer.equals("ADMIN"));
    }
    
    @Test
    public void testIfRolesAreCreated()
    {
    	Rolle r = new Rolle("ADMIN");
    	assertEquals(r.getBezeichnung(), "ADMIN");
    	
    	r = new Rolle("SUPERVISOR");
    	assertEquals(r.getBezeichnung(), "SUPERVISOR");
    	
    	r = new Rolle("BENUTZER");
    	assertEquals(r.getBezeichnung(), "BENUTZER");
    }
}
