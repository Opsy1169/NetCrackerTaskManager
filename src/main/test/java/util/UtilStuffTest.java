package util;

import model.implementations.Task;
import model.interfaces.TaskJournalInterface;
import org.junit.Test;

import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class UtilStuffTest {

    private static boolean CHANGED = false;

    public static TaskJournalInterface loadingJournal() {
        TaskJournalInterface journal = null;
        try (DataInputStream in = new DataInputStream(new FileInputStream("SavedJournal.txt"));
             ObjectInputStream objIn = new ObjectInputStream(in)) {
            journal = (TaskJournalInterface) objIn.readObject();
            int before = journal.getList().size();
            journal = UtilStuff.deleteAllPassedDates(journal);
            if (before > journal.getList().size())
                CHANGED = true;

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return journal;
    }

    @Test
    public void loadJournal() {
        TaskJournalInterface actual = UtilStuff.loadJournal();
        TaskJournalInterface result = loadingJournal();
        assertEquals(result.getList().size(), actual.getList().size());
    }
}