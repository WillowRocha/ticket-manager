package br.com.paripassu.ticketmanager.http.response;

import java.util.ArrayList;

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
}
