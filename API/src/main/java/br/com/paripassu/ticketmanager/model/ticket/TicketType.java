package br.com.paripassu.ticketmanager.model.ticket;

public enum TicketType {

    ORDINARY("N", 1),
    PREFERENTIAL("P", 2);

    private String prefix;
    private Integer priority;

    TicketType(String prefix, Integer priority) {
        this.prefix = prefix;
        this.priority = priority;
    }

    public static TicketType getTypeByPriority(Integer priority) {
        for (TicketType type : values()) {
            if (type.getPriority() == priority) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unable to find type by priority");
    }

    public String getPrefix() {
        return prefix;
    }

    public Integer getPriority() {
        return priority;
    }
}
