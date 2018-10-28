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
        logger.info("index called in {}", new Object() {}.getClass().getEnclosingMethod().getName());
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        logger.info("login called in {}", new Object() {}.getClass().getEnclosingMethod().getName());
        return "login";
    }

    @GetMapping("/user")
    public String userIndex() {
        logger.info("user called in {}", new Object() {}.getClass().getEnclosingMethod().getName());
        return "user/index";
    }

    @GetMapping("/mitarbeiterverwaltung")
    public String mitarbeiterverwaltung() {
        logger.info("mitarbeiterverwaltung called in {}", new Object() {}.getClass().getEnclosingMethod().getName());
        return "mitarbeiterverwaltung";
    }
}