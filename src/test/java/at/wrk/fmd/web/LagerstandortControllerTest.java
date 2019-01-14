package at.wrk.fmd.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.WebApplicationContext;

import at.wrk.fmd.model.Benutzer;
import at.wrk.fmd.model.Lagerstandort;
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
 
    private Benutzer benutzer;
    private LagerstandortController dummy;
    private BindingResult bindingResult;
    private Lagerstandort nameOfLager;
    private Model model;
    @Autowired
    private WebApplicationContext webAppContext;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        benutzer = new Benutzer("ADMIN", "ADMIN");
        dummy = mock(LagerstandortController.class);
        bindingResult = mock(BindingResult.class);
        model = mock(Model.class);
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }
    
    //Simple test whether LagerstandortController, LagerstandortRepository,User Repository and isNotNull
    
    @Test
    public void contexLoads() throws Exception
    {
        assertThat(lagerstandortController).isNotNull();
        assertThat(lagerstandortRepository).isNotNull();
        assertThat(userRepository).isNotNull();
    }
 
    @Test
    public void validateAllUsers() throws Exception {
        List<Benutzer> userList = new ArrayList<>();
        userList.add(0, benutzer);

        when(dummy.alleBenutzer()).thenReturn(userList);
    }
    
    @Test
    public void validateAendernSpeichern() throws Exception {
        dummy.aendernSpeichern(1L, nameOfLager, bindingResult);
    }
    
    @Test
    public void return4xxxError() throws Exception{
        MvcResult mvcResult = mockMvc.perform(put("/lagerstandort"))
                .andExpect(status().is4xxClientError()).andReturn();
        Assert.assertEquals("", mvcResult.getResponse().getContentAsString());
    }
}