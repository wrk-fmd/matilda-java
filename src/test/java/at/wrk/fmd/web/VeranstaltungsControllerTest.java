package at.wrk.fmd.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import at.wrk.fmd.environment.AbstractJunitMatildaTest;
import at.wrk.fmd.model.Veranstaltung;
import at.wrk.fmd.repository.VeranstaltungRepository;

public class VeranstaltungsControllerTest extends AbstractJunitMatildaTest {
	@Autowired
	private VeranstaltungRepository veranstaltungRepository;

	@Test
	public void contexLoads() throws Exception
	{
		assertThat(veranstaltungRepository).isNotNull();
	}

	@Test
	public void shouldReturnVeranstaltungIsInserted() {
	    List<Veranstaltung> veranstaltungSize = veranstaltungRepository.findAll();
	    assertTrue(veranstaltungSize.size()>0);
	}

	@Test
	public void shouldReturnVeranstaltungWithNameIsCreated() {
	    Veranstaltung veranstaltungsName = veranstaltungRepository.findByName("Wien 2018");
	    assertTrue(veranstaltungsName.getName().equals("Wien 2018"));
	}

    @Test
    public void shouldReturnVeranstaltungWithZustand() {
        Veranstaltung veranstaltungsZustand = veranstaltungRepository.findByName("Wien 2018");
        assertTrue(veranstaltungsZustand.getZustand().equals("In Bearbeitung"));
    }

    @Test
    public void shouldReturnVeranstaltungWithBeginnDate() {
        Veranstaltung veranstaltungsZustand = veranstaltungRepository.findByName("Wien 2018");
        assertTrue(veranstaltungsZustand.getBeginn().toString().equals("2018-11-04T00:00"));
    }

    @Test
    public void shouldReturnVeranstaltungWithEndeDate() {
        Veranstaltung veranstaltungsZustand = veranstaltungRepository.findByName("Wien 2018");
        assertTrue(veranstaltungsZustand.getEnde().toString().equals("2018-11-05T00:00"));
    }

    @Test
    public void shouldReturnVeranstaltungWithLagerstandort() {
        Veranstaltung veranstaltungsZustand = veranstaltungRepository.findByName("Wien 2018");
        assertTrue(veranstaltungsZustand.getLagerstandort().getId() == 1);
    }
}