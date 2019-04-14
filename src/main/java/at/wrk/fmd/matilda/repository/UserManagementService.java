package at.wrk.fmd.matilda.repository;

import java.util.List;

import at.wrk.fmd.matilda.dto.UserCreationDto;
import at.wrk.fmd.matilda.model.Benutzer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserManagementService {
    @Query(value = "SELECT b.* "
            + "FROM Benutzer b "
            + "ORDER BY b.name", nativeQuery = true)
    List<?> getAllUsers();
    
    UserCreationDto saveUserForm(List<Benutzer> users);
    

    @Modifying
    @Query(value = "UPDATE benutzer SET active = :active,"
            + "username = :username,"
            + "anzeigename = :anzeigename,"
            + "dienstnummer = :dienstnummer"
            + " WHERE id = :id", nativeQuery = true)
    @Transactional
    void updateActivePassiveUser(Boolean active, String username, String anzeigename, String dienstnummer, long id);
}