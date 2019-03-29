/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hello1;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Reiner
 */
@ManagedBean
@RequestScoped
public class Hello {
    private String name;

    /**
     * Default constructor
     */
    public Hello() {
    }

    /**
     * Getter for name
     * @return current value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name
     * @param user_name new value for name
     */
    public void setName(String user_name) {
        this.name = user_name;
    }
    
}
