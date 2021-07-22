package br.com.paripassu.ticketmanager.repo.ticket;

import br.com.paripassu.ticketmanager.model.ticket.NextTicket;
import br.com.paripassu.ticketmanager.model.ticket.TicketType;
import org.springframework.data.repository.CrudRepository;

public interface NextTicketRepo extends CrudRepository<NextTicket, TicketType> {

    NextTicket findOneByType(TicketType type);

}
