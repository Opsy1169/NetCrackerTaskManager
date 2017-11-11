import Util.UtilStuff;
import Model.Implementations.Task;
import Model.Implementations.TaskJournal;
import Model.Interfaces.TaskInterface;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Opsymonroe on 06.11.2017.
 */
public class Main {
    public static void main(String[] args) {

        ArrayList<TaskInterface> tasklist = new ArrayList<>();
        Task task1 = new Task("Zadacha1", "CheckWork", new GregorianCalendar(2017, Calendar.DECEMBER, 10, 10, 30), new String[]{"9999999", "8888888"});
        tasklist.add(task1);
        Task task2 = new Task("Zadacha2", "CheckWork", new GregorianCalendar(2018, Calendar.DECEMBER, 10, 11, 30), new String[]{"9999999", "8888888"});
        tasklist.add(task2);
        Task task3 = new Task("Zadacha3", "asdasd", new GregorianCalendar(2018, Calendar.AUGUST, 20, 20, 0), new String[]{"9999999", "8888888"});
        tasklist.add(task3);
        Task task4 = new Task("Zadacha4", "letssee", new GregorianCalendar(2020, Calendar.DECEMBER, 10, 10, 30), new String[]{"9999999", "8888888"});
        tasklist.add(task4);
        Task task5 = new Task("Zadacha5", "letssee", new GregorianCalendar(2020, Calendar.DECEMBER, 10, 10, 30), new String[]{"9999999", "8888888"});
        tasklist.add(task5);
        Task task6 = new Task("Zadacha6", "letssee", new GregorianCalendar(2020, Calendar.DECEMBER, 10, 10, 30), new String[]{"9999999", "8888888"});
        tasklist.add(task6);
        TaskJournal journal = new TaskJournal(tasklist);
        //System.out.println(journal);
        UtilStuff.saveJournal(journal);
        TaskJournal newJournal = (TaskJournal) UtilStuff.loadJournal();
        //System.out.println(newJournal);
        long time = UtilStuff.getLeastTimeDelay(newJournal);
        System.out.println(time);
        //System.out.println("Hello world");
    }
}
