package com.nando.apiExample.service;

import com.nando.apiExample.model.dao.ITaskDao;
import com.nando.apiExample.model.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService implements ITask{
    @Autowired
    ITaskDao taskDao;

    @Transactional
    @Override
    public Task save(Task task){
        return taskDao.save(task);
    }

    @Transactional(readOnly = true)
    public Task findById(Integer id) {
        return this.taskDao.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Task> findAll() {
        return (List<Task>) this.taskDao.findAll();
    }
}
