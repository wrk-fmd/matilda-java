package at.wrk.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import at.wrk.model.Benutzer;
import at.wrk.repository.UserRepository;

@Controller
public class UserController
{
	private UserRepository userRepository;
	
	@Autowired
	public UserController(UserRepository userRepository)
	{
		this.userRepository = userRepository;
	}
	
	@RequestMapping(value="/users" , method=RequestMethod.GET)
	public String users(Model model)
	{
		List<Benutzer> users = userRepository.findAll();
		
		if(users !=null)
		{
			model.addAttribute("benutzern",users);
		}
		return "users";
	}
	
	@RequestMapping(value="/users/{id}/delete", method = RequestMethod.POST)
	public String deleteUser(@PathVariable("id") long id) 
	{
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