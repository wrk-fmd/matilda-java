package at.wrk.fmd.web;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import at.wrk.fmd.repository.LagerstandortRepository;
import at.wrk.fmd.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LagerstandortControllerTest {
    @Autowired
    private LagerstandortController lagerstandortController;
    
    @Autowired
    private LagerstandortRepository lagerstandortRepository;
    
    @Autowired
    private UserRepository userRepository;

    //Simple test whether LagerstandortController, LagerstandortRepository,User Repository and isNotNull
    
    @Test
    public void contexLoads() throws Exception
    {
        assertThat(lagerstandortController).isNotNull();
        assertThat(lagerstandortRepository).isNotNull();
        assertThat(userRepository).isNotNull();
    }
}
