package com.nando.apiExample.model.dao;

import com.nando.apiExample.model.entity.Task;
import org.springframework.data.repository.CrudRepository;

public interface ITaskDao extends CrudRepository<Task, Integer> {
}
