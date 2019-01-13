package at.wrk.fmd.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import at.wrk.fmd.model.Benutzer;
import at.wrk.fmd.model.Lagerstandort;
import at.wrk.fmd.repository.LagerstandortRepository;
import at.wrk.fmd.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LagerstandortControllerTest {
    @Autowired
    private LagerstandortController lagerstandortController;
    
    @Autowired
    private LagerstandortRepository lagerstandortRepository;
    
    @Autowired
    private UserRepository userRepository;
 
    private Benutzer benutzer;
    private LagerstandortController dummy;
    private BindingResult bindingResult;
    private Lagerstandort nameOfLager;
    private Model model;

    @Before
    public void setup() {
        benutzer = new Benutzer("ADMIN", "ADMIN");
        dummy = mock(LagerstandortController.class);
        bindingResult = mock(BindingResult.class);
        model = mock(Model.class);
    }
    
    //Simple test whether LagerstandortController, LagerstandortRepository,User Repository and isNotNull
    
    @Test
    public void contexLoads() throws Exception
    {
        assertThat(lagerstandortController).isNotNull();
        assertThat(lagerstandortRepository).isNotNull();
        assertThat(userRepository).isNotNull();
    }
 
    @Test
    public void validateAllUsers() throws Exception {
        List<Benutzer> userList = new ArrayList<>();
        userList.add(0, benutzer);

        when(dummy.alleBenutzer()).thenReturn(userList);
    }
    
    @Test
    public void validateAendernSpeichern() throws Exception {
        dummy.aendernSpeichern(1L, nameOfLager, bindingResult);
    }
}