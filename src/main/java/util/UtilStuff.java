package util;

import model.interfaces.TaskInterface;
import model.interfaces.TaskJournalInterface;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Opsymonroe on 06.11.2017.
 */
public class UtilStuff {
    public static boolean CHANGED = false;

    public static void saveJournal(TaskJournalInterface journal){
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream("SavedJournal.txt"));
             ObjectOutputStream objOut = new ObjectOutputStream(out)){
            objOut.writeObject(journal);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static TaskJournalInterface loadJournal(){
        TaskJournalInterface journal = null;
        try(DataInputStream in = new DataInputStream(new FileInputStream("SavedJournal.txt"));
            ObjectInputStream objIn = new ObjectInputStream(in)) {
            journal = (TaskJournalInterface) objIn.readObject();
            int before = journal.getList().size();
            journal = deleteAllPassedDates(journal);
            if(before > journal.getList().size())
                CHANGED = true;

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return journal;
    }

    public static void addTask(TaskJournalInterface journal, TaskInterface task){
        journal.addTask(task);
    }

    public static void removeTask(TaskJournalInterface journal, TaskInterface task){
        journal.removeTask(task);
    }
    public static void removeTask(TaskJournalInterface journal, int index){
        journal.removeTask(index);
    }


    public static long getLeastTimeDelay(TaskJournalInterface journalInterface){

        ArrayList<TaskInterface> tasks = journalInterface.getList();
        Collections.sort(tasks, (o1, o2) -> {
            if(o1.getDate().getTimeInMillis() < o2.getDate().getTimeInMillis())
                return -1;
            if(o1.getDate().getTimeInMillis() > o2.getDate().getTimeInMillis())
                return 1;
            return 0;
        });
        for (TaskInterface task: tasks) {
            System.out.println(task);
        }
        return tasks.get(0).getDate().getTimeInMillis() - System.currentTimeMillis();
    }

    public static TaskJournalInterface deleteAllPassedDates(TaskJournalInterface journalInterface){
        ArrayList<TaskInterface> tasks = journalInterface.getList();
        ArrayList<TaskInterface> deleted = new ArrayList<>();
        for (TaskInterface task : tasks) {
            if(task.getDate().getTimeInMillis() < System.currentTimeMillis())
                deleted.add( task);
        }
        tasks.removeAll(deleted);
        journalInterface.setList(tasks);
        return journalInterface;
    }
    //just checking pull
    public static mul(int a, int b){
        return a*b;
    }
}
