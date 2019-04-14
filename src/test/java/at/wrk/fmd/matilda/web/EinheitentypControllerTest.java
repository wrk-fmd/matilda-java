package at.wrk.fmd.matilda.web;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import at.wrk.fmd.matilda.environment.AbstractJunitMatildaTest;
import at.wrk.fmd.matilda.model.Einheitentyp;
import at.wrk.fmd.matilda.repository.EinheitentypRepository;


public class EinheitentypControllerTest extends AbstractJunitMatildaTest{
    @Autowired
    private EinheitentypRepository einheitentypRepository;
    
    //Simple test whether MaterialController isNotNull
    
    @Test
    public void contexLoads() throws Exception
    {
        assertThat(einheitentypRepository).isNotNull();
    }

    @Test
    public void shouldReturnEinheitenTypName() {
        List<Einheitentyp> einheit = einheitentypRepository.findAll();
        Einheitentyp einheitentyp = einheit.get(0);
        assertThat(einheitentyp.getName().equals("TYP 1"));
    }
}