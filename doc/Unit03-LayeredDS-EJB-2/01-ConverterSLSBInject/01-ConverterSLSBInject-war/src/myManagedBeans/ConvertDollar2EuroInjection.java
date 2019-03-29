/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myManagedBeans;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

import converterEJB.*;
/**
 *
 * @author Reiner
 */
@Named(value = "convertInject")
@RequestScoped
public class ConvertDollar2EuroInjection {


    // use dependency injection to access EJB
    @EJB
    ConverterInj converter;

    private double amount;

    /**
     * Creates a new instance of ConvertDollar2EuroLookup
     */
    public ConvertDollar2EuroInjection() {
        amount = 0;
    }

    /**
     * Get the value of amount
     *
     * @return the value of amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Set the value of amount
     *
     * @param amount new value of amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Converts the stored dollar amount into euro Utilizes the ConverterBean
     * EJB via JNDI lookup
     *
     * @return Euro equivalent of dollar amount
     */
    public double getEuro() {
        double euro = 0;
        euro = converter.dollarToEuro(amount);
        double pount = converter.dollarToEuro(euro);
        return euro;
    }
}
