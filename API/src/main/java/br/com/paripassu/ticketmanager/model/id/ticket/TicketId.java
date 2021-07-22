package br.com.paripassu.ticketmanager.model.id.ticket;

import br.com.paripassu.ticketmanager.model.ticket.TicketType;

import java.io.Serializable;

public class TicketId implements Serializable {

    private Integer seriesId;
    private Integer number;
    private TicketType type;

    public Integer getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(Integer seriesId) {
        this.seriesId = seriesId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }
}
