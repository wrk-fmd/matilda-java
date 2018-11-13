package at.wrk.fmd.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("it")
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class UserRegistrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void submitRegistrationAccountExists() throws Exception {
        mockMvc
        .perform(post("/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .param("Benutzername", "ADMIN")
                .param("password", "#WRK#")
                .with(csrf())).andExpect(status().is(302));
    }
    
    @Test
    public void submitRegistrationAccountExistsWithoutCSRF() throws Exception {
        mockMvc.perform(post("/registration"))
        .andExpect(status().is(302));
    }    
}