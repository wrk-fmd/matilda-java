package at.wrk.controller;

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
        this.mockMvc
        	.perform(
        			post("/registration")
                    	.with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                         .param("Benutzername", "ADMIN")
                         .param("password", "#WRK#")
        			)
        	.andExpect(model().hasErrors())
            .andExpect(model().attributeHasFieldErrors("benutzer"))
            .andExpect(status().isOk());
    }
}