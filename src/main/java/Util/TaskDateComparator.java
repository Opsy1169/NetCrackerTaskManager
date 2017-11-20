package Util;

import Model.Interfaces.TaskInterface;

import java.util.Comparator;

/**
 * Created by Opsymonroe on 13.11.2017.
 */
public class TaskDateComparator implements Comparator<TaskInterface> {
    @Override
    public int compare(TaskInterface o1, TaskInterface o2) {

        if (o1.getDate().getTimeInMillis() < o2.getDate().getTimeInMillis())
            return -1;
        if (o1.getDate().getTimeInMillis() > o2.getDate().getTimeInMillis())
            return 1;
        return 0;
    }

}
