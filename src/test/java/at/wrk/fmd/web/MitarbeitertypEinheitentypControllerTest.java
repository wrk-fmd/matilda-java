package at.wrk.fmd.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import at.wrk.fmd.model.Einheitentyp;
import at.wrk.fmd.model.Materialtyp;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MitarbeitertypEinheitentypControllerTest {
    
	@Autowired
	private MitarbeitertypEinheitentypController mitEinController;

	//Simple test whether MitEinController isNotNull
	
	@Test
	public void contexLoads() throws Exception
	{
		assertThat(mitEinController).isNotNull();
	}
	
    @Test
    public void validateAllMitarbeitertypEinheitentyp() throws Exception {
        MitarbeitertypEinheitentypController dummy = mock(MitarbeitertypEinheitentypController.class);
        List<Einheitentyp> einheitentypen = new ArrayList<>();
        Einheitentyp einheit = new Einheitentyp();
        einheit.setName("TestTyp");
        einheitentypen.add(einheit);

        when(dummy.alleEinheitentypen()).thenReturn(einheitentypen);
    }
    
    @Test
    public void validateAllMaterialtyp() throws Exception {
        MaterialtypEinheitentypController dummy = mock(MaterialtypEinheitentypController.class);
        List<Materialtyp> materialtyp = new ArrayList<>();
        Materialtyp einheit = new Materialtyp();
        einheit.setName("TestTyp");
        materialtyp.add(einheit);

        when(dummy.alleMaterialtypen()).thenReturn(materialtyp);
    }
}