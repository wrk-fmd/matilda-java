package at.wrk.fmd.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class EinheitentypControllerTest {
    @Autowired
    private EinheitentypController einheitentypController;

    @Autowired
    private WebApplicationContext webAppContext;
    private MockMvc mockMvc;
    
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }
    
    //Simple test whether MaterialController isNotNull
    
    @Test
    public void contexLoads() throws Exception
    {
        assertThat(einheitentypController).isNotNull();
    }
    
    @Test
    public void return405Error() throws Exception{
        MvcResult mvcResult = mockMvc.perform(put("/einheitentyp"))
                .andExpect(status().is4xxClientError()).andReturn();
        Assert.assertEquals("", mvcResult.getResponse().getContentAsString());
    }
}