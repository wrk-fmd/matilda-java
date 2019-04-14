package at.wrk.fmd.matilda.config;

import at.wrk.fmd.matilda.repository.UserRepository;
import at.wrk.fmd.matilda.model.Benutzer;
import at.wrk.fmd.matilda.model.Rolle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRolleIntegrationTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void isBenutzerTableNotEmpty() throws Exception {
        List<Benutzer> users = userRepository.findAll();
        assertFalse(users.isEmpty());
    }
    
    @Test
    public void isBenutzerTableSizeThree() throws Exception {
        List<Benutzer> users = userRepository.findAll();
        assertEquals(users.size(), 3);
    }
    
    @Test
    public void isBenutzerAdminCreated() throws Exception {
        List<Benutzer> users = userRepository.findAll();
        assertTrue(users.get(0).getBenutzername().equals("ADMIN"));
    }
    
    @Test
    public void isRolleADMINCreated() throws Exception {
        boolean isFound = false;
        List<Benutzer> users = userRepository.findAll();
        for(Benutzer b : users) {
            for(Rolle rolle : b.getRollen()) {
                String bezeichnung = rolle.getBezeichnung();
                if(bezeichnung.equals("ADMIN")) {
                    isFound = true;
                }
            }
        }
        assertTrue(isFound);
    }
}