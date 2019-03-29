/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Reiner
 */
@Named(value = "nameBean")
@RequestScoped
public class NameBean {

    private String fname;
    private String mname;
    private String sname;

    /**
     * Get the value of mname
     *
     * @return the value of mname
     */
    public String getMname() {
        return mname;
    }

    /**
     * Set the value of mname
     *
     * @param mname new value of mname
     */
    public void setMname(String mname) {
        this.mname = mname;
    }

    /**
     * Get the value of sname
     *
     * @return the value of sname
     */
    public String getSname() {
        return sname;
    }

    /**
     * Set the value of sname
     *
     * @param sname new value of sname
     */
    public void setSname(String sname) {
        this.sname = sname;
    }

    /**
     * Get the value of fname
     *
     * @return the value of fname
     */
    public String getFname() {
        return fname;
    }

    /**
     * Set the value of fname
     *
     * @param fname new value of fname
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * Creates a new instance of NameBean
     */
    public NameBean() {
    }

}
