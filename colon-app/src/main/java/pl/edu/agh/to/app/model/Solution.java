package pl.edu.agh.to.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import pl.edu.agh.to.app.view.SolutionSerializer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@JsonSerialize(using = SolutionSerializer.class)
public class Solution {

    private long id;
    private Integer execTime;
    private String status;
    private User author;
    @JsonBackReference
    private Task task;
    private String code;
    private String filePath;

    public Solution(Integer execTime, String status, User author, Task task, String code) {
        this.execTime = execTime;
        this.status = status;
        this.author = author;
        this.task = task;
        this.code = code;
    }

    public Solution() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getExecTime() {
        return execTime;
    }

    public void setExecTime(Integer execTime) {
        this.execTime = execTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getRankPosition() {
        List<Solution> solutions = new ArrayList<Solution>(this.task.getSolutions());
        solutions.sort(new Comparator<Solution>() {
            @Override
            public int compare(Solution o1, Solution o2) {
                return o1.getExecTime().compareTo(o2.getExecTime());
            }
        });
        return solutions.indexOf(this);
    }
}
