package pl.edu.agh.to.app.model;


import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import pl.edu.agh.to.app.view.GroupDateSerializer;

import java.util.*;
public class Task {
    private long id;
    private String name;
    private String description;
    @JsonBackReference
    private Teacher teacher;
    @JsonSerialize(using = GroupDateSerializer.class)
    private Map<Group, Date> dates;
    private int timeLimit;
    private String testInput;
    private String testOutput;
    @JsonManagedReference
    private Set<Solution> solutions;

    public Task(String name, String description, Teacher teacher, Map<Group, Date> dates, int timeLimit, String testInput, String testOutput) {
        this.name = name;
        this.description = description;
        this.teacher = teacher;
        this.dates = dates;
        this.timeLimit = timeLimit;
        this.testInput = testInput;
        this.testOutput = testOutput;
    }

    public Task() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonBackReference
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public Map<Group, Date> getDates() {
        return dates;
    }

    public void setDates(Map<Group, Date> dates) {
        this.dates = dates;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    // These should load test files from colon-files
    public String getTestInput() {
        return testInput;
    }

    public String getTestOutput() {
        return testOutput;
    }

    // These should save test files to colon-files
    public void setTestInput(String testInput) {
        this.testInput = testInput;
    }

    public void setTestOutput(String testOutput) {
        this.testOutput = testOutput;
    }

    public Set<Solution> getSolutions() {
        return solutions;
    }

    public void setSolutions(Set<Solution> solutions) {
        this.solutions = solutions;
    }

}
