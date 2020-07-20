package appdynamics.challenge.ui.notifications;

import java.util.ArrayList;
import java.util.List;

import appdynamics.challenge.ui.home.Item;

public class ValidatedTicket {

    private Item ticket;
    private String date;
    private String code;


    public static List<ValidatedTicket> ticketsList = new ArrayList<>();

    public ValidatedTicket (Item ticket, String date, String code){
        this.ticket = ticket;
        this.date = date;
        this.code = code;
    }

    public void insertTicket(ValidatedTicket ticket){
        ticketsList.add(ticket);
    }

    public Item getTicket() {
        return ticket;
    }

    public void setTicket(Item ticket) {
        this.ticket = ticket;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
