package br.com.paripassu.ticketmanager.repo.ticket;

import br.com.paripassu.ticketmanager.model.id.ticket.TicketId;
import br.com.paripassu.ticketmanager.model.ticket.Ticket;
import br.com.paripassu.ticketmanager.model.ticket.TicketStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface TicketRepo extends CrudRepository<Ticket, TicketId> {

    @Query(value = "SELECT id_serie, numero, prioridade, tipo, status, data_hora_inclusao, data_hora_conclusao FROM ( " +
            "SELECT id_serie, numero, prioridade, tipo, status, data_hora_inclusao, data_hora_conclusao, row_number() OVER (ORDER BY prioridade DESC, numero ASC) AS n_linha FROM senhas_atendimento WHERE status = ?1 AND id_serie IN (SELECT id_serie FROM proximas_senhas) " +
            ") a WHERE n_linha < 2", nativeQuery = true)
    Ticket findNextTicket(String status);

    @Query(value = "SELECT id_serie, numero, prioridade, tipo, status, data_hora_inclusao, data_hora_conclusao FROM ( " +
            "SELECT id_serie, numero, prioridade, tipo, status, data_hora_inclusao, data_hora_conclusao, row_number() OVER (ORDER BY data_hora_conclusao DESC) AS n_linha FROM senhas_atendimento WHERE status = ?1 AND id_serie IN (SELECT id_serie FROM proximas_senhas) " +
            ") a WHERE n_linha < 2", nativeQuery = true)
    Ticket findCurrentTicket(String status);

    @Query(value = "SELECT id_serie, numero, prioridade, tipo, status, data_hora_inclusao, data_hora_conclusao FROM ( " +
            "SELECT id_serie, numero, prioridade, tipo, status, data_hora_inclusao, data_hora_conclusao, row_number() OVER (ORDER BY data_hora_conclusao DESC) AS n_linha FROM senhas_atendimento WHERE status = ?1 AND id_serie IN (SELECT id_serie FROM proximas_senhas) " +
            ") a WHERE n_linha > 1 AND (n_linha - 1) <= ?2", nativeQuery = true)
    ArrayList<Ticket> findPreviousTicketsByStatus(String status, Integer amount);

    ArrayList<Ticket> findAllByStatus(TicketStatus status);

}
