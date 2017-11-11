package Util;

import Model.Interfaces.TaskInterface;
import Model.Interfaces.TaskJournalInterface;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Opsymonroe on 06.11.2017.
 */
public class UtilStuff {

    public static void saveJournal(TaskJournalInterface journal){
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream("SavedJournal.txt"));
            ObjectOutputStream objOut = new ObjectOutputStream(out);
            objOut.writeObject(journal);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static TaskJournalInterface loadJournal(){
        TaskJournalInterface journal = null;
        try {
            DataInputStream in = new DataInputStream(new FileInputStream("SavedJournal.txt"));
            ObjectInputStream objIn = new ObjectInputStream(in);
            journal = (TaskJournalInterface) objIn.readObject();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
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
        Collections.sort(tasks, new Comparator<TaskInterface>() {
            @Override
            public int compare(TaskInterface o1, TaskInterface o2) {
                if(o1.getDate().getTimeInMillis() < o2.getDate().getTimeInMillis())
                    return -1;
                if(o1.getDate().getTimeInMillis() > o2.getDate().getTimeInMillis())
                    return 1;
                return 0;
            }
        });
        System.out.println("-----------------------getLeastTimeDelay-----------------");
        for (TaskInterface task: tasks) {
            System.out.println(task);
        }
        return tasks.get(0).getDate().getTimeInMillis() - System.currentTimeMillis();
    }
}
