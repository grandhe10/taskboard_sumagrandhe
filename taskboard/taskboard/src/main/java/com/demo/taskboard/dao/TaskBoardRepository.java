package com.demo.taskboard.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.taskboard.model.TaskBoard;

public interface TaskBoardRepository extends JpaRepository<TaskBoard, Long>{
	
	Optional<List<TaskBoard>> findByAssignorUserSapId(String userSapIdId);
	
	List<TaskBoard> findAll();
	
	Optional<List<TaskBoard>> findByTaskLastUpdatedDateAndIsActiveAndAssigneeUserSapId(LocalDate date,Boolean active,String assigneeUserSapIdId);

	Optional<List<TaskBoard>> findByAssigneeUserSapId(String assignorUserSapIdId);

	Optional<List<TaskBoard>> findByAssignorUserSapIdAndTaskLastUpdatedDate(String userSapId,
			LocalDate parse);

	Optional<TaskBoard> findByAssigneeUserSapIdAndTaskIdAndIsActive(String userSapId, String taskId, Boolean true1);

	Optional<List<TaskBoard>> findByAssigneeUserSapIdAndTaskLastUpdatedDate(String userSapId, LocalDate parse);


}
