package at.wrk.fmd.matilda.website;

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
public class MaterialVerwaltungWebsiteTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnIsOk() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isFound());
    }

    @Test
    public void shouldReturnStatusOkIfStringContainsLieferung() throws Exception {
        this.mockMvc.perform(get("/materialverwaltung")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Lieferung(Eingabe/Ausgabe)")));
    }

    @Test
    public void shouldReturnStatusOkIfStringContainsBuchen() throws Exception {
        this.mockMvc.perform(get("/materialverwaltung")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Buchen/Abbestellen")));
    }

    @Test
    public void shouldReturnStatusOkIfStringContainsInventur() throws Exception {
        this.mockMvc.perform(get("/materialverwaltung")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Inventur")));
    }

    @Test
    public void shouldReturnStatusOkIfStringContainsSeriennummer() throws Exception {
        this.mockMvc.perform(get("/materialverwaltung")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Seriennummer")));
    }

    @Test
    public void shouldReturnStatusOkIfStringContainsBestand() throws Exception {
        this.mockMvc.perform(get("/materialverwaltung")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Bestand")));
    }

    @Test
    public void shouldReturnStatusOkIfStringContainsEinkaufsdatum() throws Exception {
        this.mockMvc.perform(get("/materialverwaltung")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Einkaufsdatum")));
    }

    @Test
    public void shouldReturnStatusOkIfStringContainsLUeberpruefungsdatum() throws Exception {
        this.mockMvc.perform(get("/materialverwaltung")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Letztes Überprüfungsdatum")));
    }

    @Test
    public void shouldReturnStatusOkIfStringContainsNUeberpruefungsdatum() throws Exception {
        this.mockMvc.perform(get("/materialverwaltung")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Nächstes Überprüfungsdatum")));
    }

    @Test
    public void shouldReturnStatusOkIfStringContainsEinsatzbereitschaft() throws Exception {
        this.mockMvc.perform(get("/materialverwaltung")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Einsatzbereitschaft")));
    }
}