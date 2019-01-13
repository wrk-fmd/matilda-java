package at.wrk.fmd.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import at.wrk.fmd.model.Benutzer;
import at.wrk.fmd.model.Buchung;
import at.wrk.fmd.model.Lagerstandort;
import at.wrk.fmd.model.Material;
import at.wrk.fmd.model.Veranstaltung;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserManagementRepositoryTest {
    
    @Autowired
    private UserManagementService repo;
    
    @Autowired
    LagerstandortRepository lager;
    
    @Autowired
    BuchungRepository buchungRepo;
    
    Veranstaltung veranstaltung;    
    List<?> allUsers;

    @Before
    public void setup() {
        allUsers = repo.getAllUsers();
        veranstaltung = new Veranstaltung();
        veranstaltung.setName("Wien");
    }
    
    @Test
    public void validateAdminIsPresent() throws Exception {
        Lagerstandort s = lager.findByName("ADMIN");
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
    
    @Test
    public void findByCreatedAtGreaterThan() {
        Date date = new Date(2010, 01, 01);
        List<Buchung> buchung = buchungRepo.findByCreatedAtGreaterThan(date);
        int sizeOfBuchung = buchung.size();
        assertThat(sizeOfBuchung == 0);
    }
}