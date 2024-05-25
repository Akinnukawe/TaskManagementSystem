package com.project.TMSwithSec.controller;

import com.project.TMSwithSec.dto.ReqRes;
import com.project.TMSwithSec.dto.TaskReqRes;
import com.project.TMSwithSec.entity.Task;
import com.project.TMSwithSec.entity.Users;
import com.project.TMSwithSec.service.TaskService;
import com.project.TMSwithSec.service.UsersManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserManagementController {

    @Autowired
    private UsersManagementService usersManagementService;

    @Autowired
    private TaskService taskService;

    @PostMapping("/auth/register")
    public ResponseEntity<ReqRes> register(@RequestBody ReqRes req){
        return ResponseEntity.ok(usersManagementService.register(req));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<ReqRes> login(@RequestBody ReqRes req){
        return ResponseEntity.ok(usersManagementService.login(req));
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<ReqRes> refresh(@RequestBody ReqRes req){
        return ResponseEntity.ok(usersManagementService.refreshToken(req));
    }

    @GetMapping("/admin/get-all-users")
    public ResponseEntity<ReqRes> getAllUsers(){
        return ResponseEntity.ok(usersManagementService.getAllUsers());
    }

    @GetMapping("/admin/get-users/{userId}")
    public ResponseEntity<ReqRes> getUser(@PathVariable Integer userId){
        return ResponseEntity.ok(usersManagementService.getUsersById(userId));
    }

    @GetMapping("/admin/get-staffs")
    public ResponseEntity<ReqRes> getStaffs(){
        return ResponseEntity.ok(usersManagementService.getUsersByRole("USER"));
    }

    @PutMapping("/admin/update/{userId}")
    public ResponseEntity<ReqRes> updateUser(@PathVariable Integer userId, @RequestBody Users req){
        return ResponseEntity.ok(usersManagementService.updateUser(userId, req));
    }

    @GetMapping("/adminuser/get-profile")
    public ResponseEntity<ReqRes> getMyProfile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        ReqRes res = usersManagementService.getMyInfo(email);
        return ResponseEntity.status(res.getStatusCode()).body(res);
    }

    @PutMapping("/admin/delete/{userId}")
    public ResponseEntity<ReqRes> deleteUser(@PathVariable Integer userId){
        return ResponseEntity.ok(usersManagementService.deleteUser(userId));
    }

    @GetMapping("/admin/tasks/{userId}")
    public ResponseEntity<TaskReqRes> findUserTasks(@PathVariable Integer userId){
        return ResponseEntity.ok(taskService.findByUserId(userId));
    }

    @GetMapping("/admin/tasks/completed/{userId}")
    public ResponseEntity<TaskReqRes> findCompletedTask(@PathVariable Integer userId){
        return ResponseEntity.ok(taskService.findByUserIdAndStatus(userId, "completed"));
    }

    @GetMapping("/admin/tasks/uncompleted/{userId}")
    public ResponseEntity<TaskReqRes> findUncompletedTask(@PathVariable Integer userId){
        return ResponseEntity.ok(taskService.findByUserIdAndStatus(userId, "uncompleted"));
    }

    @GetMapping("/admin/task/{taskId}")
    public ResponseEntity<TaskReqRes> findTaskByTaskId(@PathVariable Integer taskId){
        return ResponseEntity.ok(taskService.findById(taskId));
    }

    @PostMapping("/admin/task")
    public ResponseEntity<TaskReqRes> addTask(@RequestBody TaskReqRes req){
        return ResponseEntity.ok(taskService.addTask(req));
    }

    @PutMapping("/admin/task/{taskId}")
    public ResponseEntity<TaskReqRes> updateTask(@PathVariable Integer taskId, @RequestBody TaskReqRes req){
        return ResponseEntity.ok(taskService.update(taskId, req));
    }

    @DeleteMapping("/admin/task/{taskId}")
    public ResponseEntity<TaskReqRes> deleteTask(@PathVariable Integer taskId){
        return ResponseEntity.ok(taskService.delete(taskId));
    }
}
