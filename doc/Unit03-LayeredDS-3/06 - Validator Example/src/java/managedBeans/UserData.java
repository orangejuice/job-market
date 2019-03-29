/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Reiner
 */
@ManagedBean
@SessionScoped
public class UserData {

    private int intValue;
    private double doubleValue;
    private String stringValue;
    private String regexValue;

    /**
     * Getter for integer value
     * @return current integer value
     */
    public int getIntValue() {
        return intValue;
    }

    /**
     * Setter for integer value
     * @param intValue new integer value
     */
    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    /**
     * Getter for double value
     * @return current double value
     */
    public double getDoubleValue() {
        return doubleValue;
    }

    /**
     * Setter for double value
     * @param doubleValue new double value
     */
    public void setDoubleValue(double doubleValue) {
        this.doubleValue = doubleValue;
    }

    /**
     * Getter for string value
     * @return current string value
     */
    public String getStringValue() {
        return stringValue;
    }

    /**
     * Setter for string value
     * @param stringValue  new string value
     */
    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    /**
     * Getter for regular expression
     * @return current regular expression
     */
    public String getRegexValue() {
        return regexValue;
    }

    /**
     * Setter for regular expression
     * @param regexValue new regular expression value
     */
    public void setRegexValue(String regexValue) {
        this.regexValue = regexValue;
    }
    
    /**
     * Creates a new instance of UserData
     */
    public UserData() {
    }
    
}
