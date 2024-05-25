package com.project.TMSwithSec.controller;

import com.project.TMSwithSec.dto.TaskReqRes;
import com.project.TMSwithSec.service.TaskService;
import com.project.TMSwithSec.service.UsersManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StaffController {

    @Autowired
    private UsersManagementService usersManagementService;

    @Autowired
    private TaskService taskService;

    @GetMapping("/user/tasks/{userId}")
    public ResponseEntity<TaskReqRes> findUserTasks(@PathVariable Integer userId){
        return ResponseEntity.ok(taskService.findByUserId(userId));
    }

    @GetMapping("/user/tasks/completed/{userId}")
    public ResponseEntity<TaskReqRes> findCompletedTask(@PathVariable Integer userId){
        return ResponseEntity.ok(taskService.findByUserIdAndStatus(userId, "completed"));
    }

    @GetMapping("/user/tasks/uncompleted/{userId}")
    public ResponseEntity<TaskReqRes> findUncompletedTask(@PathVariable Integer userId){
        return ResponseEntity.ok(taskService.findByUserIdAndStatus(userId, "uncompleted"));
    }

    @GetMapping("/user/task/{taskId}")
    public ResponseEntity<TaskReqRes> findTaskByTaskId(@PathVariable Integer taskId){
        return ResponseEntity.ok(taskService.findById(taskId));
    }

    @PostMapping("/user/task")
    public ResponseEntity<TaskReqRes> addTask(@RequestBody TaskReqRes req){
        return ResponseEntity.ok(taskService.addTask(req));
    }

    @PutMapping("/user/task/{taskId}")
    public ResponseEntity<TaskReqRes> updateTask(@PathVariable Integer taskId, @RequestBody TaskReqRes req){
        return ResponseEntity.ok(taskService.update(taskId, req));
    }

    @DeleteMapping("/user/task/{taskId}")
    public ResponseEntity<TaskReqRes> deleteTask(@PathVariable Integer taskId){
        return ResponseEntity.ok(taskService.delete(taskId));
    }
}
