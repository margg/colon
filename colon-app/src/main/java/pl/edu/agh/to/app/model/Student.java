package pl.edu.agh.to.app.model;

import java.util.List;

public class Student extends User{

    private List<Solution> solutions;

    public Student(String firstName, String lastName, String username, List<Solution> solutions) {
        super(firstName, lastName, username);
        this.solutions = solutions;
    }

    public Student() {
        super();
    }

    public List<Solution> getSolutions() {
        return solutions;
    }

    public void setSolutions(List<Solution> solutions) {
        this.solutions = solutions;
    }

}
