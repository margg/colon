package pl.edu.agh.to.app.model;

public class Solution {

    private long id;
    private int execTime;
    private String status;
    private User author;
    private Task task;
    private String code;

    public Solution(int execTime, String status, User author, Task task, String code) {
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

    public int getExecTime() {
        return execTime;
    }

    public void setExecTime(int execTime) {
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
}
