package com.demo.taskboard.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.taskboard.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{

	Optional<Task> findByTaskIdAndIsActive(String taskId,Boolean active);
	
	

}
