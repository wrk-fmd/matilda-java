package at.wrk.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserRegistrationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void submitLoginThrowsError() throws Exception {
		this.mockMvc
			.perform(
						post("/login")
						.with(csrf())
						.contentType(MediaType.APPLICATION_FORM_URLENCODED)
						.param("benutzername", "ADMIN")
						.param("passwort", "test")
					)
			.andExpect(status().is3xxRedirection());
	}

    @Test
    public void submitRegistrationSuccess() throws Exception {
        this.mockMvc
                .perform(
						post("/registration")
						.with(csrf())
						.contentType(MediaType.APPLICATION_FORM_URLENCODED)
						.param("anzeigename", "ADMIN")
						.param("dienstnummer", "123")
						.param("benutzername", "ADMIN")
						.param("passwort", "#WRK#")
						.param("konfPasswort", "#WRK#")
						.param("konfBenutzername", "ADMIN")
						.param("rollen", "ADMIN")
                )
                .andExpect(redirectedUrl("/registration?success"))
                .andExpect(status().is3xxRedirection());
    }
}