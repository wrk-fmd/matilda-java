package at.wrk.fmd.matilda.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import at.wrk.fmd.matilda.dto.UserRegistrationDto;
import at.wrk.fmd.matilda.model.Benutzer;

public interface UserService extends UserDetailsService {

    Benutzer findByBenutzername(String benutzername);

    Benutzer save(UserRegistrationDto registration);
}