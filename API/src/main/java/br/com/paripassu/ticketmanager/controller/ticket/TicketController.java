package br.com.paripassu.ticketmanager.controller.ticket;

import br.com.paripassu.ticketmanager.http.response.PanelDataResponse;
import br.com.paripassu.ticketmanager.model.ticket.TicketType;
import br.com.paripassu.ticketmanager.model.user.UserType;
import br.com.paripassu.ticketmanager.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService service;

    @PostMapping("/generate")
    public void generateTicket(@RequestParam UserType userType, @RequestBody TicketType ticketType) {
        service.generateTicket(userType, ticketType);
    }

    @GetMapping("/panel")
    public PanelDataResponse getPanelData(@RequestParam UserType userType) {
        return service.getPanelData(userType);
    }

    @PostMapping("/next")
    public void callNextTicket(@RequestParam UserType userType) {
        service.callNextTicket(userType);
    }

    @PostMapping("/reset")
    public void resetTickets(@RequestParam UserType userType) {
        service.skipAllTicketsAndCreateNewSeries(userType);
    }
}