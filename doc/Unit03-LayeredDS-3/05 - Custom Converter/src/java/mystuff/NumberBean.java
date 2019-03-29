/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mystuff;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Reiner
 */
@Named(value = "numberBean")
@RequestScoped
public class NumberBean {

    private long hexNum1;
    private long hexNum2;

    /**
     * Get the value of hexNum2
     *
     * @return the value of hexNum2
     */
    public long getHexNum2() {
        return hexNum2;
    }

    /**
     * Set the value of hexNum2
     *
     * @param hexNum2 new value of hexNum2
     */
    public void setHexNum2(long hexNum2) {
        this.hexNum2 = hexNum2;
    }

    /**
     * Get the value of hexNum
     *
     * @return the value of hexNum
     */
    public long getHexNum1() {
        return hexNum1;
    }

    /**
     * Set the value of hexNum
     *
     * @param hexNum new value of hexNum
     */
    public void setHexNum1(long hexNum) {
        this.hexNum1 = hexNum;
    }

    /**
     * Creates a new instance of NumberBean
     */
    public NumberBean() {
    }
    
}
