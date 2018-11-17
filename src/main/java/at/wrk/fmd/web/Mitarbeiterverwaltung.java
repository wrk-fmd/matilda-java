package at.wrk.fmd.web;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import at.wrk.fmd.dto.UserCreationDto;
import at.wrk.fmd.model.Benutzer;
import at.wrk.fmd.pojo.User;
import at.wrk.fmd.service.UserManagementServiceImpl;

@Controller
@RequestMapping("/mitarbeiterverwaltung")
public class Mitarbeiterverwaltung {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    UserManagementServiceImpl userManagementServiceImpl;
    
    @RequestMapping
    public String showUserManagement(Model model) {
        logger.info("Method {} called in {}", new Object() {}.getClass().getEnclosingMethod().getName(), this.getClass().getName());

        List<Benutzer> users = userManagementServiceImpl.getAllUsers();
        UserCreationDto userForm = userManagementServiceImpl.saveUserForm(users);
        
        model.addAttribute("users", userForm);
        
        return "mitarbeiterverwaltung";
    }
    
    //TODO: create boolean active dataype
    @PostMapping
    public String updateActivePassiveUser(@ModelAttribute UserCreationDto userTableSettings,
            @RequestParam("checkBox") String checkBoxName, BindingResult result, Model model, Errors errors) {
        
        logger.info("Method {} called in {}", new Object() {}.getClass().getEnclosingMethod().getName(), this.getClass().getName());
        
        if (errors.hasErrors()) {
            logger.error("Error in {}", new Object() {}.getClass().getEnclosingMethod().getName());
            return "error";
        }

        List<User> users = userTableSettings.getUsers();
        userManagementServiceImpl.updateActivePassiveUser(1, 0);

        return "redirect:/mitarbeiterverwaltung?success";
    }
}