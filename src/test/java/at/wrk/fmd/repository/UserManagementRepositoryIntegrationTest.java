package at.wrk.fmd.repository;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import at.wrk.fmd.dto.UserCreationDto;
import at.wrk.fmd.model.Benutzer;
import at.wrk.fmd.pojo.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserManagementRepositoryIntegrationTest {

    @Mock
    private UserRepository repo;
    
    Benutzer benutzer;
    List<Benutzer> users;
    User user;
    UserCreationDto userCreationdto;
    
    @Before
    public void setUp() {
        benutzer = new Benutzer();
        benutzer.setAnzeigename("ADMIN");
        
        user = new User();
        user.setAnzeigename("ADMIN");
        
        users = new ArrayList<>();
        users.add(benutzer);
        
        userCreationdto = new UserCreationDto();
        userCreationdto.addUser(user);
    }

    @Test
    public void testSaveUserFormMethod() {
        UserManagementService localMockRepository = Mockito.mock(UserManagementService.class);
        Mockito.when(localMockRepository.saveUserForm(users)).thenReturn(userCreationdto);
    }
}