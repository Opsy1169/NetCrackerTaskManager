package model.interfaces;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Opsymonroe on 06.11.2017.
 */
public interface TaskJournalInterface extends Serializable {
    public  void addTask(TaskInterface task);
    public void removeTask(TaskInterface task);
    public void removeTask(int index);
    public ArrayList<TaskInterface> getList();
    public void setList(ArrayList<TaskInterface> list);
}
