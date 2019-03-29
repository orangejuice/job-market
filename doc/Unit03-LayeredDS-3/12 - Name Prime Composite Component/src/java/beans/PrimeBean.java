/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.validation.constraints.Size;

/**
 *
 * @author Reiner
 */
@Named(value = "primeBean")
@RequestScoped
public class PrimeBean {

    @Size(min = 1, max = 45)
    private String name;
    private int totalSum;
    private String response;

    /**
     * Get the value of response
     *
     * @return the value of response
     */
    public String getResponse() {
        return response;
    }

    /**
     * Set the value of response
     *
     * @param response new value of response
     */
    public void setResponse(String response) {
        this.response = response;
    }

    /**
     * Get the value of totalSum
     *
     * @return the value of totalSum
     */
    public int getTotalSum() {
        return totalSum;
    }

    /**
     * Set the value of totalSum
     *
     * @param totalSum new value of totalSum
     */
    public void setTotalSum(int totalSum) {
        this.totalSum = totalSum;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Creates a new instance of PrimeBean
     */
    public PrimeBean() {
    }

    public String calculate() {
        response = "";
        String allcaps = name.toUpperCase();
        totalSum = 0;
        for (int i = 0; i < allcaps.length(); i++) {
            // sum up all values, 
            // shift value of A to 1 by subtracting 64
            if (Character.isAlphabetic(allcaps.charAt(i))) {
                totalSum += allcaps.charAt(i) - 64;
            }
        }
        response = name + " equals " + totalSum + ", which is ";
        if (isPrime(totalSum)) {
            response += "prime.";
        } else {
            response += "not prime";
        }
        return response;
    }

    /**
     * private helper method to establish prime property of a number
     * 
     * @param n number to be tested
     * @return true if n is prime, false otherwise
     */
    private boolean isPrime(int n) {
        boolean result = true;
        //check if n is a multiple of 2
        if (n % 2 == 0) {
            result = false;
        } else {
            //if not, then just check the odds
            for (int i = 3; i * i <= n; i += 2) {
                if (n % i == 0) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

}
