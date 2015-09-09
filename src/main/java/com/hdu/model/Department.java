package com.hdu.model;

/**
 * Created by Flight on 2015/7/20.
 */
public class Department {
    private int id;
    private String name;
    private String manager;
    private int employeecount;

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

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public int getEmployeecount() {
        return employeecount;
    }

    public void setEmployeecount(int employeecount) {
        this.employeecount = employeecount;
    }
}
