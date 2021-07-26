package br.com.paripassu.ticketmanager.http.response;

import java.util.ArrayList;
import java.util.Objects;

public class PanelDataResponse {

    private String currentTicketCode;
    private ArrayList<String> lastTickets;

    public String getCurrentTicketCode() {
        return currentTicketCode;
    }

    public void setCurrentTicketCode(String currentTicketCode) {
        this.currentTicketCode = currentTicketCode;
    }

    public ArrayList<String> getLastTickets() {
        return lastTickets;
    }

    public void setLastTickets(ArrayList<String> lastTickets) {
        this.lastTickets = lastTickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PanelDataResponse that = (PanelDataResponse) o;

        if (!Objects.equals(currentTicketCode, that.currentTicketCode))
            return false;
        return Objects.equals(lastTickets, that.lastTickets);
    }

    @Override
    public int hashCode() {
        int result = currentTicketCode != null ? currentTicketCode.hashCode() : 0;
        result = 31 * result + (lastTickets != null ? lastTickets.hashCode() : 0);
        return result;
    }
}
