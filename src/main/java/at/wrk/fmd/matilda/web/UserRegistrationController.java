package at.wrk.fmd.matilda.web;

import at.wrk.fmd.matilda.model.Benutzer;
import at.wrk.fmd.matilda.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import at.wrk.fmd.matilda.dto.UserRegistrationDto;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private UserService userService;

    @ModelAttribute("benutzer")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        logger.info("Method {} called in {}", new Object() {}.getClass().getEnclosingMethod().getName(), this.getClass().getName());
        
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("benutzer") @Valid UserRegistrationDto userDto,
            BindingResult result) {
        
        logger.info("Method {} called in {}", new Object() {}.getClass().getEnclosingMethod().getName(), this.getClass().getName());
        
        Benutzer existing = userService.findByBenutzername(userDto.getBenutzername());
        if (existing != null) {
            result.rejectValue("benutzername", null, "Es ist bereits ein Konto mit diesem Benutzernamen registriert");
        }

        if (result.hasErrors()) {
            logger.error("Error in method {}, called in {}", new Object() {}.getClass().getEnclosingMethod().getName(), this.getClass().getName());
            return "registration";
        }

        userService.save(userDto);
        return "redirect:/registration?success";
    }
}