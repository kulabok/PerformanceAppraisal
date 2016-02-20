package DAO.Entities;

import java.util.List;

/**
 * This is the entity class of employee.
 * Contains all Employee data.
 * @author Viktor Bolshakov on 30.01.16.
 */
public class Employee {
    private int id;
    private String name;
    private String lastname;
    private String mobile;
    private String login;
    private String password;
    private Structure structure;
    private boolean isSuperior;
    private boolean isCeo;
    private List<Task> taskList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public boolean isSuperior() {
        return isSuperior;
    }

    public void setSuperior(boolean isSuperior) {
        this.isSuperior = isSuperior;
    }

    public boolean isCeo() {
        return isCeo;
    }

    public void setCeo(boolean isCeo) {
        this.isCeo = isCeo;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
}
