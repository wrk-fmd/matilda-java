package at.wrk.fmd.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import at.wrk.fmd.model.Benutzer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserManagementRepositoryTest {
    
    @Autowired
    private UserManagementService repo;
    
    List<?> allUsers;

    @Before
    public void setup() {
        allUsers = repo.getAllUsers();
    }
    
    @Test
    public void validateAdminIsPresent() throws Exception {
        Benutzer admin = (Benutzer) allUsers.get(0);
        String benutzername = admin.getBenutzername();
        assertTrue(benutzername.equals("ADMIN"));
    }

    @Test
    public void validateUserNotAvailable() throws Exception {
        Benutzer someoneElse = (Benutzer) allUsers.get(0);
        String benutzername = someoneElse.getBenutzername();
        assertFalse(benutzername.equals("SomeoneElse"));
    }
    
    @Test
    public void validateUserSizeEqualsOne() throws Exception {
        int size = allUsers.size();
        assertTrue(size == 1);
    }
}