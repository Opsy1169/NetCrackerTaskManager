package Model.Implementations;

import Model.Interfaces.TaskInterface;
import Model.Interfaces.TaskJournalInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Opsymonroe on 06.11.2017.
 */
public class TaskJournal implements TaskJournalInterface{
    ArrayList<TaskInterface> list;
    UUID userId;

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
}
