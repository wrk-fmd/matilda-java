package at.wrk.fmd.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import at.wrk.fmd.dto.UserRegistrationDto;
import at.wrk.fmd.model.Benutzer;

public interface UserService extends UserDetailsService {

    Benutzer findByBenutzername(String benutzername);

    Benutzer save(UserRegistrationDto registration);
}