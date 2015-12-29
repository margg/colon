package pl.edu.agh.to.app.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.List;

public class Group {
    private long id;
    private String name;
    @JsonBackReference
    private Teacher teacher;
    private List<Student> students;

    public Group(Teacher teacher, List<Student> students, String name) {
        this.teacher = teacher;
        this.students = students;
        this.name = name;
    }

    public Group() {
    }
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @JsonBackReference
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

}
