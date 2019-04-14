package at.wrk.fmd.matilda.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import at.wrk.fmd.matilda.environment.AbstractJunitMatildaTest;
import at.wrk.fmd.matilda.model.Lagerstandort;
import at.wrk.fmd.matilda.repository.LagerstandortRepository;


public class LagerstandortControllerTest extends AbstractJunitMatildaTest {
    @Autowired
    private LagerstandortRepository lagerstandortRepository;

    @Test
    public void contexLoads() throws Exception
    {
        assertThat(lagerstandortRepository).isNotNull();
    }
    
    @Test
    public void shouldReturnVeranstaltungIsInserted() {
        List<Lagerstandort> lagerstandortSize = lagerstandortRepository.findAll();
        assertTrue(lagerstandortSize.size()>0);
    }

    @Test
    public void shouldReturnVeranstaltungWithNameIsCreated() {
        Lagerstandort lagerstandort = lagerstandortRepository.findByName("Wien-Kenyongasse");
        assertTrue(lagerstandort.getName().equals("Wien-Kenyongasse"));
    }
    
    @Test
    public void shouldReturnVeranstaltungWithAdressIsCreated() {
        List<Lagerstandort> lagerstandort = lagerstandortRepository.findAll();
        assertTrue(lagerstandort.get(0).getAdresse().equalsIgnoreCase("Kenyongasse 100"));
    }
}