package at.wrk.fmd.matilda.web;

import java.util.List;

import at.wrk.fmd.matilda.model.Benutzer;
import at.wrk.fmd.matilda.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String users(Model model) {
        logger.info("Method {} called in {}", new Object() {}.getClass().getEnclosingMethod().getName(), this.getClass().getName());
        
        List<Benutzer> users = userRepository.findAll();

        if (users != null) {
            model.addAttribute("benutzern", users);
        }
        return "users";
    }

    @RequestMapping(value = "/users/{id}/delete", method = RequestMethod.POST)
    public String deleteUser(@PathVariable("id") long id) {
        logger.info("Method {} called in {}", new Object() {}.getClass().getEnclosingMethod().getName(), this.getClass().getName());
        
        userRepository.deleteById(id);

        return "redirect:/users";
    }

//	@RequestMapping(value="/{benutzername}" , method=RequestMethod.POST)
//	public String addToUserList(@PathVariable("benutzername") String benutzername, Benutzer benutzer)
//	{
//		benutzer.setBenutzername(benutzername);
//		userRepository.save(benutzer);
//		return "redirect:/{benutzername}";
//	}
}