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
        Collection listOne = new ArrayList<>();
        Collection listTwo = new ArrayList<>();
        
        for(int i = 0; i<modelSize; i++) {
            listOne.add(userTableSettings.getUsers().get(i).getId());
        }
        
        for(int i = 0; i<searchValues.size(); i++) {
            listTwo.add(Long.parseLong(searchValues.get(i)));
        }
        
        Collection<Long> similar = new HashSet<Long>( listOne );
        Collection<Long> different = new HashSet<Long>();
        different.addAll( listOne );
        different.addAll( listTwo );

        similar.retainAll( listTwo );
        different.removeAll( similar );

        Iterator<Long> iteratorSimilar = similar.iterator();
        Iterator<Long> iteratorDifferent = different.iterator();
        
        while (iteratorSimilar.hasNext()) {
            userManagementServiceImpl.updateActivePassiveUser(new Boolean("true"), iteratorSimilar.next());
        }

        while (iteratorDifferent.hasNext()) {
            userManagementServiceImpl.updateActivePassiveUser(new Boolean("false"), iteratorDifferent.next());
        }
    }
}