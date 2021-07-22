package br.com.paripassu.ticketmanager.controller;

import br.com.paripassu.ticketmanager.http.response.PanelDataResponse;
import br.com.paripassu.ticketmanager.model.ticket.TicketType;
import br.com.paripassu.ticketmanager.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService service;

    @PostMapping("/generate")
    public void generateTicket(@RequestParam TicketType type) {
        service.generateTicket(type);
    }

    @GetMapping("/panel")
    public PanelDataResponse getPanelData() {
        return service.getPanelData();
    }

    @PostMapping("/next")
    public void callNextTicket() {
        service.callNextTicket();
    }

    @PostMapping("/reset")
    public void resetTickets() {
        service.skipAllTicketsAndCreateNewSeries();
    }
}