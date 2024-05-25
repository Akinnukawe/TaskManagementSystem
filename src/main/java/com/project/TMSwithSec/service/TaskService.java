package com.project.TMSwithSec.service;

import com.project.TMSwithSec.dto.TaskReqRes;
import com.project.TMSwithSec.entity.Task;
import com.project.TMSwithSec.entity.Users;
import com.project.TMSwithSec.repository.TaskRepo;
import com.project.TMSwithSec.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private UserRepo userRepo;

    public TaskReqRes findById(int id){
        TaskReqRes taskReqRes = new TaskReqRes();
        try{
            Task task = taskRepo.findById(id).orElseThrow(() -> new RuntimeException("Task Not Found"));
            taskReqRes.setTask(task);
            taskReqRes.setUserEmail(task.getAssignedUser().getEmail());
            taskReqRes.setUserFirstname(task.getAssignedUser().getFirstname());
            taskReqRes.setUserLastname(task.getAssignedUser().getLastname());
            taskReqRes.setStatusCode(200);
            taskReqRes.setMessage("Successful");
            return taskReqRes;
        } catch(Exception e){
            taskReqRes.setStatusCode(500);
            taskReqRes.setMessage("Error occured: "+ e.getMessage());
            return taskReqRes;
        }
    }

    public TaskReqRes findByUserId(int userId){
        TaskReqRes taskReqRes = new TaskReqRes();
        try{
            List<Task> task = taskRepo.findByAssignedUser_Id(userId);
            Optional<Users> result = userRepo.findById(userId);
            if(!task.isEmpty()){
                Users user = result.get();
                taskReqRes.setTasks(task);
                taskReqRes.setUserEmail(user.getEmail());
                taskReqRes.setUserFirstname(user.getFirstname());
                taskReqRes.setUserLastname(user.getLastname());
            } else {
                taskReqRes.setStatusCode(404);
                taskReqRes.setMessage("User not Found");
            }
            return  taskReqRes;
        } catch (Exception e){
            taskReqRes.setStatusCode(500);
            taskReqRes.setMessage("Error Occurred: "+ e.getMessage());
            return taskReqRes;
        }
    }

    public TaskReqRes findByUserIdAndStatus(int userId, String status){
        TaskReqRes taskReqRes = new TaskReqRes();
        try{
            List<Task> task = taskRepo.findByAssignedUser_IdAndStatus(userId, status);
            Optional<Users> result = userRepo.findById(userId);
            if(!task.isEmpty()){
                Users user = result.get();
                taskReqRes.setTasks(task);
                taskReqRes.setUserEmail(user.getEmail());
                taskReqRes.setUserFirstname(user.getFirstname());
                taskReqRes.setUserLastname(user.getLastname());
            } else {
                taskReqRes.setStatusCode(404);
                taskReqRes.setMessage("User not Found");
            }
            return  taskReqRes;
        } catch (Exception e){
            taskReqRes.setStatusCode(500);
            taskReqRes.setMessage("Error Occurred: "+ e.getMessage());
            return taskReqRes;
        }
    }

    public TaskReqRes addTask(TaskReqRes req){
        TaskReqRes res = new TaskReqRes();
        Task task = new Task();
        try{
            Optional<Users> result = userRepo.findByEmail(req.getUserEmail());
            if(result.isPresent()){
                Users user = result.get();
                task.setName(req.getName());
                task.setDescription(req.getDescription());
                task.setStatus(req.getStatus());
                task.setDeadline(req.getDeadline());
                task.setAssignedUser(user);
                taskRepo.save(task);
                res.setStatusCode(200);
                res.setMessage("Successful");
            } else {
                res.setStatusCode(404);
                res.setMessage("User Email not Found");
            }
            return res;

        }catch (Exception e){
            res.setStatusCode(500);
            res.setMessage("Error Occurred: "+ e.getMessage());
            return res;
        }
    }

    public TaskReqRes update(int taskId, TaskReqRes req){
        TaskReqRes res = new TaskReqRes();
        try{
            Optional<Task> result = taskRepo.findById(taskId);
            if(result.isPresent()){
                Task existingtask = result.get();
                existingtask.setName(req.getName());
                existingtask.setDescription(req.getDescription());
                existingtask.setStatus(req.getStatus());
                existingtask.setDeadline(req.getDeadline());
                Task updatedTask = taskRepo.save(existingtask);
                res.setTask(updatedTask);
                res.setStatusCode(200);
                res.setMessage("Successful");
            } else {
                res.setStatusCode(404);
                res.setMessage("Task Not Found");
            }
            return res;
        } catch(Exception e){
            res.setStatusCode(500);
            res.setMessage("Error Occurred: "+ e.getMessage());
            return res;
        }
    }

    public TaskReqRes delete(int taskId){
        TaskReqRes res = new TaskReqRes();
        try{
            Optional<Task> result = taskRepo.findById(taskId);
            if(result.isPresent()){
                taskRepo.deleteById(taskId);
                res.setStatusCode(200);
                res.setMessage("Task deleted successful");
            } else{
                res.setStatusCode(404);
                res.setMessage("Task Not Found");
            }
            return res;
        } catch (Exception e){
            res.setStatusCode(500);
            res.setMessage("Error Occurred: "+ e.getMessage());
            return res;
        }
    }
}
