package Model.Implementations;

import Model.Interfaces.TaskInterface;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Opsymonroe on 06.11.2017.
 */
public class Task implements TaskInterface {
    private String name;
    private String discription;
    private GregorianCalendar date;
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

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public String[] getContacts() {
        return contacts;
    }

    public void setContacts(String[] contacts) {
        this.contacts = contacts;
    }

    public Task(String name, String discription, GregorianCalendar date, String [] contacts){
        this.name = name;
        if(date.getTimeInMillis() < System.currentTimeMillis())
            throw new IllegalArgumentException();
        this.discription = discription;
        this.date = date;
        this.contacts = contacts;

    }

    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Task:\n");
        builder.append("Name: " + getName() + " \n");
        builder.append("Discription: " + getDiscription() + " \n");
        builder.append("Date: " + getDate().get(Calendar.YEAR) + "-" + (getDate().get(Calendar.MONTH) + 1) + "-" + getDate().get(Calendar.DAY_OF_MONTH)
                + " " + getDate().get(Calendar.HOUR_OF_DAY) + ":" + getDate().get(Calendar.MINUTE) + " \n");
        builder.append("Contacts: ");
        for (String str: contacts) {
            builder.append(str + "; ");
        }
        builder.append("\n");
        return builder.toString();
    }
}
