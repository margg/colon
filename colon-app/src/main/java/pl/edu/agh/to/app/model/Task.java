package pl.edu.agh.to.app.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Task {

    @JsonBackReference
    private Teacher teacher;
    @JsonManagedReference
    private Map<Group, Date> dates;
    private int timeLimit;
    private String testInput;
    private String testOutput;

    public Task(Teacher teacher, Map<Group, Date> dates, int timeLimit, String testInput, String testOutput) {
        this.teacher = teacher;
        this.dates = dates;
        this.timeLimit = timeLimit;
        this.testInput = testInput;
        this.testOutput = testOutput;
    }

    public Task() {
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

    public String getTestInput() {
        return testInput;
    }

    public void setTestInput(String testInput) {
        this.testInput = testInput;
    }

    public String getTestOutput() {
        return testOutput;
    }

    public void setTestOutput(String testOutput) {
        this.testOutput = testOutput;
    }
}
