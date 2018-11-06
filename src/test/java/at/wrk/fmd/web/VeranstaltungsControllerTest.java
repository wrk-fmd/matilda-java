package at.wrk.fmd.web;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class VeranstaltungsControllerTest {
	@Autowired
	private VeranstaltungController veranstaltungsController;

	//Simple test whether VeranstaltungController isNotNull
	
	@Test
	public void contexLoads() throws Exception
	{
		assertThat(veranstaltungsController).isNotNull();
	}
	
	@Test
	public void shouldReturnVeranstaltungIsInserted() {
	    
	}
}
