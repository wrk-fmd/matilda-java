package at.wrk.fmd.web;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import at.wrk.fmd.environment.AbstractJunitMatildaTest;
import at.wrk.fmd.model.Mitarbeitertyp;
import at.wrk.fmd.repository.MitarbeitertypRepository;

public class MitarbeiterTypControllerTest extends AbstractJunitMatildaTest {
    
    @Autowired
    private MitarbeitertypRepository mitarbeitertyp;
    
    @Test
    public void contexLoads() throws Exception
    {
        assertThat(mitarbeitertyp).isNotNull();
    }
    
    @Test
    public void shouldReturnCountOfMitarbeitertyp() {
        assertThat(mitarbeitertyp.count()>0);
    }
    
    @Test
    public void shouldReturnKuerzel() {
        List<Mitarbeitertyp> mitarbeiter = mitarbeitertyp.findAll();
        String kuerzel = mitarbeiter.get(0).getKuerzel();
        assertThat(kuerzel.contains("Team 1"));
    }
    
    @Test
    public void shouldReturnName() {
        List<Mitarbeitertyp> mitarbeiter = mitarbeitertyp.findAll();
        String name = mitarbeiter.get(0).getName();
        assertThat(name.contains("Team 1"));
    }
}