package com.hdu.model;

import java.util.Date;

/**
 * Created by Flight on 2015/7/20.
 */
public class Attend {
    private int id;
    private int emp_id;
    private int department_id;
    private Date punchtime;
    private String remark;

    public Date getPunchtime() {
        return punchtime;
    }

    public void setPunchtime(Date punchtime) {
        this.punchtime = punchtime;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
