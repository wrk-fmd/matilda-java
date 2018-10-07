package at.wrk.application;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@TestPropertySource("application.properties")
public class ApplicationPropertiesTest {
 
      @Autowired
      protected Environment env;
 
      @Test
      public void verifyPropertyServerPortIsAvailableInEnvironment() {
    	  String serverPort = "server.port";
    	  assertEquals("8080", env.getProperty(serverPort));
      }

      @Test
      public void verifyPropertyUsernameIsAvailableInEnvironment() {
              String username = "spring.datasource.username";
              assertEquals("postgres", env.getProperty(username));
      }
       
      // -------------------------------------------------------------------
       @Configuration
      static class Config {
      }
 }