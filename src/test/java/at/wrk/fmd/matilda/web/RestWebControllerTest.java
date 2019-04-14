package at.wrk.fmd.matilda.web;

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
public class RestWebControllerTest {
    @Autowired
    private RestWebController restWebController;

    //Simple test whether RestWebController isNotNull
    
    @Test
    public void contexLoads() throws Exception
    {
        assertThat(restWebController).isNotNull();
    }
}