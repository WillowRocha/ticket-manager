package br.com.paripassu.ticketmanager.model.ticket;

import br.com.paripassu.ticketmanager.model.id.ticket.TicketId;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@IdClass(TicketId.class)
@Table(name = "SENHAS_ATENDIMENTO")
public class Ticket {

    @Id
    @Column(name = "ID_SERIE")
    private Integer seriesId;

    @Id
    @Column(name = "NUMERO")
    private Integer number;

    @Id
    @Column(name = "PRIORIDADE")
    private Integer priotity;

    @Column(name = "TIPO")
    @Enumerated(EnumType.STRING)
    private TicketType type;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @Column(name = "DATA_HORA_INCLUSAO")
    private LocalDateTime inclusionDateTime;

    @Column(name = "DATA_HORA_CONCLUSAO")
    private LocalDateTime conclusionDateTime;

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

    public Integer getPriotity() {
        return priotity;
    }

    public void setPriotity(Integer priotity) {
        this.type = TicketType.getTypeByPriority(priotity);
        this.priotity = priotity;
    }

    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.priotity = type.getPriority();
        this.type = type;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public LocalDateTime getInclusionDateTime() {
        return inclusionDateTime;
    }

    public void setInclusionDateTime(LocalDateTime inclusionDateTime) {
        this.inclusionDateTime = inclusionDateTime;
    }

    public LocalDateTime getConclusionDateTime() {
        return conclusionDateTime;
    }

    public void setConclusionDateTime(LocalDateTime conclusionDateTime) {
        this.conclusionDateTime = conclusionDateTime;
    }
}
