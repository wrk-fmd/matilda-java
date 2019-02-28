package at.wrk.fmd.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import at.wrk.fmd.model.Benutzer;
import at.wrk.fmd.repository.UserRepository;

// Adding src/main/resources/application-test.properties.
// Annotating test class with @ActiveProfiles("test").
// This loads application.properties and then application-test.properties properties into application context.
// For further clarification, look here: https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class MainControllerTest {
    @Autowired
    private MainController mainController;
    
    @Autowired
    private UserRepository uRepo;

//    @Autowired
//    private RoleRepository roleRepo;
    
    @Autowired
    private MockMvc mockMvc;

    //Simple test whether MainController isNotNull
    
    @Test
    public void contexLoads() throws Exception
    {
        assertThat(mainController).isNotNull();
    }
    
    @Test
    public void shouldReturnUsername() throws Exception {
        this.mockMvc.perform(get("/login")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("username")));
    }
    
    @Test
    public void shouldReturnPassword() throws Exception {
        this.mockMvc.perform(get("/login")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Password")));
    }
    
    @Test
    public void shouldReturnLogin() throws Exception {
        this.mockMvc.perform(get("/login")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Log In")));
    }
    
    @Test
    public void shouldReturnAdminIsCreated() {
        Benutzer benutzer = uRepo.findByBenutzername("ADMIN");
        assertThat("ADMIN is created in database!", benutzer.getBenutzername(), is("ADMIN"));
    }
    
//    @Test
//    public void shouldReturnSupervisorIsCreated() {
//        Benutzer benutzer = uRepo.findByBenutzername("SUPERVISOR");
//        assertThat("SUPERVISOR", benutzer.getBenutzername(), is("SUPERVISOR"));
//    }
//    
//    @Test
//    public void shouldReturnBenutzerIsCreated() {
//        Benutzer benutzer = uRepo.findByBenutzername("BENUTZER");
//        assertThat("BENUTZER is created in database!", benutzer.getBenutzername(), is("BENUTZER"));
//    }
//    
//    @Test
//    public void shouldReturnRolleAdminIsCreated() {
//        List<Rolle> role = roleRepo.findAll();
//        assertThat("Role Admin is created in database!", role.get(0).getBezeichnung(), is("ADMIN"));
//    }
//    
//    @Test
//    public void shouldReturnRolleSupervisorIsCreated() {
//        List<Rolle> role = roleRepo.findAll();
//        assertThat("Role SUPERVISOR is created in database!", role.get(1).getBezeichnung(), is("SUPERVISOR"));
//    }
//    
//    @Test
//    public void shouldReturnRolleBenutzerIsCreated() {
//        List<Rolle> role = roleRepo.findAll();
//        assertThat("Role BENUTZER is created in database!", role.get(2).getBezeichnung(), is("BENUTZER"));
//    }
}