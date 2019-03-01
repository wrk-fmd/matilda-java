package at.wrk.fmd.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import at.wrk.fmd.environment.AbstractJunitMatildaTest;
import at.wrk.fmd.model.Lagerstandort;
import at.wrk.fmd.model.Materialtyp;
import at.wrk.fmd.model.Veranstaltung;
import at.wrk.fmd.repository.MaterialRepository;

public class MaterialControllerTest extends AbstractJunitMatildaTest{	
	@Autowired
	private MaterialRepository materialRepository;
	private Lagerstandort lagerstandort;
    
	//Simple test whether MaterialController isNotNull
	
	@Test
	public void contexLoads() throws Exception
	{
		assertThat(materialRepository).isNotNull();
	}
	
	@Before
	public void setup() {
	    lagerstandort = new Lagerstandort();
	    lagerstandort.setAdresse("Wien-Kenyongasse");
	}
	
    @Test
    public void validateAllLagerstandorte() throws Exception {
        MaterialController dummy = mock(MaterialController.class);
        List<Lagerstandort> listLagerstandort = new ArrayList<>();
        Lagerstandort lager = new Lagerstandort();
        lager.setAdresse("Wien-Traisengasse");
        lager.setName("Test");
        listLagerstandort.add(lager);

        when(dummy.alleLagerstandorten()).thenReturn(listLagerstandort);
    }
    
    @Test
    public void validateAllMaterial() throws Exception {
        MaterialController dummy = mock(MaterialController.class);
        List<Materialtyp> listAllMaterial = new ArrayList<>();
        Materialtyp mat = new Materialtyp();
        mat.setBeschreibung("Test");
        listAllMaterial.add(mat);

        when(dummy.alleMaterialtypen()).thenReturn(listAllMaterial);
    }
    
    @Test
    public void validateAlleVerstanstaltungen() throws Exception {
        MaterialController dummy = mock(MaterialController.class);
        List<Veranstaltung> listAlleVeranstaltungen = new ArrayList<>();
        Veranstaltung veranstaltung = new Veranstaltung();
        veranstaltung.setName("Test");
        listAlleVeranstaltungen.add(veranstaltung);

        when(dummy.alleVeranstaltungen()).thenReturn(listAlleVeranstaltungen);
    }
    
    @Test
    public void validateNeuMaterial() throws Exception {
        MaterialController dummy = mock(MaterialController.class);
        Model model = mock(Model.class);

        when(dummy.neuMaterial(model)).thenReturn("Test");
    }    
}