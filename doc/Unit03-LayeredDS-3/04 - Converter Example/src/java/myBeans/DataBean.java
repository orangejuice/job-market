/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myBeans;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Reiner
 */
@ManagedBean
@RequestScoped
public class DataBean {

    private Date today;
    private int intNum = 123;
    private double doubleNum = 100.12678765;
    private double doubleNum2 = 100.0;
    private double currency = 56.5555;


    /**
     * Get the value of doubleNum2
     *
     * @return the value of doubleNum2
     */
    public double getDoubleNum2() {
        return doubleNum2;
    }

    /**
     * Set the value of doubleNum2
     *
     * @param doubleNum2 new value of doubleNum2
     */
    public void setDoubleNum2(double doubleNum2) {
        this.doubleNum2 = doubleNum2;
    }

    /**
     * Get the value of currency
     *
     * @return the value of currency
     */
    public double getCurrency() {
        return currency;
    }

    /**
     * Set the value of currency
     *
     * @param currency new value of currency
     */
    public void setCurrency(double currency) {
        this.currency = currency;
    }


    /**
     * Get the value of doubleNum
     *
     * @return the value of doubleNum
     */
    public double getDoubleNum() {
        return doubleNum;
    }

    /**
     * Set the value of doubleNum
     *
     * @param doubleNum new value of doubleNum
     */
    public void setDoubleNum(double doubleNum) {
        this.doubleNum = doubleNum;
    }

    /**
     * Get the value of intNum
     *
     * @return the value of intNum
     */
    public int getIntNum() {
        return intNum;
    }

    /**
     * Set the value of intNum
     *
     * @param intNum new value of intNum
     */
    public void setIntNum(int intNum) {
        this.intNum = intNum;
    }

    
    /**
     * Creates a new instance of dateBean
     */
    public DataBean() {
        today = new Date();
    }

    /**
     * Getter for today's date
     * @return today's date
     */
    public Date getToday() {
        return today;
    }

    /**
     * Setter for today's date
     * @param today Today's date
     */
    public void setToday(Date today) {
        this.today = today;
    } 
}
