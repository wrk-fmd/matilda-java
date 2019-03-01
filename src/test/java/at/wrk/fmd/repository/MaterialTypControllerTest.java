package at.wrk.fmd.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import at.wrk.fmd.environment.AbstractJunitMatildaTest;
import at.wrk.fmd.model.Material;

public class MaterialTypControllerTest extends AbstractJunitMatildaTest {
    
    @Autowired
    private MaterialRepository materialRepository;
  
    @Test
    public void contexLoads() throws Exception
    {
        assertThat(materialRepository).isNotNull();
    }
    
    @Test
    public void shouldReturnMateriaTyplByIdGreaterZero() {
        Material material = materialRepository.findById(1L);
        int count = material.getBestand();
        assertTrue(count>0);
    }
    
    @Test
    public void shouldReturnMaterialTypBezeichnungByIdGreaterZero() {
        Material material = materialRepository.findById(1L);
        String typ = material.getBezeichnung();
        assertTrue(typ.contains("Aspirin"));
    }
}