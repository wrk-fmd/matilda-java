package at.wrk.fmd.matilda.repository;

import static org.assertj.core.api.Assertions.assertThat;

import at.wrk.fmd.matilda.environment.AbstractJunitMatildaTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class BuchungRepositoryTest extends AbstractJunitMatildaTest {
    @Autowired
    private BuchungRepository buchungRepository;
  
    @Test
    public void contexLoads() throws Exception
    {
        assertThat(buchungRepository).isNotNull();
    }
    
//    List<Buchung> findByCreatedAtGreaterThan(Date createdAt);
//    List<Buchung> findByVeranstaltung(Veranstaltung veranstaltung);
//    List<Buchung> findByMaterial(Material material);
    
    @Test
    public void shouldReturnCreatedAtGreaterThan() {
    }
    
    @Test
    public void shouldReturnVeranstaltung() {
    }
    
    @Test
    public void shouldReturnMaterial() {
    }
}