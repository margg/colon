package pl.edu.agh.to.app.model;

import com.fasterxml.jackson.annotation.*;

import java.util.List;
import java.util.Set;

public class Student extends User{
    @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    private Group group;
    private Set<Solution> solutions;

    public Student(String firstName, String lastName, String username, String password, Set<Solution> solutions) {
        super(firstName, lastName, username, password);
        this.solutions = solutions;
    }

    public Student() {
        super();
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Set<Solution> getSolutions() {
        return solutions;
    }

    public void setSolutions(Set<Solution> solutions) {
        this.solutions = solutions;
    }

    public void addSolution(Solution solution){
        this.solutions.add(solution);
    }

}
