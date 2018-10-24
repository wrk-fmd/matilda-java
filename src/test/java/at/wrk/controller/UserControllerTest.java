package at.wrk.controller;

import at.wrk.repository.DummyUserRepository;
import at.wrk.repository.UserRepository;
import at.wrk.web.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { UserController.class, DummyUserRepository.class })
@EnableConfigurationProperties
@ActiveProfiles("test")
public class UserControllerTest {

	@Before
	public void setUp() throws Exception {
	}

	@Mock
	UserRepository userRepository;
	
    @Autowired
    private UserController user;
    
    @Test
    public void deleteUserTest() throws Exception {
    	//user.setUserRepository(new DummyUserRepository());
    	user.deleteUser(1);
    }
}