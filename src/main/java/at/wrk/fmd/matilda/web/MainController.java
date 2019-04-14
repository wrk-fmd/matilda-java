package at.wrk.fmd.matilda.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    final Logger logger = LoggerFactory.getLogger(MainController.class);
    
    @GetMapping("/")
    public String root() {
        // TODO SMELL: Are those loggings necessary? If you want to log all calls of mappings, Spring can provide this feature.
        //             Imo it is ugly to have it copy-pasted, and also on INFO level.
        logger.info("Method {} called in {}", new Object() {}.getClass().getEnclosingMethod().getName(), this.getClass().getName());
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        logger.info("Method {} called in {}", new Object() {}.getClass().getEnclosingMethod().getName(), this.getClass().getName());
        return "login";
    }
    
    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access-denied";
    }
    
    
}