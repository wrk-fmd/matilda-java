package at.wrk.fmd.pojo;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserTest {

    User user;
    
    @BeforeEach
    void setUp() throws Exception {
        user = new User();
        user.setUsername("Testuser");
        user.setPassword("secret");
    }

    @Test
    void testUsername() {
        assertThat(user.getUsername().equals("Testuser"));
    }
    
    @Test
    void testPassword() {
        assertThat(user.getPassword().equals("secret"));
    }
}
