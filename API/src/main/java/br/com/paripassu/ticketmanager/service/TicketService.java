package br.com.paripassu.ticketmanager.service;

import br.com.paripassu.ticketmanager.http.response.PanelDataResponse;
import br.com.paripassu.ticketmanager.model.ticket.NextTicket;
import br.com.paripassu.ticketmanager.model.ticket.Ticket;
import br.com.paripassu.ticketmanager.model.ticket.TicketStatus;
import br.com.paripassu.ticketmanager.model.ticket.TicketType;
import br.com.paripassu.ticketmanager.repo.ticket.NextTicketRepo;
import br.com.paripassu.ticketmanager.repo.ticket.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class TicketService {

    private TicketRepo repo;
    private NextTicketRepo nextTicketRepo;
    private DecimalFormat format;

    @Autowired
    public TicketService(TicketRepo repo, NextTicketRepo nextTicketRepo) {
        this.repo = repo;
        this.nextTicketRepo = nextTicketRepo;
        this.format = new DecimalFormat("0000");
    }

    @Transactional
    public void generateTicket(TicketType type) {
        if (type == null) {
            throw new IllegalArgumentException("Type cannot be null");
        }
        NextTicket next = nextTicketRepo.findById(type)
                .orElse(getNewNextTicket(type));

        Ticket ticket = getNewTicket(next);

        next.setNumber(next.getNumber() + 1);
        nextTicketRepo.save(next);
        repo.save(ticket);
    }

    public PanelDataResponse getPanelData() {
        PanelDataResponse response = new PanelDataResponse();

        Ticket lastCalledTicket = repo.findCurrentTicketByStatus(TicketStatus.DONE.name());
        if (lastCalledTicket == null) {
            return new PanelDataResponse();
        }
        ArrayList<Ticket> lastTickets = repo.findPreviousTicketsByStatus(TicketStatus.DONE.name(), 5);

        response.setCurrentTicketCode(this.parseTicketNumber(lastCalledTicket));
        response.setLastTickets(this.getParsedTicketNumbers(lastTickets));
        return response;
    }

    public void callNextTicket() {
        Ticket ticket = repo.findNextTicketStatus(TicketStatus.PENDING.name());

        if (ticket != null) {
            ticket.setStatus(TicketStatus.DONE);
            ticket.setConclusionDateTime(LocalDateTime.now());
            repo.save(ticket);
        }
    }

    @Transactional
    public void skipAllTicketsAndCreateNewSeries() {
        ArrayList<Ticket> tickets = repo.findAllByStatus(TicketStatus.PENDING);
        for (Ticket ticket : tickets) {
            ticket.setStatus(TicketStatus.SKIPPED);
            ticket.setConclusionDateTime(LocalDateTime.now());
        }
        Iterable<NextTicket> nextTickets = nextTicketRepo.findAll();
        for (NextTicket next : nextTickets) {
            next.setNumber(1);
            next.setSerieId(next.getSerieId() + 1);
        }
        repo.saveAll(tickets);
        nextTicketRepo.saveAll(nextTickets);
    }

    private NextTicket getNewNextTicket(TicketType type) {
        NextTicket next = new NextTicket();
        next.setSerieId(1);
        next.setNumber(1);
        next.setType(type);
        return next;
    }

    private Ticket getNewTicket(NextTicket nextTicket) {
        if (nextTicket == null) {
            throw new IllegalArgumentException("Next ticket cannot be null");
        }
        Ticket ticket = new Ticket();
        ticket.setType(nextTicket.getType());
        ticket.setNumber(nextTicket.getNumber());
        ticket.setSeriesId(nextTicket.getSerieId());
        ticket.setStatus(TicketStatus.PENDING);
        ticket.setInclusionDateTime(LocalDateTime.now());
        return ticket;
    }

    private String parseTicketNumber(Ticket ticket) {
        if (ticket == null) {
            throw new IllegalArgumentException("Ticket cannot be null");
        }
        return ticket.getType().getPrefix() + format.format(ticket.getNumber());
    }

    private ArrayList<String> getParsedTicketNumbers(ArrayList<Ticket> tickets) {
        ArrayList<String> parsedTicketNumbers = new ArrayList<>();
        tickets.forEach(currentTicket -> parsedTicketNumbers.add(this.parseTicketNumber(currentTicket)));
        return parsedTicketNumbers;
    }
}
