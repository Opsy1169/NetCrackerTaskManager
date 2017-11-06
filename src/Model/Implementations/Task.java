package Model.Implementations;

import Model.Interfaces.TaskInterface;

import java.util.Date;

/**
 * Created by Opsymonroe on 06.11.2017.
 */
public class Task implements TaskInterface{
    private String name;
    private String discription;
    private Date date;
    private String[] contacts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String[] getContacts() {
        return contacts;
    }

    public void setContacts(String[] contacts) {
        this.contacts = contacts;
    }

    public Task(String name, String discription, Date date, String [] contacts){
        this.name = name;
        this.discription = discription;
        this.date = date;
        this.contacts = contacts;

    }
}
