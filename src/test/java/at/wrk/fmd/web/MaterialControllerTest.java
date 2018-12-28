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
import at.wrk.fmd.model.Lagerstandort;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MaterialControllerTest {
	@Autowired
	private MaterialController materialController;
    
	//Simple test whether MaterialController isNotNull
	
	@Test
	public void contexLoads() throws Exception
	{
		assertThat(materialController).isNotNull();
	}
	
    @Test
    public void validateAllMaterial() throws Exception {
        MaterialController dummy = mock(MaterialController.class);
        List<Lagerstandort> listLagerstandort = new ArrayList<>();
        Lagerstandort lager = new Lagerstandort();
        lager.setAdresse("Wien-Traisengasse");
        lager.setName("Test");
        listLagerstandort.add(lager);

        when(dummy.alleLagerstandorten()).thenReturn(listLagerstandort);
    }
}
