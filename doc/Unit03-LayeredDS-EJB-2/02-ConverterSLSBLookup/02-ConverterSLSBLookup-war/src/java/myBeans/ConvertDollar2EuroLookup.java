/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myBeans;

import converterEJB.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author reiner.dojen
 */
@ManagedBean
@Named(value = "convertLook")
@RequestScoped

public class ConvertDollar2EuroLookup {

    private double amount;

    /**
     * Creates a new instance of ConvertDollar2EuroLookup
     */
    public ConvertDollar2EuroLookup() {
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
        Converter conv;
        double euro = 0;

        try {
            // obtain initial context object
            InitialContext context =
                    new InitialContext();
            
            // obtain reference to bean via lookup
            // Option 1: use java:global namespace
            conv = (Converter) 
                    context.lookup("java:global/02-ConverterSLSBLookup/02-ConverterSLSBLookup-ejb/ConverterBean!converterEJB.Converter");
            // Option 2: use java:app namespace
            // conv = (Converter) context.lookup("java:app/02-ConverterSLSBLookup-ejb/ConverterBean!converterEJB.Converter");

            // invoke EJB method
            euro = conv.dollarToEuro(amount);

        } catch (NamingException ex) {
            Logger.getLogger(ConvertDollar2EuroLookup.class.getName()).log(Level.SEVERE, null, ex);
        }
        return euro;

    }
}
