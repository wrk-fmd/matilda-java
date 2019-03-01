package at.wrk.fmd.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Ordering;

import at.wrk.fmd.environment.AbstractJunitMatildaTest;
import at.wrk.fmd.model.Benutzer;
import at.wrk.fmd.repository.UserRepository;

public class MitarbeiterverwaltungControllerTest extends AbstractJunitMatildaTest {
    
    @Autowired
    UserRepository userRepo;

    @Test
    public void contexLoads() throws Exception
    {
        assertThat(userRepo).isNotNull();
    }
    
    @Test
    public void shouldReturnMitarbeiterCount() {
        List<Benutzer> mitarbeiterVerwaltung = userRepo.findAll();
        assertThat(mitarbeiterVerwaltung.size()>0);
    }
    
    @Test
    public void shouldReturnMitarbeiterAnzeigename() {
        List<Benutzer> mitarbeiterVerwaltung = userRepo.findAll();
        assertThat(mitarbeiterVerwaltung.get(0).getAnzeigename().equals("ADMIN"));
    }
    
    @Test
    public void shouldReturnMitarbeiterDienstnummer() {
        List<Benutzer> mitarbeiterVerwaltung = userRepo.findAll();
        assertThat(mitarbeiterVerwaltung.get(0).getDienstnummer().equals("1"));
    }
    
    @Test
    public void shouldReturnMitarbeiterPassword() {
        List<Benutzer> mitarbeiterVerwaltung = userRepo.findAll();
        assertThat(mitarbeiterVerwaltung.get(0).getPasswort().contains("$2a$1"));
    }
    
    @Test
    public void shouldReturnMitarbeiterBenutzername() {
        List<Benutzer> mitarbeiterVerwaltung = userRepo.findAll();
        assertThat(mitarbeiterVerwaltung.get(0).getBenutzername().equalsIgnoreCase("admin"));
    }
    
    @Test
    public void shouldReturnMitarbeiterListeOrderByAsc() {
        List<Benutzer> mitarbeiterVerwaltung = userRepo.findAll();
        List<String> newListOfBenutzername = new ArrayList<String>();
        
        for(int i = 0; i<mitarbeiterVerwaltung.size(); i++) {
            newListOfBenutzername.add(mitarbeiterVerwaltung.get(i).getBenutzername());
        }
        boolean sorted = Ordering.natural().isOrdered(newListOfBenutzername);
        assertTrue(sorted);
    }
    
    @Test
    public void shouldReturnMitarbeiterIsActive() {
        Benutzer mitarbeiterVerwaltung = userRepo.findByBenutzername("ADMIN");
        assertTrue(mitarbeiterVerwaltung.isActive());
    }

    @Test
    public void shouldReturnMitarbeiterIsNotActive() {
        Benutzer mitarbeiterVerwaltung = userRepo.findByBenutzername("Bernd");
        assertFalse(mitarbeiterVerwaltung.isActive());
    }
    
    @Test
    public void shouldReturnMitarbeiterIsDeleted() {
        Benutzer benutzer = userRepo.findByBenutzername("Bernd");
        userRepo.delete(benutzer);
        assertTrue(true);
    }
}