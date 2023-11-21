package com.example.uf1_proyecto_compose.data.task;

import java.util.List;

public class ITaskDao implements TaskDAO {

    private String remote;

    private String local;

    @Override
    public List<Task> getLocalTasks() {
        return null;
    }

    @Override
    public void storeLocalTasks(Task task) {

    }

    @Override
    public List<Task> getRemoteTasks() {
        return null;
    }

    @Override
    public void storeRemoteTask(Task task) {

    }
}
