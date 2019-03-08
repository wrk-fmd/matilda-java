package at.wrk.fmd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.wrk.fmd.dto.UserCreationDto;
import at.wrk.fmd.model.Benutzer;

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