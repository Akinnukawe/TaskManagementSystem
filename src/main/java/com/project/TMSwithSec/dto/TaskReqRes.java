package com.project.TMSwithSec.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.TMSwithSec.entity.Task;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskReqRes {
    private int statusCode;
    private String error;
    private String message;
    private Integer id;
    private String name;
    private String description;
    private String deadline;
    private String status;
    private Integer userId;
    private String userEmail;
    private String userFirstname;
    private String userLastname;
    private Task task;
    private List<Task> tasks;
}
