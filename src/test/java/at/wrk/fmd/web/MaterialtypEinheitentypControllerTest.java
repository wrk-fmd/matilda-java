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

@RunWith(SpringRunner.class)
@SpringBootTest
public class MaterialtypEinheitentypControllerTest {
    @Autowired
    private MaterialtypEinheitentypController matEinController;

    //Simple test whether MatEinController isNotNull
    
    @Test
    public void contexLoads() throws Exception
    {
        assertThat(matEinController).isNotNull();
    }
    
    @Test
    public void validateAllMaterialtypEinheitentyp() throws Exception {
        MaterialtypEinheitentypController dummy = mock(MaterialtypEinheitentypController.class);
        List<Einheitentyp> einheitentypen = new ArrayList<>();
        Einheitentyp einheit = new Einheitentyp();
        einheit.setName("TestTyp");
        einheitentypen.add(einheit);

        when(dummy.alleEinheitentypen()).thenReturn(einheitentypen);
    }
    
    @Test
    public void validateAllEinheitentyp() throws Exception {
        MitarbeitertypEinheitentypController dummy = mock(MitarbeitertypEinheitentypController.class);
        List<Einheitentyp> einheitentypen = new ArrayList<>();
        Einheitentyp einheit = new Einheitentyp();
        einheit.setName("TestTyp");
        einheitentypen.add(einheit);

        when(dummy.alleEinheitentypen()).thenReturn(einheitentypen);
    }
}
