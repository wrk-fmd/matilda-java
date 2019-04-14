package at.wrk.fmd.matilda.service;

import at.wrk.fmd.matilda.dto.UserRegistrationDto;
import at.wrk.fmd.matilda.model.Benutzer;
import at.wrk.fmd.matilda.model.Rolle;
import at.wrk.fmd.matilda.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository, final BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Benutzer findByBenutzername(final String benutzername) {
        return userRepository.findByBenutzername(benutzername);
    }

    @Override
    public Benutzer save(final UserRegistrationDto registration) {
        Benutzer benutzer = new Benutzer();
        benutzer.setBenutzername(registration.getBenutzername());
        benutzer.setAnzeigename(registration.getAnzeigename());
        benutzer.setDienstnummer(registration.getDienstnummer());
        benutzer.setPasswort(passwordEncoder.encode(registration.getPasswort()));
        benutzer.setRollen(registration.getRollen());
        return userRepository.save(benutzer);
    }

    @Override
    public UserDetails loadUserByUsername(final String benutzername) throws UsernameNotFoundException {
        Benutzer benutzer = userRepository.findByBenutzername(benutzername);
        if (benutzer == null) {
            LOG.debug("User {} does not exist in database.", benutzername);
            throw new UsernameNotFoundException("User does not exist in database.");
        }

        if (!benutzer.isActive()) {
            LOG.debug("User {} is not activated.", benutzername);
            throw new UsernameNotFoundException("User is not active.");
        }

        return new org.springframework.security.core.userdetails.User(
                benutzer.getBenutzername(),
                benutzer.getPasswort(),
                mapRolesToAuthorities(benutzer.getRollen()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(final Collection<Rolle> roles) {
        return roles.stream()
                .map(rolle -> new SimpleGrantedAuthority(rolle.getBezeichnung()))
                .collect(Collectors.toList());
    }
}