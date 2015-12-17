package pl.edu.agh.to.app.model;

import java.util.List;

public class Student extends User{

    private long id;
    private Group group;
    private List<Solution> solutions;

    public Student(String firstName, String lastName, String username, String password, List<Solution> solutions) {
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

    public List<Solution> getSolutions() {
        return solutions;
    }

    public void setSolutions(List<Solution> solutions) {
        this.solutions = solutions;
    }

}
