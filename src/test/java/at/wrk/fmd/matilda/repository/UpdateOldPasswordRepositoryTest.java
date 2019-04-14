package at.wrk.fmd.matilda.repository;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class UpdateOldPasswordRepositoryTest {
    
    @Mock
    private UserRepository repo;
    
    @Test
    public void givenCountMethodMocked_WhenCountInvoked_ThenMockedValueReturned() {
        UserRepository localMockRepository = Mockito.mock(UserRepository.class);
        Mockito.when(localMockRepository.count()).thenReturn(1L);
     
        long userCount = localMockRepository.count();
     
        Assert.assertEquals(1, userCount);
        Mockito.verify(localMockRepository).count();
    }
}