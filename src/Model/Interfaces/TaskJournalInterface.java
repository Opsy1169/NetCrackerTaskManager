package Model.Interfaces;

import java.io.Serializable;

/**
 * Created by Opsymonroe on 06.11.2017.
 */
public interface TaskJournalInterface extends Serializable {
    public  void addTask(TaskInterface task);
    public void removeTask(TaskInterface task);
    public void removeTask(int index);
}
