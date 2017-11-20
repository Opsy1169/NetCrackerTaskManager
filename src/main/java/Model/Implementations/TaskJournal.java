package Model.Implementations;

import Model.Interfaces.TaskInterface;
import Model.Interfaces.TaskJournalInterface;
import Util.TaskDateComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

/**
 * Created by Opsymonroe on 06.11.2017.
 */
public class TaskJournal implements TaskJournalInterface{
    ArrayList<TaskInterface> list;
    UUID userId;

    public TaskJournal(ArrayList<TaskInterface> list){
        Collections.sort(list, new TaskDateComparator());
        this.list = list;
    }

    public ArrayList<TaskInterface> getList() {
        return list;
    }

    public void setList(ArrayList<TaskInterface> list) {
        this.list = list;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void addTask(TaskInterface task){
        list.add(task);
    }

    public void removeTask(int index){
        list.remove(index);
    }

    public void removeTask(TaskInterface task){
        list.remove(task);
    }

    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("TaskJournal:\n");
        for (TaskInterface task: list) {
            builder.append(task.toString());
        }
        return builder.toString();
    }
}
