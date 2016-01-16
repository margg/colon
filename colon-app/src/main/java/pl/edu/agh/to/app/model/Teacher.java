package pl.edu.agh.to.app.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;
import java.util.Set;

public class Teacher extends User {

    @JsonManagedReference
    private Set<Group> groups;
    @JsonManagedReference
    private Set<Task> tasks;

    public Teacher(String firstName, String lastName, String username, String password, Set<Group> groups, Set<Task> tasks) {
        super(firstName, lastName, username, password);
        this.groups = groups;
        this.tasks = tasks;
    }

    public Teacher() {
    }


    @JsonManagedReference
    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    public Group getGroupByName(String name) {
        for (Group g : this.groups) {
            if (g.getName().equals(name)){
                return g;
            }
        }
        return null;
    }

    public void addGroup(Group group) {
        this.groups.add(group);
    }

    @JsonManagedReference
    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task){
        this.tasks.add(task);
    }
}
