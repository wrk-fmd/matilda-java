package at.wrk.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import at.wrk.model.Benutzer;
import at.wrk.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {

    Benutzer findByBenutzername(String benutzername);

    Benutzer save(UserRegistrationDto registration);   
}