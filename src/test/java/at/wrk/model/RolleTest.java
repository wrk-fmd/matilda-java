package at.wrk.model;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RolleTest  {

	@Test
	public void rolleTest()  {
	        Rolle rolle = mock(Rolle.class);

	        when(rolle.getBezeichnung()).thenReturn("ADMIN");
	        when(rolle.getBezeichnung()).thenReturn("SUPERVISOR");
	        when(rolle.getBezeichnung()).thenReturn("BENUTZER");

//	        assertEquals(rolle.getBezeichnung(), "ADMIN");
//	        assertEquals(rolle.getBezeichnung(), "BENUTZER");
//	        assertEquals(rolle.getBezeichnung(), "SUPERVISOR");
	}
}
