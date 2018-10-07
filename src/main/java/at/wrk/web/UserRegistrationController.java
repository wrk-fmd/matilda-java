package at.wrk.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import at.wrk.model.Benutzer;
import at.wrk.service.UserService;
import at.wrk.web.dto.UserRegistrationDto;


import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    @Autowired
    private UserService userService;

    @ModelAttribute("benutzer")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("benutzer") @Valid UserRegistrationDto userDto,
                                      BindingResult result){

        Benutzer existing = userService.findByBenutzername(userDto.getBenutzername());
        if (existing != null){
            result.rejectValue("benutzername", null, "Es ist bereits ein Konto mit diesem Benutzernamen registriert");
        }

        if (result.hasErrors()){
            return "registration";
        }

        userService.save(userDto);
        return "redirect:/registration?success";
    }

}
