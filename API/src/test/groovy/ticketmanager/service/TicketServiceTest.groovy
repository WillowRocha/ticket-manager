package ticketmanager.service

import br.com.paripassu.ticketmanager.http.response.PanelDataResponse
import br.com.paripassu.ticketmanager.model.ticket.NextTicket
import br.com.paripassu.ticketmanager.model.ticket.Ticket
import br.com.paripassu.ticketmanager.model.ticket.TicketStatus
import br.com.paripassu.ticketmanager.model.ticket.TicketType
import br.com.paripassu.ticketmanager.repo.ticket.NextTicketRepo
import br.com.paripassu.ticketmanager.repo.ticket.TicketRepo
import br.com.paripassu.ticketmanager.service.TicketService
import org.springframework.security.access.AccessDeniedException
import spock.lang.Specification
import spock.lang.Unroll

import static br.com.paripassu.ticketmanager.model.ticket.TicketType.ORDINARY
import static br.com.paripassu.ticketmanager.model.ticket.TicketType.PREFERENTIAL
import static br.com.paripassu.ticketmanager.model.user.UserType.CUSTOMER
import static br.com.paripassu.ticketmanager.model.user.UserType.MANAGER

class TicketServiceTest extends Specification {

    TicketService service
    TicketRepo repo
    NextTicketRepo nextTicketRepo

    void setup() {
        repo = Mock()
        nextTicketRepo = Mock()
        service = new TicketService(repo, nextTicketRepo)
    }

    def "should correctly save next ticket and current ticket"() {
        given:
        NextTicket next = new NextTicket(type: ORDINARY, serieId: 2, number: 5)
        when:
        service.generateTicket(CUSTOMER, ORDINARY)
        then:
        1 * nextTicketRepo.findById(ORDINARY) >> Optional.of(next)
        1 * nextTicketRepo.save({ NextTicket it ->
            it.type == ORDINARY &&
                    it.serieId == 2 &&
                    it.number == 6
        })
        1 * repo.save({ Ticket it ->
            it.type == ORDINARY &&
                    it.seriesId == 2 &&
                    it.number == 5
        })
    }

    @Unroll
    def "Any user should be able to generate a new ticket of any type"() {
        when:
        service.generateTicket(userType, ticketType)
        then:
        1 * nextTicketRepo.findById(_ as TicketType) >> Optional.ofNullable(null)
        noExceptionThrown()
        where:
        userType | ticketType
        CUSTOMER | ORDINARY
        CUSTOMER | PREFERENTIAL
        MANAGER  | ORDINARY
        MANAGER  | PREFERENTIAL
    }

    def "should throw exception when user type is null"() {
        when:
        service.generateTicket(null, ORDINARY)
        then:
        thrown(AccessDeniedException)
    }

    def "should throw exception when ticket type is null"() {
        when:
        service.generateTicket(CUSTOMER, null)
        then:
        thrown(IllegalArgumentException)
    }

    def "should initialize next ticket without breaking when there is no data on database"() {
        when:
        service.generateTicket(CUSTOMER, ORDINARY)
        then:
        1 * nextTicketRepo.findById(ORDINARY) >> Optional.ofNullable(null)
        1 * nextTicketRepo.save({ NextTicket it ->
            it.type == ORDINARY &&
                    it.serieId == 1 &&
                    it.number == 2
        })
        1 * repo.save({ Ticket it ->
            it.type == ORDINARY &&
                    it.seriesId == 1 &&
                    it.number == 1
        })
    }

    @Unroll
    def "Any user should be able to get panel data"() {
        when:
        service.getPanelData(userType)
        then:
        1 * repo.findCurrentTicket(_ as String) >> null
        0 * _
        noExceptionThrown()
        where:
        userType << [CUSTOMER, MANAGER]
    }

    def "GetPanelData"() {
        when:
        PanelDataResponse result = service.getPanelData(CUSTOMER)
        then:
        1 * repo.findCurrentTicket(_ as String) >> new Ticket(number: 3, type: ORDINARY)
        1 * repo.findPreviousTicketsByStatus(_ as String, _ as Integer) >> [
                new Ticket(number: 1, type: ORDINARY),
                new Ticket(number: 1, type: PREFERENTIAL),
                new Ticket(number: 2, type: ORDINARY)
        ]
        result == new PanelDataResponse(currentTicketCode: 'N0003', lastTickets: ['N0001', 'P0001', 'N0002'])
    }

    def "Manager should be able to call next ticket"() {
        when:
        service.callNextTicket(MANAGER)
        then:
        noExceptionThrown()
    }

    def "Customer should not be able to call next ticket"() {
        when:
        service.callNextTicket(CUSTOMER)
        then:
        thrown(AccessDeniedException)
    }

    def "should update ticketStatus on database changing anything but status and conclusion time"() {
        given:
        Ticket ticket = new Ticket(type: ORDINARY, number: 2, seriesId: 1)
        when:
        service.callNextTicket(MANAGER)
        then:
        1 * repo.findNextTicket(_ as String) >> ticket
        1 * repo.save({ Ticket it ->
            it.status == TicketStatus.DONE &&
                    it.conclusionDateTime != null &&
                    it.type == ticket.type &&
                    it.number == ticket.number &&
                    it.seriesId == ticket.seriesId
        })
    }

    def "Manager should be able to rest tickets counting"() {
        when:
        service.skipAllTicketsAndCreateNewSeries(MANAGER)
        then:
        1 * repo.findAllByStatus(_ as TicketStatus) >> []
        1 * nextTicketRepo.findAll() >> []
        noExceptionThrown()
    }

    def "Customer should not be able to rest tickets counting"() {
        when:
        service.skipAllTicketsAndCreateNewSeries(CUSTOMER)
        then:
        thrown(AccessDeniedException)
    }

    def "should correctly update ticket status and series id"() {
        when:
        service.skipAllTicketsAndCreateNewSeries(MANAGER)
        then:
        1 * repo.findAllByStatus(_ as TicketStatus) >> [
                new Ticket(seriesId: 1, number: 2, type: ORDINARY),
                new Ticket(seriesId: 1, number: 3, type: PREFERENTIAL),
                new Ticket(seriesId: 1, number: 3, type: ORDINARY),
                new Ticket(seriesId: 1, number: 4, type: ORDINARY)
        ]
        1 * nextTicketRepo.findAll() >> [
                new NextTicket(serieId: 1, number: 5, type: ORDINARY),
                new NextTicket(serieId: 1, number: 4, type: PREFERENTIAL)
        ]
        1 * repo.saveAll({ List<Ticket> list ->
            list.every({ it.status == TicketStatus.SKIPPED && it.conclusionDateTime != null })
        })
        1 * nextTicketRepo.saveAll({ List<NextTicket> list ->
            list.every { it.serieId == 2 && it.number == 1 }
        })
    }
}
