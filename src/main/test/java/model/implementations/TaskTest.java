package model.implementations;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class TaskTest {
    private String name = "qwerty";
    private String discription = "asdf";
    private String[] contacts = {"111", "222", "333"};
    private GregorianCalendar date = new GregorianCalendar(2018, Calendar.DECEMBER, 10, 10, 30);

    /*public TaskTest(String name, String discription, GregorianCalendar date, String[] contacts) {
        this.name = name;
        if (date.getTimeInMillis() < System.currentTimeMillis())
            throw new IllegalArgumentException();
        this.discription = discription;
        this.date = date;
        this.contacts = contacts;
    }*/


    //TaskTest taskTest = new TaskTest("qwerty", "asdf", date, contacts);

    Task task = new Task("qwerty", "asdf", date, contacts);

    @Test
    public void getName() {
        String actual = task.getName();
        String result = name;
        assertEquals(result, actual);
    }

    @Test
    public void setName() {
        name = "qwerty2";
        String result = name;
        task.setName("qwerty2");
        String actual = task.getName();
        assertEquals(result, actual);
    }

    @Test
    public void getDiscription() {
        String actual = task.getDiscription();
        String result = discription;
        assertEquals(result, actual);
    }

    @Test
    public void setDiscription() {
        discription = "asdf2";
        String result = discription;
        task.setDiscription("asdf2");
        String actual = task.getDiscription();
        assertEquals(result, actual);
    }

    @Test
    public void getDate() {
        GregorianCalendar actual = task.getDate();
        GregorianCalendar result = date;
        assertEquals(result, actual);
    }

    @Test
    public void setDate() {
        GregorianCalendar date2 = new GregorianCalendar(2020, Calendar.NOVEMBER, 1, 15, 30);
        GregorianCalendar result = date2;
        task.setDate(date2);
        GregorianCalendar actual = task.getDate();
        assertEquals(result, actual);
    }

    @Test
    public void getContacts() {
        String[] result = contacts;
        String[] actual = task.getContacts();
        assertArrayEquals(result, actual);
    }

    @Test
    public void setContacts() {
        String[] contacts2 = {"111", "222", "333"};
        String[] result = contacts2;
        task.setContacts(contacts2);
        String[] actual = task.getContacts();
        assertArrayEquals(result, actual);
    }
}