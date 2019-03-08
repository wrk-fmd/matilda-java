package at.wrk.fmd.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
import at.wrk.fmd.service.UserManagementServiceImpl;

@Controller
@RequestMapping("/mitarbeiterverwaltung")
public class Mitarbeiterverwaltung {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    UserManagementServiceImpl userManagementServiceImpl;
    
    @RequestMapping
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPERVISOR')")
    public String showUserManagement(Model model) {
        logger.info("Method {} called in {}", new Object() {}.getClass().getEnclosingMethod().getName(), this.getClass().getName());

        List<Benutzer> users = userManagementServiceImpl.getAllUsers();
        UserCreationDto userForm = userManagementServiceImpl.saveUserForm(users);
        
        model.addAttribute("userForm", userForm);
        
        return "mitarbeiterverwaltung";
    }
    
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPERVISOR')")
    public String updateActivePassiveUser(@Valid @ModelAttribute("userForm") UserCreationDto userTableSettings,
            @RequestParam List<String> searchValues, BindingResult result, Model model, Errors errors) {
        
        logger.info("Method {} called in {}", new Object() {}.getClass().getEnclosingMethod().getName(), this.getClass().getName());
        
        if (errors.hasErrors()) {
            logger.error("Error in {}", new Object() {}.getClass().getEnclosingMethod().getName());
            return "error";
        }

        callUpdateActiveOrNotActiveUser(userTableSettings, searchValues);

        return "redirect:/mitarbeiterverwaltung?success";
    }

    private void callUpdateActiveOrNotActiveUser(UserCreationDto userTableSettings, List<String> searchValues) {
        int modelSize = userTableSettings.getUsers().size();
        
        for(int i = 1; i<=modelSize; i++) {
            String x = String.valueOf(i);
            if(searchValues.contains(x)) {
                Long id = userTableSettings.getUsers().get(i-1).getId();
                String username = userTableSettings.getUsers().get(i-1).getUsername();
                String anzeigename = userTableSettings.getUsers().get(i-1).getAnzeigename();
                String dienstnummer = userTableSettings.getUsers().get(i-1).getDienstnummer();
                
                userManagementServiceImpl.updateActivePassiveUser(new Boolean("true"), username, anzeigename, dienstnummer, id);                
            } else {
                Long id = userTableSettings.getUsers().get(i-1).getId();
                String username = userTableSettings.getUsers().get(i-1).getUsername();
                String anzeigename = userTableSettings.getUsers().get(i-1).getAnzeigename();
                String dienstnummer = userTableSettings.getUsers().get(i-1).getDienstnummer();
                
                userManagementServiceImpl.updateActivePassiveUser(new Boolean("false"), username, anzeigename, dienstnummer, id);
            }
        }
    }
}