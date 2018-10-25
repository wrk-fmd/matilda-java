package at.wrk.fmd.website;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RegistrationWebsiteTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnIsOk() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isFound());
    }

    @Test
    public void shouldReturnStatusOkIfStringContainsBenutzername() throws Exception {
        this.mockMvc.perform(get("/registration")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Benutzername")));
    }

    @Test
    public void shouldReturnStatusOkIfStringContainsPassword() throws Exception {
        this.mockMvc.perform(get("/registration")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Passwort")));
    }

    @Test
    public void shouldReturnStatusOkIfStringContainsDienstnummer() throws Exception {
        this.mockMvc.perform(get("/registration")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Dienstnummer")));
    }

    @Test
    public void shouldReturnStatusOkIfStringContainsAnzeigename() throws Exception {
        this.mockMvc.perform(get("/registration")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Anzeigename")));
    }

    @Test
    public void shouldReturnStatusOkIfStringContainsBestBenutzername() throws Exception {
        this.mockMvc.perform(get("/registration")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Bestätigungs-Passwort")));
    }

    @Test
    public void shouldReturnStatusOkIfStringContainsRollen() throws Exception {
        this.mockMvc.perform(get("/registration")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Rollen")));
    }
}