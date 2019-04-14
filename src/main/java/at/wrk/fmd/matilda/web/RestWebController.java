package at.wrk.fmd.matilda.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import at.wrk.fmd.matilda.model.Veranstaltung;
import at.wrk.fmd.matilda.pojo.Event;
import at.wrk.fmd.matilda.repository.VeranstaltungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/event")
public class RestWebController {
    
    @Autowired
    VeranstaltungRepository eventRepo;

    @GetMapping(value = "/all")
    public String getEvents() {
        String jsonMsg = null;
        try {
            List<Event> listOfEvents = new ArrayList<Event>();
            List<?> events = eventRepo.findAll();
            
            for(Object veranstaltung : events) {
                Veranstaltung v = (Veranstaltung) veranstaltung;
                Event pojoEvent = new Event();
                pojoEvent.setTitle(v.getName());
                pojoEvent.setStart(v.getBeginn().toString());
                pojoEvent.setEnd(v.getEnde().toString());
                listOfEvents.add(pojoEvent);
            }
            
            ObjectMapper mapper = new ObjectMapper();
            jsonMsg =  mapper.writerWithDefaultPrettyPrinter().writeValueAsString(listOfEvents);
        } catch (IOException ioex) {
            System.out.println(ioex.getMessage());
        }
        return jsonMsg;
    }
}
