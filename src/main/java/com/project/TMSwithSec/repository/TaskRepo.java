package com.project.TMSwithSec.repository;

import com.project.TMSwithSec.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task, Integer> {
    List<Task> findByAssignedUser_Id(int userId);

    List<Task> findByAssignedUser_IdAndStatus(int userId, String status);
}
