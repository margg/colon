package pl.edu.agh.to.app.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.List;
import java.util.Set;

public class Group {
    private long id;
    private String name;
    @JsonBackReference
    private Teacher teacher;
    private Set<Student> students;

    public Group(Teacher teacher, Set<Student> students, String name) {
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

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student){
        this.students.add(student);
    }

}
