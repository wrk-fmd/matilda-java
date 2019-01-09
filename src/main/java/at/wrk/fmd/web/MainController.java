package at.wrk.fmd.web;

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