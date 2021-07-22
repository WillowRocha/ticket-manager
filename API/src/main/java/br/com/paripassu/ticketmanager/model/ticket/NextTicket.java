package br.com.paripassu.ticketmanager.model.ticket;

import javax.persistence.*;

@Entity
@Table(name = "PROXIMAS_SENHAS")
public class NextTicket {

    @Id
    @Column(name = "TIPO")
    @Enumerated(EnumType.STRING)
    private TicketType type;

    @Column(name = "ID_SERIE")
    private Integer serieId;

    @Column(name = "NUMERO")
    private Integer number;

    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }

    public Integer getSerieId() {
        return serieId;
    }

    public void setSerieId(Integer serieId) {
        this.serieId = serieId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
