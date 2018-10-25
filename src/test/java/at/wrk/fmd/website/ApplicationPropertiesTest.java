package at.wrk.fmd.website;

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
@TestPropertySource("application.propertiesTest")
public class ApplicationPropertiesTest {

    @Autowired
    protected Environment env;
    
    // Spring Boot Config

    @Test
    public void verifyPropertyServerPortIsAvailableInEnvironment() {
        String serverPort = "server.port";
        assertEquals("8080", env.getProperty(serverPort));
    }
    
    // Spring Boot Config

    // DB Config
    
    @Test
    public void verifyPropertyUsernameIsAvailableInEnvironment() {
        String username = "spring.datasource.username";
        assertEquals("postgres", env.getProperty(username));
    }

    @Test
    public void verifyPropertDatasourceIsAvailableInEnvironment() {
        String datasource = "spring.datasource.url";
        assertEquals("jdbc:postgresql://localhost/matilda", env.getProperty(datasource));
    }

    @Test
    public void verifyPropertyUsernameForDbIsAvailableInEnvironment() {
        String username = "spring.datasource.username";
        assertEquals("postgres", env.getProperty(username));
    }

    @Test
    public void verifyPropertyPasswordForDbIsAvailableInEnvironment() {
        String password = "spring.datasource.password";
        assertEquals("ENC(LBRtyWasc+a8xdLHl6xD/S5rzkAaiQIt6NFEZqIT4uI=)", env.getProperty(password));
    }

    @Test
    public void verifyPropertyDDLIsAvailableInEnvironment() {
        String ddl = "spring.jpa.generate-ddl";
        assertEquals("true", env.getProperty(ddl));
    }

    // DB Config
    
    // Logging

    @Test
        public void verifyPropertyConsoleIsAvailableInEnvironment() {
        String console = "logging.pattern.console";
        assertEquals("%d{yyyy-MM-dd HH:mm:ss} %-5level %logger{36} - %msg%n", env.getProperty(console));
    }
    
    @Test
    public void verifyPropertySQLDebugIsAvailableInEnvironment() {
        String console = "logging.level.org.hibernate.SQL";
        assertEquals("debug", env.getProperty(console));
    }

    @Test
    public void verifyPropertySBasicBinderIsAvailableInEnvironment() {
        String console = "logging.level.org.hibernate.type.descriptor.sql.BasicBinder";
        assertEquals("TRACE", env.getProperty(console));
    }
            
    // Logging
    
    // Security - Config

    @Test
    public void verifyPropertyEncryptorPasswordIsAvailableInEnvironment() {
        String encryptorPassword = "jasypt.encryptor.password";
        assertEquals("secretpassword", env.getProperty(encryptorPassword));
    }

    @Test
    public void verifyPropertEncryptorAlgoIsAvailableInEnvironment() {
        String algo = "jasypt.encryptor.algorithm";
        assertEquals("PBEWithMD5AndDES", env.getProperty(algo));
    }

    // Security - Config

    // Session Management

    @Test
    public void verifyPropertsessionTimeoutIsAvailableInEnvironment() {
        String legacyMode = "server.servlet.session.timeout";
        assertEquals("3600s", env.getProperty(legacyMode));
    }

    // Session Management
    
    // Thymeleaf configurations

    @Test
    public void verifyPropertLegacyHTMLIsAvailableInEnvironment() {
        String legacyMode = "spring.thymeleaf.mode";
        assertEquals("LEGACYHTML5", env.getProperty(legacyMode));
    }

    @Test
    public void verifyPropertThymeLeafCacheIsAvailableInEnvironment() {
        String cache = "spring.thymeleaf.cache";
        assertEquals("false", env.getProperty(cache));
    }
    
    // Thymeleaf configurations
    
    // ----------------------------------------------------------------------- //
    
    @Configuration
    static class Config {
    }
}