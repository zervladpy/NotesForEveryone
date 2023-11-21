package com.example.uf1_proyecto_compose.data.task;

import java.util.List;

public interface TaskDAO {

    List<Task> getLocalTasks();

    void storeLocalTasks(Task task);

    List<Task> getRemoteTasks();

    void storeRemoteTask(Task task);

}
