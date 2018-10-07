package at.wrk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import at.wrk.model.Benutzer;
import at.wrk.model.Rolle;
import at.wrk.repository.UserRepository;
import at.wrk.web.dto.UserRegistrationDto;


import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Benutzer findByBenutzername(String benutzername){
        return userRepository.findByBenutzername(benutzername);
    }

    public Benutzer save(UserRegistrationDto registration){
        Benutzer benutzer = new Benutzer();
        benutzer.setBenutzername(registration.getBenutzername());
        benutzer.setAnzeigename(registration.getAnzeigename());
        benutzer.setDienstnummer(registration.getDienstnummer());
        benutzer.setPasswort(passwordEncoder.encode(registration.getPasswort()));
        benutzer.setRollen(registration.getRollen());
        return userRepository.save(benutzer);
    }
    
    @Override
    public UserDetails loadUserByUsername(String benutzername) throws UsernameNotFoundException {
        Benutzer benutzer = userRepository.findByBenutzername(benutzername);
        if (benutzer == null){
            throw new UsernameNotFoundException("Falscher Benutzername oder Passwort.");
        }
        return new org.springframework.security.core.userdetails.User(benutzer.getBenutzername(),
                benutzer.getPasswort(),
                mapRolesToAuthorities(benutzer.getRollen()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Rolle> rollen){
        return rollen.stream()
                .map(rolle -> new SimpleGrantedAuthority(rolle.getBezeichnung()))
                .collect(Collectors.toList());
    }
}